package com.jason.artistsearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jason.artistsearch.data.model.Album
import com.jason.artistsearch.data.model.Track
import com.jason.artistsearch.data.remote.RetrofitClient
import com.jason.artistsearch.data.repository.ArtistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlbumDetailViewModel : ViewModel() {
    private val repository = ArtistRepository(RetrofitClient.api)

    private val _albumState = MutableStateFlow<UiState<Album?>>(UiState.Idle)
    val albumState: StateFlow<UiState<Album?>> = _albumState.asStateFlow()

    private val _tracksState = MutableStateFlow<UiState<List<Track>>>(UiState.Idle)
    val tracksState: StateFlow<UiState<List<Track>>> = _tracksState.asStateFlow()

    fun loadAlbumDetails(albumId: String) {
        viewModelScope.launch {
            _albumState.value = UiState.Loading
            _tracksState.value = UiState.Loading

            // Load album details
            repository.getAlbumDetails(albumId)
                .onSuccess { album ->
                    _albumState.value = UiState.Success(album)
                    // Load tracks for this album
                    loadAlbumTracks(albumId)
                }
                .onFailure { error ->
                    _albumState.value = UiState.Error(error.message ?: "Failed to load album")
                    _tracksState.value = UiState.Error(error.message ?: "Failed to load tracks")
                }
        }
    }

    private fun loadAlbumTracks(albumId: String) {
        viewModelScope.launch {
            repository.getAlbumTracks(albumId)
                .onSuccess { tracks ->
                    _tracksState.value = UiState.Success(tracks)
                }
                .onFailure { error ->
                    _tracksState.value = UiState.Error(error.message ?: "Failed to load tracks")
                }
        }
    }
}

