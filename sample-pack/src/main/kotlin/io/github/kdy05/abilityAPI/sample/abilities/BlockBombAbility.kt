package io.github.kdy05.abilityAPI.sample.abilities

import io.github.kdy05.abilityAPI.ability.AbilityMeta
import io.github.kdy05.abilityAPI.rank.Rank
import io.github.kdy05.abilityAPI.sample.SimpleAbility
import io.github.kdy05.abilityAPI.skill.PassiveSkill
import io.github.kdy05.abilityAPI.skill.Skill
import io.github.kdy05.abilityAPI.skill.SkillContext
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.block.BlockBreakEvent

@AbilityMeta(
    name = "폭파",
    rank = Rank.A,
    guide = ["블록을 부수면 그 자리에 TNT가 소환됩니다."],
)
class BlockBombAbility(owner: Player, context: SkillContext) : SimpleAbility(owner, context) {

    private val tntType: EntityType = listOf("TNT", "PRIMED_TNT")
        .firstNotNullOf { name -> runCatching { EntityType::class.java.getField(name).get(null) as? EntityType }.getOrNull() }

    private val bombSkill = object : PassiveSkill(owner, context) {
        override fun register() {
            on(BlockBreakEvent::class) { e ->
                if (e.player != owner) return@on
                val tnt = e.block.world.spawnEntity(
                    e.block.location.add(0.5, 0.0, 0.5),
                    tntType
                ) as TNTPrimed
                tnt.fuseTicks = 40
            }
        }
    }

    override fun buildSkills(): List<Skill> = listOf(bombSkill)
}
