package io.github.kdy05.abilityAPI.skill

import org.bukkit.entity.Player
import org.bukkit.event.Event
import kotlin.reflect.KClass

abstract class PassiveSkill(
    owner: Player,
    context: SkillContext,
) : SkillBase(owner, context) {

    override fun <T : Event> on(eventClass: KClass<T>, handler: (T) -> Unit) =
        super.on(eventClass) { e -> if (!context.isSilenced(owner)) handler(e) }

    override fun onStart() = register()
    override fun onStop() = unsubscribeAll()
}
