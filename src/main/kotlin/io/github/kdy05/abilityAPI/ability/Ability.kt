package io.github.kdy05.abilityAPI.ability

import io.github.kdy05.abilityAPI.skill.Skill
import io.github.kdy05.abilityAPI.skill.SkillContext
import org.bukkit.entity.Player

abstract class Ability(
    val owner: Player,
    protected val context: SkillContext,
) {
    private val _skills: List<Skill> by lazy { buildSkills() }

    protected abstract fun buildSkills(): List<Skill>

    fun skills(): List<Skill> = _skills

    fun start()             = _skills.forEach { it.onStart() }
    fun startWithCooldown() = _skills.forEach { it.onStartWithCooldown() }
    fun stop()              = _skills.forEach { it.onStop() }
}
