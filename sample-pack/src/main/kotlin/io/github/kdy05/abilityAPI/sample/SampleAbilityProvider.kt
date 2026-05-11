package io.github.kdy05.abilityAPI.sample

import io.github.kdy05.abilityAPI.AbilityProvider
import io.github.kdy05.abilityAPI.ability.Ability
import io.github.kdy05.abilityAPI.sample.abilities.BlockBombAbility
import io.github.kdy05.abilityAPI.sample.abilities.DashAbility
import io.github.kdy05.abilityAPI.sample.abilities.InvisibilityAbility
import io.github.kdy05.abilityAPI.sample.abilities.SpeedBoostAbility
import io.github.kdy05.abilityAPI.sample.abilities.StackTestAbility
import kotlin.reflect.KClass

class SampleAbilityProvider : AbilityProvider {
    override fun namespace(): String = "sample"
    override fun provide(): List<KClass<out Ability>> = listOf(
        SpeedBoostAbility::class,
        DashAbility::class,
        InvisibilityAbility::class,
        BlockBombAbility::class,
        StackTestAbility::class,
    )
}
