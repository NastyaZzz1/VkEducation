package com.nastya.vkeducation.presentation.appDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.nastya.vkeducation.R
import com.nastya.vkeducation.ui.theme.VkEducationTheme

@Composable
internal fun Toolbar(
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onWishClick: () -> Unit,
    modifier: Modifier = Modifier,
    isInWishlist: Boolean
) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton (onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = null,
                tint = colorResource(R.color.blue_main),
            )
        }
        Row {
            IconButton(onClick = onWishClick) {
                Icon(
                    painter = painterResource(
                        id = if (isInWishlist)
                            R.drawable.icon_heart
                        else
                            R.drawable.icon_heart_empty
                    ),
                    contentDescription = if (isInWishlist) "Remove from wishlist" else "Add to wishlist",
                    tint = if (isInWishlist) Color.Red else Color.Gray
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    tint = colorResource(R.color.blue_main),
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VkEducationTheme {
        Toolbar(
            onBackClick = {},
            onShareClick = {},
            onWishClick = {},
            isInWishlist = true
        )
    }
}