package io.github.kdy05.abilityAPI.skill

import org.bukkit.entity.Player

abstract class CooldownSkillBase(
    owner: Player,
    context: SkillContext,
) : SkillBase(owner, context) {

    abstract val cooldownTicks: Long

    open fun onCooldownRunning(remainingSeconds: Int) {}

    protected var cooldownSecondsLeft = 0
        private set

    private var cooldownToken: Any? = null
    private var cooldownRunningToken: Any? = null

    protected fun beginCooldown(onEnd: () -> Unit) {
        cooldownSecondsLeft = (cooldownTicks / 20L).toInt()
        cooldownRunningToken = context.scheduleRepeat(20L) {
            if (cooldownSecondsLeft > 0) onCooldownRunning(cooldownSecondsLeft--)
        }
        cooldownToken = context.scheduleOnce(cooldownTicks) {
            cooldownRunningToken?.let { context.cancelSchedule(it) }
            cooldownRunningToken = null
            onEnd()
        }
    }

    protected fun cancelCooldownTimers() {
        cooldownToken?.let { context.cancelSchedule(it) }
        cooldownRunningToken?.let { context.cancelSchedule(it) }
        cooldownToken = null
        cooldownRunningToken = null
    }
}