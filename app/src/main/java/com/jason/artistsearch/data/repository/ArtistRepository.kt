package com.jason.artistsearch.data.repository

import com.jason.artistsearch.data.model.Album
import com.jason.artistsearch.data.model.Artist
import com.jason.artistsearch.data.model.Track
import com.jason.artistsearch.data.remote.AudioDbApi

class ArtistRepository(private val api: AudioDbApi) {

    suspend fun searchArtist(artistName: String): Result<Artist?> {
        return try {
            val response = api.searchArtist(artistName)
            Result.success(response.artists?.firstOrNull())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getArtistAlbums(artistName: String): Result<List<Album>> {
        return try {
            val response = api.searchAlbumsByArtist(artistName)
            Result.success(response.album ?: emptyList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAlbumDetails(albumId: String): Result<Album?> {
        return try {
            val response = api.getAlbumDetails(albumId)
            Result.success(response.album?.firstOrNull())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAlbumTracks(albumId: String): Result<List<Track>> {
        return try {
            val response = api.getAlbumTracks(albumId)
            Result.success(response.track ?: emptyList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

