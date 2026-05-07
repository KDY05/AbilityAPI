package com.github.kdy05.abilityAPI.event

import com.github.kdy05.abilityAPI.ability.Ability
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class AbilityGiveEvent(val player: Player, val ability: Ability) : Event() {
    override fun getHandlers(): HandlerList = handlerList
    companion object { @JvmStatic val handlerList = HandlerList() }
}
