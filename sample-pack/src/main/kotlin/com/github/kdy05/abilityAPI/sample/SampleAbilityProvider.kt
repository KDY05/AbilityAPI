package com.github.kdy05.abilityAPI.sample

import com.github.kdy05.abilityAPI.AbilityProvider
import com.github.kdy05.abilityAPI.ability.Ability
import com.github.kdy05.abilityAPI.sample.abilities.BlockBombAbility
import com.github.kdy05.abilityAPI.sample.abilities.DashAbility
import com.github.kdy05.abilityAPI.sample.abilities.InvisibilityAbility
import com.github.kdy05.abilityAPI.sample.abilities.SpeedBoostAbility
import kotlin.reflect.KClass

class SampleAbilityProvider : AbilityProvider {
    override fun namespace(): String = "sample"
    override fun provide(): List<KClass<out Ability>> = listOf(
        SpeedBoostAbility::class,
        DashAbility::class,
        InvisibilityAbility::class,
        BlockBombAbility::class,
    )
}
