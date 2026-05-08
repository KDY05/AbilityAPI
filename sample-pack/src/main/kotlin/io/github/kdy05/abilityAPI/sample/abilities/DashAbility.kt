package io.github.kdy05.abilityAPI.sample.abilities

import io.github.kdy05.abilityAPI.ability.AbilityMeta
import io.github.kdy05.abilityAPI.rank.Rank
import io.github.kdy05.abilityAPI.sample.SimpleAbility
import io.github.kdy05.abilityAPI.skill.Skill
import io.github.kdy05.abilityAPI.skill.SkillContext
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.util.Vector

@AbilityMeta(
    name = "돌진",
    rank = Rank.B,
    guide = ["손에 나침반을 들고 우클릭하면 바라보는 방향으로 돌진합니다.", "쿨다운: 5초"],
)
class DashAbility(owner: Player, context: SkillContext) : SimpleAbility(owner, context) {

    private val dashSkill = object : SimpleActiveSkill() {
        override val cooldownTicks: Long = 100L // 5초

        override fun register() {
            onRightClick { e ->
                if (e.player.inventory.itemInMainHand.type == Material.COMPASS) {
                    activate()
                }
            }
        }

        override fun onActivate() {
            val dir: Vector = owner.location.direction.normalize().multiply(2.0)
            dir.y = 0.4
            owner.velocity = dir
        }
    }

    override fun skills(): List<Skill> = listOf(dashSkill)
}
