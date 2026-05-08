package io.github.kdy05.abilityAPI.sample.abilities

import io.github.kdy05.abilityAPI.ability.AbilityMeta
import io.github.kdy05.abilityAPI.rank.Rank
import io.github.kdy05.abilityAPI.sample.SimpleAbility
import io.github.kdy05.abilityAPI.skill.Skill
import io.github.kdy05.abilityAPI.skill.SkillContext
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

@AbilityMeta(
    name = "은신",
    rank = Rank.A,
    guide = ["손에 유리병을 들고 우클릭하면 3초간 투명해집니다.", "지속: 3초 / 쿨다운: 10초"],
)
class InvisibilityAbility(owner: Player, context: SkillContext) : SimpleAbility(owner, context) {

    private val invisSkill = object : SimpleActiveContinueSkill() {
        override val durationTicks: Long = 60L  // 3초
        override val cooldownTicks: Long = 200L // 10초

        override fun register() {
            onRightClick { e ->
                if (e.player.inventory.itemInMainHand.type == Material.GLASS_BOTTLE) {
                    activate()
                }
            }
        }

        override fun onActivate() {
            owner.addPotionEffect(
                PotionEffect(PotionEffectType.INVISIBILITY, durationTicks.toInt() + 5, 0, false, false)
            )
        }

        override fun onDeactivate() {
            owner.removePotionEffect(PotionEffectType.INVISIBILITY)
        }
    }

    override fun skills(): List<Skill> = listOf(invisSkill)
}
