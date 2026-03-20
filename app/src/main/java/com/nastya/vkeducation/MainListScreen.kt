package com.nastya.vkeducation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
fun MainListScreen(
    onItemClick: (Int) -> Unit
) {
    Column(modifier = Modifier
        .background(colorResource(R.color.blue_main))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
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
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                .background(Color.White)
                .padding(start = 20.dp, top = 20.dp)
                .fillMaxWidth()
                .navigationBarsPadding()
        ) {
            items(15) { index ->
                CardItem(onClick = { onItemClick(index) })
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VkEducationTheme {
        MainListScreen(onItemClick = {})
    }
}