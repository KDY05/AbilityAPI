package io.github.kdy05.abilityAPI

object AbilityPlugin {
    private var _service: AbilityService? = null

    val service: AbilityService
        get() = _service ?: error("AbilityAPI가 초기화되지 않았습니다.")

    fun setup(service: AbilityService) {
        _service = service
    }
}
