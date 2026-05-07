package com.github.kdy05.abilityAPI.skill

import org.bukkit.entity.Player

abstract class PassiveSkill(
    owner: Player,
    context: SkillContext,
) : SkillBase(owner, context) {

    override fun onStart() = register()
    override fun onStop() = unsubscribeAll()
}
