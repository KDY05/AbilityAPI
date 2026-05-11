package io.github.kdy05.abilityAPI

object AbilityPlugin {
    private var _service: AbilityService? = null

    val service: AbilityService
        get() = _service ?: error("AbilityAPI is not Initialized yet.")

    fun setup(service: AbilityService) {
        if (_service != null) error("AbilityAPI is already initialized.")
        _service = service
    }
}
