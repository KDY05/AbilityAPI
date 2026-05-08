package com.github.kdy05.abilityAPI.ability

import com.github.kdy05.abilityAPI.skill.Skill
import com.github.kdy05.abilityAPI.skill.SkillContext
import org.bukkit.entity.Player

abstract class Ability(
    val owner: Player,
    protected val context: SkillContext,
) {
    abstract fun skills(): List<Skill>

    fun start()             = skills().forEach { it.onStart() }
    fun startWithCooldown() = skills().forEach { it.onStartWithCooldown() }
    fun stop()              = skills().forEach { it.onStop() }
}
