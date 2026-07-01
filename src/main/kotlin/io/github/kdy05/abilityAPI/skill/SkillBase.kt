package io.github.kdy05.abilityAPI.skill

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import kotlin.reflect.KClass

abstract class SkillBase(
    protected val owner: Player,
    protected val context: SkillContext,
) : Skill {

    private val tokens = mutableListOf<Any>()

    protected open fun <T : Event> on(eventClass: KClass<T>, handler: (T) -> Unit) {
        tokens += context.subscribe(eventClass, handler)
    }

    fun onLeftClick(handler: (PlayerInteractEvent) -> Unit) =
        on(PlayerInteractEvent::class) { e ->
            if (e.player == owner && (e.action == Action.LEFT_CLICK_AIR || e.action == Action.LEFT_CLICK_BLOCK)) handler(e)
        }

    fun onRightClick(handler: (PlayerInteractEvent) -> Unit) =
        on(PlayerInteractEvent::class) { e ->
            if (e.player == owner && (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK)) handler(e)
        }

    fun onEntityDamage(handler: (EntityDamageByEntityEvent) -> Unit) =
        on(EntityDamageByEntityEvent::class) { e ->
            if (e.damager == owner) handler(e)
        }

    fun onEntityDamaged(handler: (EntityDamageByEntityEvent) -> Unit) =
        on(EntityDamageByEntityEvent::class) { e ->
            if (e.entity == owner) handler(e)
        }

    fun onDamaged(handler: (EntityDamageEvent) -> Unit) =
        on(EntityDamageEvent::class) { e ->
            if (e.entity == owner) handler(e)
        }

    fun onSneakTwice(handler: (PlayerToggleSneakEvent) -> Unit) {
        var lastSneakMs = 0L
        on(PlayerToggleSneakEvent::class) { e ->
            if (e.player != owner || !e.isSneaking) return@on
            val now = System.currentTimeMillis()
            if (now - lastSneakMs <= 400L) {
                lastSneakMs = 0L
                handler(e)
            } else {
                lastSneakMs = now
            }
        }
    }

    abstract fun register()

    protected fun unsubscribeAll() {
        tokens.forEach { context.unsubscribe(it) }
        tokens.clear()
    }
}
