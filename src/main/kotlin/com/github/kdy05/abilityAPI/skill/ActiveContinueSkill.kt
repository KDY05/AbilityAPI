package com.github.kdy05.abilityAPI.skill

import com.github.kdy05.abilityAPI.event.SkillActivateEvent
import org.bukkit.entity.Player

abstract class ActiveContinueSkill(
    owner: Player,
    context: SkillContext,
) : SkillBase(owner, context) {

    enum class State { READY, ACTIVE, COOLDOWN }

    var state = State.READY
        private set

    abstract val durationTicks: Long
    abstract val cooldownTicks: Long

    abstract fun onActivate()
    abstract fun onDeactivate()

    open fun onSilenceAttempt() {}
    open fun onActiveAttempt(remainingSeconds: Int) {}
    open fun onActiveRunning(remainingSeconds: Int) {}
    open fun onActiveEnd() {}
    open fun onCooldownAttempt(remainingSeconds: Int) {}
    open fun onCooldownRunning(remainingSeconds: Int) {}
    open fun onCooldownEnd() {}

    private var activeSecondsLeft = 0
    private var cooldownSecondsLeft = 0
    private var durationToken: Any? = null
    private var cooldownToken: Any? = null
    private var activeRunningToken: Any? = null
    private var cooldownRunningToken: Any? = null

    fun activate() {
        if (context.isSilenced(owner)) {
            onSilenceAttempt()
            return
        }
        when (state) {
            State.ACTIVE -> onActiveAttempt(activeSecondsLeft)
            State.COOLDOWN -> onCooldownAttempt(cooldownSecondsLeft)
            State.READY -> {
                if (context.callEvent(SkillActivateEvent(owner, this)).isCancelled) return
                state = State.ACTIVE
                activeSecondsLeft = (durationTicks / 20L).toInt()
                onActivate()
                activeRunningToken = context.scheduleRepeat(20L) {
                    if (activeSecondsLeft > 0) onActiveRunning(activeSecondsLeft--)
                }
                durationToken = context.scheduleOnce(durationTicks) {
                    activeRunningToken?.let { context.cancelSchedule(it) }
                    activeRunningToken = null
                    state = State.COOLDOWN
                    cooldownSecondsLeft = (cooldownTicks / 20L).toInt()
                    onDeactivate()
                    onActiveEnd()
                    cooldownRunningToken = context.scheduleRepeat(20L) {
                        if (cooldownSecondsLeft > 0) onCooldownRunning(cooldownSecondsLeft--)
                    }
                    cooldownToken = context.scheduleOnce(cooldownTicks) {
                        cooldownRunningToken?.let { context.cancelSchedule(it) }
                        cooldownRunningToken = null
                        state = State.READY
                        onCooldownEnd()
                    }
                }
            }
        }
    }

    override fun onStart() = register()

    override fun onStop() {
        durationToken?.let { context.cancelSchedule(it) }
        cooldownToken?.let { context.cancelSchedule(it) }
        activeRunningToken?.let { context.cancelSchedule(it) }
        cooldownRunningToken?.let { context.cancelSchedule(it) }
        durationToken = null
        cooldownToken = null
        activeRunningToken = null
        cooldownRunningToken = null
        if (state == State.ACTIVE) onDeactivate()
        unsubscribeAll()
        state = State.READY
    }
}
