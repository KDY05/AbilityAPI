package com.github.kdy05.abilityAPI.ability

import com.github.kdy05.abilityAPI.skill.PassiveSkill
import com.github.kdy05.abilityAPI.skill.Skill
import com.github.kdy05.abilityAPI.skill.SkillContext
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack

abstract class BaseItemAbility(
    owner: Player,
    context: SkillContext,
) : Ability(owner, context) {

    abstract fun createItem(): ItemStack

    private val item: ItemStack = createItem()

    private val itemGuard = object : PassiveSkill(owner, context) {
        override fun register() {
            on(PlayerDropItemEvent::class) { e ->
                if (e.player == owner && e.itemDrop.itemStack.isSimilar(item)) {
                    e.isCancelled = true
                }
            }
            on(PlayerDeathEvent::class) { e ->
                if (e.entity == owner) e.drops.removeIf { it.isSimilar(item) }
            }
            on(PlayerRespawnEvent::class) { e ->
                if (e.player == owner) e.player.inventory.addItem(item)
            }
        }
    }

    // itemGuard가 반드시 포함되도록 final로 봉인
    final override fun skills(): List<Skill> = listOf(itemGuard) + abilitySkills()

    abstract fun abilitySkills(): List<Skill>
}
