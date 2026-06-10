package io.github.kdy05.abilityAPI.sample.abilities

import io.github.kdy05.abilityAPI.ability.AbilityMeta
import io.github.kdy05.abilityAPI.rank.Rank
import io.github.kdy05.abilityAPI.sample.SimpleAbility
import io.github.kdy05.abilityAPI.skill.Skill
import io.github.kdy05.abilityAPI.skill.SkillContext
import org.bukkit.Material
import org.bukkit.entity.Player

@AbilityMeta(
    name = "스택 테스트",
    rank = Rank.F,
    guide = ["손에 불씨를 들고 우클릭하면 스택을 소모합니다.", "최대 3스택, 스택당 5초 충전"],
)
class StackTestAbility(owner: Player, context: SkillContext) : SimpleAbility(owner, context) {

    private val stackSkill = object : SimpleStackableActiveSkill() {
        override val maxStacks: Int = 3
        override val cooldownTicks: Long = 100L // 5초

        override fun register() {
            onRightClick { e ->
                if (e.player.inventory.itemInMainHand.type == Material.BLAZE_ROD) {
                    activate()
                }
            }
        }

        override fun onActivate() {
            owner.sendMessage("§6스택 사용! §f(남은 스택: $stacks/$maxStacks)")
        }
    }

    override fun buildSkills(): List<Skill> = listOf(stackSkill)
}