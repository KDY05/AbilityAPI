package com.github.kdy05.abilityAPI.skill

import org.bukkit.entity.Player
import org.bukkit.event.Event
import kotlin.reflect.KClass

interface SkillContext {
    fun <T : Event> subscribe(eventClass: KClass<T>, handler: (T) -> Unit): Any
    fun unsubscribe(token: Any)
    fun scheduleOnce(delayTicks: Long, task: () -> Unit): Any
    fun scheduleRepeat(periodTicks: Long, task: () -> Unit): Any
    fun cancelSchedule(token: Any)
    fun isSilenced(player: Player): Boolean
    fun <T : Event> callEvent(event: T): T
}
