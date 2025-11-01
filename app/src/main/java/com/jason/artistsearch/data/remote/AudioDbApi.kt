package com.jason.artistsearch.data.remote

import com.jason.artistsearch.data.model.AlbumResponse
import com.jason.artistsearch.data.model.ArtistResponse
import com.jason.artistsearch.data.model.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AudioDbApi {

    @GET("search.php")
    suspend fun searchArtist(
        @Query("s") artistName: String
    ): ArtistResponse

    @GET("searchalbum.php")
    suspend fun searchAlbumsByArtist(
        @Query("s") artistName: String
    ): AlbumResponse

    @GET("album.php")
    suspend fun getAlbumDetails(
        @Query("m") albumId: String
    ): AlbumResponse

    @GET("track.php")
    suspend fun getAlbumTracks(
        @Query("m") albumId: String
    ): TrackResponse
}

