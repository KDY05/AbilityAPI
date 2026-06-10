package io.github.kdy05.abilityAPI.sample

import io.github.kdy05.abilityAPI.AbilityProvider
import io.github.kdy05.abilityAPI.ability.Ability
import io.github.kdy05.abilityAPI.sample.abilities.BlockBombAbility
import io.github.kdy05.abilityAPI.sample.abilities.DashAbility
import io.github.kdy05.abilityAPI.sample.abilities.InvisibilityAbility
import io.github.kdy05.abilityAPI.sample.abilities.SpeedBoostAbility
import io.github.kdy05.abilityAPI.sample.abilities.StackTestAbility

class SampleAbilityProvider : AbilityProvider {
    override fun namespace(): String = "sample"
    override fun provide(): List<Class<out Ability>> = listOf(
        SpeedBoostAbility::class.java,
        DashAbility::class.java,
        InvisibilityAbility::class.java,
        BlockBombAbility::class.java,
        StackTestAbility::class.java,
    )
}
