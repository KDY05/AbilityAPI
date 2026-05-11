package io.github.kdy05.abilityAPI.sample.plugin

import io.github.kdy05.abilityAPI.AbilityPlugin
import io.github.kdy05.abilityAPI.sample.abilities.BlockBombAbility
import io.github.kdy05.abilityAPI.sample.abilities.DashAbility
import io.github.kdy05.abilityAPI.sample.abilities.InvisibilityAbility
import io.github.kdy05.abilityAPI.sample.abilities.SpeedBoostAbility
import io.github.kdy05.abilityAPI.sample.abilities.StackTestAbility
import org.bukkit.plugin.java.JavaPlugin

class SamplePlugin : JavaPlugin() {

    override fun onEnable() {
        AbilityPlugin.service.registrar.register(
            SpeedBoostAbility::class,
            DashAbility::class,
            InvisibilityAbility::class,
            BlockBombAbility::class,
            StackTestAbility::class,
        )
        logger.info("SamplePlugin: 5개 능력 등록 완료")
    }
}