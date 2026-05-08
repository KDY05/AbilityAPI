package io.github.kdy05.abilityAPI

import io.github.kdy05.abilityAPI.ability.Ability
import org.bukkit.entity.Player
import kotlin.reflect.KClass

interface AbilityService {
    val registrar: AbilityRegistrar

    fun giveAbility(player: Player, type: KClass<out Ability>)
    fun removeAbility(player: Player, type: KClass<out Ability>)
    fun clearAbilities(player: Player)
    fun getAbilities(player: Player): List<Ability>
    fun getPrimaryAbility(player: Player): Ability?
    fun hasAbility(player: Player, type: KClass<out Ability>): Boolean
    fun getAbilityPlayers(type: KClass<out Ability>): List<Player>

    fun previewAbilities(players: List<Player>): Map<Player, KClass<out Ability>>
    fun reassignAbility(player: Player): KClass<out Ability>
    fun distributeAbilities()

    var damageGuard: Boolean

    fun silencePlayer(player: Player, durationTicks: Long)
    fun unsilencePlayer(player: Player)
    fun isSilenced(player: Player): Boolean
}
