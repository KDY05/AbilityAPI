package io.github.kdy05.abilityAPI.ability

import io.github.kdy05.abilityAPI.rank.Rank

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class AbilityMeta(
    val name: String,
    val rank: Rank,
    val guide: Array<String> = [],
    val minimumPlayers: Int = 0,
    val isDeathExempt: Boolean = false,
    val isInfoPrimary: Boolean = true,
)
