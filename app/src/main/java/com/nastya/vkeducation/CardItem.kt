package com.nastya.vkeducation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardItem(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier
                .size(50.dp)
                .clickable { onClick() }
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.CenterVertically),
            tint = Color.Unspecified,
            painter = painterResource(R.drawable.icon_item_vk),
            contentDescription = stringResource(R.string.logo)
        )
        Column(
            modifier = Modifier.padding(start = 6.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = stringResource(R.string.title),
                fontSize = 16.sp,
                lineHeight = 20.sp
            )
            Text(
                text = stringResource(R.string.subtitle),
                fontSize = 12.sp,
                lineHeight = 14.sp,
            )
            Text(
                text = stringResource(R.string.category),
                fontSize = 8.sp,
                lineHeight = 12.sp
            )
        }
    }
}