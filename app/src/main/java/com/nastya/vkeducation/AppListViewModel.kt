package com.nastya.vkeducation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel: ViewModel() {
    private val _state: MutableStateFlow<AppListState> = MutableStateFlow(AppListState.Loading)
    val state = _state.asStateFlow()

    private val _event = Channel<AppListEvent> (Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    init {
        loadCards()
    }

    private fun loadCards() {
        viewModelScope.launch {
            val result = runCatching {
                _state.value = AppListState.Loading
                delay(2000)
                _state.value = AppListState.Content(getCards())
            }
            if(result.isFailure) {
                _state.value = AppListState.Error
            }
        }
    }

    private fun getCards(): List<CardApp> = listOf(
        CardApp(
            iconUrl = "https://static.rustore.ru/imgproxy/PTo8g-Giv9VHYo7_Rwxw_1wC07KtDM7eSJgAfMlv53s/preset:web_app_icon_160/plain/https://static.rustore.ru/3f3d7180-6eb9-45ad-8706-f467c6dcf82a@webp",
            title = "ВКонтакте: чаты, видео, музыка",
            subtitle = "Есть все",
            category = Category.COMMUNICATION
        ),
        CardApp(
            iconUrl = "https://static.rustore.ru/imgproxy/ja2qyXWZR7HgjtrJ0bUQF49_kSfWRDqok49VAKTvfcI/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/1/16/a3/apk/2063687556/content/ICON/362ab8c5-fe30-4226-9ffc-94822bacdf9e.png@webp",
            title = "Car OUT Jam Puzzle",
            subtitle = "Лучшая головоломка",
            category = Category.GAME
        ),
        CardApp(
            iconUrl = "https://static.rustore.ru/imgproxy/UiXkrEm32dp-YPL_cLujbMoZ4eFuzwFJjJQF2k99mqE/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/1/29/eb/apk/481215/content/ICON/06061f54-6d4a-40ec-810d-e233680b6e8d.png@webp",
            title = "Wink: ТВ онлайн, Кино, Сериалы",
            subtitle = "Много фильмов",
            category = Category.APP
        )
    ).flatMap { card -> List(5) { card } }.shuffled()

    fun showRuStoreInfo() {
        viewModelScope.launch {
            _event.send(
                AppListEvent.ShowSnackbar("RuStore information")
            )
        }
    }
}