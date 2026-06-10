package io.github.kdy05.abilityAPI

import io.github.kdy05.abilityAPI.ability.Ability

interface AbilityProvider {
    fun namespace(): String
    fun provide(): List<Class<out Ability>>
}
