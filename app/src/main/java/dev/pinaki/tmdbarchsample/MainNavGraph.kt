package dev.pinaki.tmdbarchsample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.pinaki.tmdbarchsample.navigation.TmdbDestinations
import dev.pinaki.tmdbarchsample.navigation.TmdbDestinations.TRENDING_ROUTE
import dev.pinaki.trending.TrendingScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = TRENDING_ROUTE
    ) {
        composable(
            route = TRENDING_ROUTE
        ) {
            TrendingScreen(
                modifier = modifier
            )
        }
    }
}