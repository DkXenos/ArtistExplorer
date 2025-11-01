package com.jason.artistsearch.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jason.artistsearch.ui.view.AlbumDetailView
import com.jason.artistsearch.ui.view.ArtistSelectionView
import com.jason.artistsearch.ui.view.Soal2View

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ArtistSelection.route
    ) {
        // Artist Selection Screen
        composable(Screen.ArtistSelection.route) {
            ArtistSelectionView(
                onArtistSelected = { artistName ->
                    navController.navigate(Screen.ArtistDetail.createRoute(artistName))
                }
            )
        }

        // Artist Detail Screen (shows albums)
        composable(
            route = Screen.ArtistDetail.route,
            arguments = listOf(
                navArgument("artistName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val artistName = backStackEntry.arguments?.getString("artistName") ?: ""
            Soal2View(
                artistName = artistName,
                onAlbumClick = { albumId, artistName ->
                    navController.navigate(Screen.AlbumDetail.createRoute(albumId, artistName))
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        // Album Detail Screen (shows tracks)
        composable(
            route = Screen.AlbumDetail.route,
            arguments = listOf(
                navArgument("albumId") { type = NavType.StringType },
                navArgument("artistName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId") ?: ""
            val artistName = backStackEntry.arguments?.getString("artistName") ?: ""
            AlbumDetailView(
                albumId = albumId,
                artistName = artistName,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

