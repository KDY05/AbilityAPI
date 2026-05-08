package io.github.kdy05.abilityAPI.skill

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import kotlin.reflect.KClass

abstract class SkillBase(
    protected val owner: Player,
    protected val context: SkillContext,
) : Skill {

    private val tokens = mutableListOf<Any>()

    protected fun <T : Event> on(eventClass: KClass<T>, handler: (T) -> Unit) {
        tokens += context.subscribe(eventClass, handler)
    }

    fun onLeftClick(handler: (PlayerInteractEvent) -> Unit) =
        on(PlayerInteractEvent::class) { e ->
            if (e.player == owner && e.action.isLeftClick) handler(e)
        }

    fun onRightClick(handler: (PlayerInteractEvent) -> Unit) =
        on(PlayerInteractEvent::class) { e ->
            if (e.player == owner && e.action.isRightClick) handler(e)
        }

    fun onEntityDamage(handler: (EntityDamageByEntityEvent) -> Unit) =
        on(EntityDamageByEntityEvent::class) { e ->
            if (e.damager == owner) handler(e)
        }

    fun onEntityDamaged(handler: (EntityDamageByEntityEvent) -> Unit) =
        on(EntityDamageByEntityEvent::class) { e ->
            if (e.entity == owner) handler(e)
        }

    abstract fun register()

    protected fun unsubscribeAll() {
        tokens.forEach { context.unsubscribe(it) }
        tokens.clear()
    }
}
