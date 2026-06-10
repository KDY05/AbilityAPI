package io.github.kdy05.abilityAPI.skill

import io.github.kdy05.abilityAPI.event.SkillActivateEvent
import org.bukkit.entity.Player

abstract class ActiveSkill(
    owner: Player,
    context: SkillContext,
) : CooldownSkillBase(owner, context) {

    enum class State { READY, COOLDOWN }

    var state = State.READY
        private set

    abstract fun onActivate()

    open fun onPostActivate() {}

    open fun onSilenceAttempt() {}
    open fun onCooldownAttempt(remainingSeconds: Int) {}
    open fun onCooldownEnd() {}

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
        onActivate()
        onPostActivate()
        beginCooldown {
            state = State.READY
            onCooldownEnd()
        }
    }

    override fun onStart() = register()

    override fun onStartWithCooldown() {
        register()
        state = State.COOLDOWN
        beginCooldown {
            state = State.READY
            onCooldownEnd()
        }
    }

    override fun onStop() {
        cancelCooldownTimers()
        unsubscribeAll()
        state = State.READY
    }
}