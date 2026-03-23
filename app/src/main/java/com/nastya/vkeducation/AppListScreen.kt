package com.nastya.vkeducation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nastya.vkeducation.ui.theme.VkEducationTheme

@Composable
fun AppListScreen(
    onItemClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(10.dp, 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = stringResource(R.string.logo),
                    tint = Color.Unspecified
                )
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.change_display_item),
                    contentDescription = stringResource(R.string.logo),
                    tint = Color.Unspecified
                )
            }
        },
        containerColor = colorResource(R.color.blue_main),
    )
    { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                .background(Color.White),
            contentPadding = PaddingValues(
                top = 20.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = innerPadding.calculateBottomPadding()
            )
        ) {
            items(cards.size) { index ->
                Card(
                    card = cards[index],
                    onClick = { onItemClick(index) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VkEducationTheme {
        AppListScreen(onItemClick = {})
    }
}

private val cards: List<CardApp> = listOf(
    *Array(5) {
        CardApp(
            iconUrl = "https://static.rustore.ru/imgproxy/PTo8g-Giv9VHYo7_Rwxw_1wC07KtDM7eSJgAfMlv53s/preset:web_app_icon_160/plain/https://static.rustore.ru/3f3d7180-6eb9-45ad-8706-f467c6dcf82a@webp",
            title = "ВКонтакте: чаты, видео, музыка",
            subtitle = "Есть все",
            category = Category.COMMUNICATION
        )
    },
    *Array(5) {
        CardApp(
            iconUrl = "https://static.rustore.ru/imgproxy/ja2qyXWZR7HgjtrJ0bUQF49_kSfWRDqok49VAKTvfcI/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/1/16/a3/apk/2063687556/content/ICON/362ab8c5-fe30-4226-9ffc-94822bacdf9e.png@webp",
            title = "Car OUT Jam Puzzle",
            subtitle = "Лучшая головоломка",
            category = Category.GAME
        )
    },
    *Array(5) {
        CardApp(
            iconUrl = "https://static.rustore.ru/imgproxy/UiXkrEm32dp-YPL_cLujbMoZ4eFuzwFJjJQF2k99mqE/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/1/29/eb/apk/481215/content/ICON/06061f54-6d4a-40ec-810d-e233680b6e8d.png@webp",
            title = "Wink: ТВ онлайн, Кино, Сериалы",
            subtitle = "Много фильмов",
            category = Category.APP
        )
    }
).shuffled()
