package io.github.kdy05.abilityAPI

import org.bukkit.plugin.Plugin

object AbilityAPI {
    private var _service: AbilityService? = null
    private var _plugin: Plugin? = null

    val service: AbilityService
        get() = _service ?: error("AbilityAPI is not initialized yet.")

    val plugin: Plugin
        get() = _plugin ?: error("AbilityAPI is not initialized yet.")

    fun setup(service: AbilityService, plugin: Plugin) {
        if (_service != null) error("AbilityAPI is already initialized.")
        _service = service
        _plugin = plugin
    }
}
