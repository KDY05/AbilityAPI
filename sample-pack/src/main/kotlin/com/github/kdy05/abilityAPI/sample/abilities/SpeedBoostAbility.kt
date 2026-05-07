package com.github.kdy05.abilityAPI.sample.abilities

import com.github.kdy05.abilityAPI.ability.AbilityMeta
import com.github.kdy05.abilityAPI.rank.Rank
import com.github.kdy05.abilityAPI.sample.SimpleAbility
import com.github.kdy05.abilityAPI.skill.PassiveSkill
import com.github.kdy05.abilityAPI.skill.Skill
import com.github.kdy05.abilityAPI.skill.SkillContext
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.entity.Player

@AbilityMeta(
    name = "질주",
    rank = Rank.C,
    guide = ["항상 이동 속도가 빨라집니다."],
)
class SpeedBoostAbility(owner: Player, context: SkillContext) : SimpleAbility(owner, context) {

    private val speedSkill = object : PassiveSkill(owner, context) {
        private val modifierKey = NamespacedKey.fromString("abilityapi:speed_boost")!!
        private val modifier = AttributeModifier(modifierKey, 0.06, AttributeModifier.Operation.ADD_NUMBER)

        override fun register() {
            owner.getAttribute(Attribute.MOVEMENT_SPEED)?.addModifier(modifier)
        }

        override fun onStop() {
            owner.getAttribute(Attribute.MOVEMENT_SPEED)?.removeModifier(modifier)
            super.onStop()
        }
    }

    override fun skills(): List<Skill> = listOf(speedSkill)
}
