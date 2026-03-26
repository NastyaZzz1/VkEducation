package com.nastya.vkeducation.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nastya.vkeducation.R
import com.nastya.vkeducation.ui.theme.VkEducationTheme

@Composable
fun AppListScreen(
    onItemClick: (Int) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val viewModel = hiltViewModel<AppListViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when(event) {
                is AppListEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

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
                    modifier = Modifier
                        .clickable { viewModel.showRuStoreInfo() },
                    painter = painterResource(R.drawable.logo),
                    contentDescription = stringResource(R.string.logo),
                    tint = Color.Unspecified,
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
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    )
    { innerPadding ->
        when(val currentState = state.value) {
            is AppListState.Content -> {
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
                    items(currentState.appCards.size) { index ->
                        Card(
                            card = currentState.appCards[index],
                            onClick = { onItemClick(index) }
                        )
                    }
                }
            }
            AppListState.Error -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Ошибка!")
                }
            }
            AppListState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
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