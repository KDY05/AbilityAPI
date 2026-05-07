package com.github.kdy05.abilityAPI

import com.github.kdy05.abilityAPI.ability.Ability
import kotlin.reflect.KClass

interface AbilityProvider {
    fun namespace(): String
    fun provide(): List<KClass<out Ability>>
}
