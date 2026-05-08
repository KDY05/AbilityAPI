package io.github.kdy05.abilityAPI.sample

import io.github.kdy05.abilityAPI.ability.Ability
import io.github.kdy05.abilityAPI.skill.ActiveContinueSkill
import io.github.kdy05.abilityAPI.skill.ActiveSkill
import io.github.kdy05.abilityAPI.skill.SkillContext
import org.bukkit.entity.Player

abstract class SimpleAbility(owner: Player, context: SkillContext) : Ability(owner, context) {

    abstract inner class SimpleActiveSkill : ActiveSkill(owner, context) {
        override fun onCooldownAttempt(remainingSeconds: Int) {
            owner.sendMessage("§c쿨다운 중입니다! §f(${remainingSeconds}초 남음)")
        }
        override fun onCooldownRunning(remainingSeconds: Int) {
            if (remainingSeconds <= 3) owner.sendMessage("§e쿨다운 종료까지 §f${remainingSeconds}§e초...")
        }
        override fun onCooldownEnd() {
            owner.sendMessage("§a쿨다운이 종료되었습니다!")
        }
    }

    abstract inner class SimpleActiveContinueSkill : ActiveContinueSkill(owner, context) {
        override fun onActiveAttempt(remainingSeconds: Int) {
            owner.sendMessage("§a이미 스킬이 활성화 중입니다! §f(${remainingSeconds}초 남음)")
        }
        override fun onActiveRunning(remainingSeconds: Int) {
            if (remainingSeconds <= 3) owner.sendMessage("§6스킬 종료까지 §f${remainingSeconds}§6초...")
        }
        override fun onActiveEnd() {
            owner.sendMessage("§6스킬이 종료되었습니다!")
        }
        override fun onCooldownAttempt(remainingSeconds: Int) {
            owner.sendMessage("§c쿨다운 중입니다! §f(${remainingSeconds}초 남음)")
        }
        override fun onCooldownRunning(remainingSeconds: Int) {
            if (remainingSeconds <= 3) owner.sendMessage("§e쿨다운 종료까지 §f${remainingSeconds}§e초...")
        }
        override fun onCooldownEnd() {
            owner.sendMessage("§a쿨다운이 종료되었습니다!")
        }
    }
}
