package io.github.kdy05.abilityAPI

import io.github.kdy05.abilityAPI.ability.Ability

interface AbilityRegistrar {
    fun register(vararg types: Class<out Ability>)
    fun getAll(): List<Class<out Ability>>
    fun getByName(name: String): Class<out Ability>?
    fun getCount(): Int
}
