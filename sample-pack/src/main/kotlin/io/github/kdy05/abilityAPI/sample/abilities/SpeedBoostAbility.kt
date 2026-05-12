package io.github.kdy05.abilityAPI.sample.abilities

import io.github.kdy05.abilityAPI.ability.AbilityMeta
import io.github.kdy05.abilityAPI.rank.Rank
import io.github.kdy05.abilityAPI.sample.SimpleAbility
import io.github.kdy05.abilityAPI.skill.PassiveSkill
import io.github.kdy05.abilityAPI.skill.Skill
import io.github.kdy05.abilityAPI.skill.SkillContext
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.entity.Player
import java.util.UUID

@AbilityMeta(
    name = "질주",
    rank = Rank.C,
    guide = ["항상 이동 속도가 빨라집니다."],
)
class SpeedBoostAbility(owner: Player, context: SkillContext) : SimpleAbility(owner, context) {

    private val speedSkill = object : PassiveSkill(owner, context) {
        private val movementSpeed: Attribute = listOf("GENERIC_MOVEMENT_SPEED", "MOVEMENT_SPEED")
            .firstNotNullOf { name -> runCatching { Attribute::class.java.getField(name).get(null) as? Attribute }.getOrNull() }
        private val modifier = AttributeModifier(UUID.randomUUID(), "speed_boost", 0.06, AttributeModifier.Operation.ADD_NUMBER)

        override fun register() {
            owner.getAttribute(movementSpeed)?.addModifier(modifier)
        }

        override fun onStop() {
            owner.getAttribute(movementSpeed)?.removeModifier(modifier)
            super.onStop()
        }
    }

    override fun skills(): List<Skill> = listOf(speedSkill)
}
