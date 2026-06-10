package io.github.kdy05.abilityAPI.sample.plugin

import io.github.kdy05.abilityAPI.AbilityAPI
import io.github.kdy05.abilityAPI.sample.abilities.BlockBombAbility
import io.github.kdy05.abilityAPI.sample.abilities.DashAbility
import io.github.kdy05.abilityAPI.sample.abilities.InvisibilityAbility
import io.github.kdy05.abilityAPI.sample.abilities.SpeedBoostAbility
import io.github.kdy05.abilityAPI.sample.abilities.StackTestAbility
import org.bukkit.plugin.java.JavaPlugin

class SamplePlugin : JavaPlugin() {

    override fun onEnable() {
        AbilityAPI.service.registrar.register(
            SpeedBoostAbility::class.java,
            DashAbility::class.java,
            InvisibilityAbility::class.java,
            BlockBombAbility::class.java,
            StackTestAbility::class.java,
        )
        logger.info("SamplePlugin: 5개 능력 등록 완료")
    }
}