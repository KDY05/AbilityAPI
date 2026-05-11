package io.github.kdy05.abilityAPI.skill

import io.github.kdy05.abilityAPI.event.SkillActivateEvent
import org.bukkit.entity.Player

abstract class StackableActiveSkill(
    owner: Player,
    context: SkillContext,
) : CooldownSkillBase(owner, context) {

    abstract val maxStacks: Int

    abstract fun onActivate()

    open fun onSilenceAttempt() {}
    open fun onStackEmptyAttempt() {}
    open fun onStackGained(currentStacks: Int) {}

    var stacks: Int = 0
        private set

    private fun startRecharge() {
        beginCooldown {
            stacks++
            onStackGained(stacks)
            if (stacks < maxStacks) startRecharge()
        }
    }

    fun activate() {
        if (context.isSilenced(owner)) {
            onSilenceAttempt()
            return
        }
        if (stacks == 0) {
            onStackEmptyAttempt()
            return
        }
        if (context.callEvent(SkillActivateEvent(owner, this)).isCancelled) return
        val wasAtMax = stacks == maxStacks
        stacks--
        onActivate()
        if (wasAtMax) startRecharge()
    }

    override fun onStart() {
        register()
        stacks = maxStacks
    }

    override fun onStartWithCooldown() {
        register()
        stacks = 0
        startRecharge()
    }

    override fun onStop() {
        cancelCooldownTimers()
        unsubscribeAll()
        stacks = 0
    }
}