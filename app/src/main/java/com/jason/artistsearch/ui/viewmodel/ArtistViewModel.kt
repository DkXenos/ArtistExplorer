package com.jason.artistsearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jason.artistsearch.data.model.Album
import com.jason.artistsearch.data.model.Artist
import com.jason.artistsearch.data.remote.RetrofitClient
import com.jason.artistsearch.data.repository.ArtistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

class ArtistViewModel : ViewModel() {
    private val repository = ArtistRepository(RetrofitClient.api)

    private val _artistState = MutableStateFlow<UiState<Artist?>>(UiState.Idle)
    val artistState: StateFlow<UiState<Artist?>> = _artistState.asStateFlow()

    private val _albumsState = MutableStateFlow<UiState<List<Album>>>(UiState.Idle)
    val albumsState: StateFlow<UiState<List<Album>>> = _albumsState.asStateFlow()

    fun searchArtist(artistName: String) {
        viewModelScope.launch {
            _artistState.value = UiState.Loading
            _albumsState.value = UiState.Loading

            repository.searchArtist(artistName)
                .onSuccess { artist ->
                    _artistState.value = UiState.Success(artist)
                    // Load albums after artist is loaded
                    if (artist != null) {
                        loadArtistAlbums(artistName)
                    } else {
                        _albumsState.value = UiState.Error("Artis tidak ditemukan")
                    }
                }
                .onFailure { error ->
                    _artistState.value = UiState.Error(error.message ?: "Terjadi kesalahan")
                    _albumsState.value = UiState.Error(error.message ?: "Terjadi kesalahan")
                }
        }
    }

    private fun loadArtistAlbums(artistName: String) {
        viewModelScope.launch {
            repository.getArtistAlbums(artistName)
                .onSuccess { albums ->
                    _albumsState.value = UiState.Success(albums)
                }
                .onFailure { error ->
                    _albumsState.value = UiState.Error(error.message ?: "Gagal memuat album")
                }
        }
    }
}

