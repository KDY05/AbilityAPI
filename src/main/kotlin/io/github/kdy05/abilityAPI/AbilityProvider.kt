package io.github.kdy05.abilityAPI

import io.github.kdy05.abilityAPI.ability.Ability
import kotlin.reflect.KClass

interface AbilityProvider {
    fun namespace(): String
    fun provide(): List<KClass<out Ability>>
}
