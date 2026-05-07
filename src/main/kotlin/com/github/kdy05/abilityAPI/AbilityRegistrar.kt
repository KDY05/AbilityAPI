package com.github.kdy05.abilityAPI

import com.github.kdy05.abilityAPI.ability.Ability
import kotlin.reflect.KClass

interface AbilityRegistrar {
    fun register(vararg types: KClass<out Ability>)
}
