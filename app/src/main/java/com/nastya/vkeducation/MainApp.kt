package com.nastya.vkeducation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Preview
@Composable
fun MainApp() {
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .systemBarsPadding()
    ) {
        NavHost(
            navController = navController,
            startDestination = "main_list"
        ) {
            composable("main_list") {
                MainListScreen(
                    onItemClick = { itemId ->
                        navController.navigate("detail_screen/$itemId")
                    }
                )
            }

            composable(
                "detail_screen/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
                DetailScreen(onBackClick = { navController.navigateUp() })
            }
        }
    }
}