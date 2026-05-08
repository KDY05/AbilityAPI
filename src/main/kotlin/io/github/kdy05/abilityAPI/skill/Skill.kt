package io.github.kdy05.abilityAPI.skill

interface Skill {
    fun onStart()
    fun onStartWithCooldown() = onStart()
    fun onStop()
}
