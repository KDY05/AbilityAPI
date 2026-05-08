package com.github.kdy05.abilityAPI.skill

import com.github.kdy05.abilityAPI.event.SkillActivateEvent
import org.bukkit.entity.Player

abstract class ActiveSkill(
    owner: Player,
    context: SkillContext,
) : SkillBase(owner, context) {

    enum class State { READY, COOLDOWN }

    var state = State.READY
        private set

    abstract val cooldownTicks: Long

    abstract fun onActivate()

    open fun onSilenceAttempt() {}
    open fun onCooldownAttempt(remainingSeconds: Int) {}
    open fun onCooldownRunning(remainingSeconds: Int) {}
    open fun onCooldownEnd() {}

    private var cooldownSecondsLeft = 0
    private var cooldownToken: Any? = null
    private var runningToken: Any? = null

    fun activate() {
        if (context.isSilenced(owner)) {
            onSilenceAttempt()
            return
        }
        if (state == State.COOLDOWN) {
            onCooldownAttempt(cooldownSecondsLeft)
            return
        }
        if (context.callEvent(SkillActivateEvent(owner, this)).isCancelled) return
        state = State.COOLDOWN
        cooldownSecondsLeft = (cooldownTicks / 20L).toInt()
        onActivate()
        runningToken = context.scheduleRepeat(20L) {
            if (cooldownSecondsLeft > 0) onCooldownRunning(cooldownSecondsLeft--)
        }
        cooldownToken = context.scheduleOnce(cooldownTicks) {
            runningToken?.let { context.cancelSchedule(it) }
            runningToken = null
            state = State.READY
            onCooldownEnd()
        }
    }

    override fun onStart() = register()

    override fun onStartWithCooldown() {
        register()
        state = State.COOLDOWN
        cooldownSecondsLeft = (cooldownTicks / 20L).toInt()
        runningToken = context.scheduleRepeat(20L) {
            if (cooldownSecondsLeft > 0) onCooldownRunning(cooldownSecondsLeft--)
        }
        cooldownToken = context.scheduleOnce(cooldownTicks) {
            runningToken?.let { context.cancelSchedule(it) }
            runningToken = null
            state = State.READY
            onCooldownEnd()
        }
    }

    override fun onStop() {
        cooldownToken?.let { context.cancelSchedule(it) }
        runningToken?.let { context.cancelSchedule(it) }
        cooldownToken = null
        runningToken = null
        unsubscribeAll()
        state = State.READY
    }
}
