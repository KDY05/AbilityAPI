package io.github.kdy05.abilityAPI.event

import io.github.kdy05.abilityAPI.ability.Ability
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class AbilityRemoveEvent(val player: Player, val ability: Ability) : Event() {
    override fun getHandlers(): HandlerList = handlerList
    companion object { @JvmStatic val handlerList = HandlerList() }
}
