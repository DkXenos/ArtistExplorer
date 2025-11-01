package com.jason.artistsearch.ui.route

sealed class Screen(val route: String) {
    object ArtistSelection : Screen("artist_selection")
    object ArtistDetail : Screen("artist_detail/{artistName}") {
        fun createRoute(artistName: String) = "artist_detail/$artistName"
    }
    object AlbumDetail : Screen("album_detail/{albumId}/{artistName}") {
        fun createRoute(albumId: String, artistName: String) = "album_detail/$albumId/$artistName"
    }
}

