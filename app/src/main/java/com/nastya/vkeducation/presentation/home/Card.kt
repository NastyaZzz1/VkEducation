package com.nastya.vkeducation.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.nastya.vkeducation.domain.CardApp
import com.nastya.vkeducation.domain.Category
import com.nastya.vkeducation.domain.getCategoryText
import com.nastya.vkeducation.ui.theme.VkEducationTheme

@Composable
fun Card(
    card: CardApp,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = card.iconUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.CenterVertically),
        )
        Column(
            modifier = Modifier.padding(start = 6.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = card.title,
                fontSize = 16.sp,
                lineHeight = 20.sp
            )
            Text(
                text = card.subtitle,
                fontSize = 12.sp,
                lineHeight = 14.sp,
            )
            Text(
                text = getCategoryText(card.category),
                fontSize = 8.sp,
                lineHeight = 12.sp
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val card = CardApp(
        id = "1",
        iconUrl = "https://static.rustore.ru/imgproxy/PTo8g-Giv9VHYo7_Rwxw_1wC07KtDM7eSJgAfMlv53s/preset:web_app_icon_160/plain/https://static.rustore.ru/3f3d7180-6eb9-45ad-8706-f467c6dcf82a@webp",
        title = "ВКонтакте: чаты, видео, музыка",
        subtitle = "Есть все",
        category = Category.COMMUNICATION
    )

    VkEducationTheme {
        Card(card = card, onClick = {})
    }
}