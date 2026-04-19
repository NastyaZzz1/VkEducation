package com.nastya.vkeducation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nastya.vkeducation.presentation.appDetails.AppDetailsScreen
import com.nastya.vkeducation.presentation.home.AppListScreen

@Preview
@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main_list"
    ) {
        composable("main_list") {
            AppListScreen(
                onItemClick = { itemId ->
                    navController.navigate("detail_screen/$itemId")
                }
            )
        }

        composable(
            "detail_screen/{appId}",
            arguments = listOf(navArgument("appId") { type = NavType.StringType })
        ) {
            AppDetailsScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}