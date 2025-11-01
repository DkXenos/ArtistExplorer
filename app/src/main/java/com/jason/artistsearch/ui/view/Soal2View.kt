package com.jason.artistsearch.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.jason.artistsearch.data.model.Album
import com.jason.artistsearch.ui.viewmodel.ArtistViewModel
import com.jason.artistsearch.ui.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Soal2View(
    artistName: String,
    viewModel: ArtistViewModel = viewModel(),
    onAlbumClick: (String, String) -> Unit = { _, _ -> },
    onBackClick: () -> Unit = {}
) {
    val artistState by viewModel.artistState.collectAsState()
    val albumsState by viewModel.albumsState.collectAsState()

    LaunchedEffect(artistName) {
        viewModel.searchArtist(artistName)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = when (val state = artistState) {
                            is UiState.Success -> state.data?.strArtist ?: "Artist Explorer"
                            else -> "Artist Explorer"
                        },
                        fontSize = 18.sp,
                        color = Color(0xFFD4AF37)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFFD4AF37)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF1A1A1A)
                )
            )
        },
        containerColor = Color(0xFF1A1A1A)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (val state = artistState) {
                is UiState.Loading -> {
                    LoadingView()
                }
                is UiState.Error -> {
                    ErrorView(state.message)
                }
                is UiState.Success -> {
                    state.data?.let { artist ->
                        ArtistHeader(artist = artist)
                    }
                }
                is UiState.Idle -> {}
            }

            Text(
                text = "Albums",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFD4AF37),
                modifier = Modifier.padding(16.dp)
            )

            when (val state = albumsState) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFFD4AF37))
                    }
                }
                is UiState.Error -> {
                    ErrorView(state.message)
                }
                is UiState.Success -> {
                    AlbumGrid(
                        albums = state.data,
                        onAlbumClick = onAlbumClick,
                        artistState = artistState
                    )
                }
                is UiState.Idle -> {}
            }
        }
    }
}

@Composable
fun ArtistHeader(artist: com.jason.artistsearch.data.model.Artist) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = artist.strArtistThumb,
            contentDescription = artist.strArtist,
            modifier = Modifier
                .size(280.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = artist.strArtist ?: "",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = artist.strGenre ?: "",
            fontSize = 14.sp,
            color = Color(0xFFD4AF37),
            modifier = Modifier.padding(top = 4.dp)
        )

        artist.strBiographyEN?.let { bio ->
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = bio.take(300) + if (bio.length > 300) "..." else "",
                fontSize = 14.sp,
                color = Color(0xFFB0B0B0),
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun AlbumGrid(
    albums: List<Album>,
    onAlbumClick: (String, String) -> Unit,
    artistState: UiState<com.jason.artistsearch.data.model.Artist?>
) {
    val artistName = when (artistState) {
        is UiState.Success -> artistState.data?.strArtist ?: ""
        else -> ""
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(albums) { album ->
            AlbumCard(
                album = album,
                onClick = {
                    album.idAlbum?.let { id ->
                        onAlbumClick(id, artistName)
                    }
                }
            )
        }
    }
}

@Composable
fun AlbumCard(album: Album, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A2A)
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = album.strAlbumThumb,
                contentDescription = album.strAlbum,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = album.strAlbum ?: "",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "${album.intYearReleased ?: ""} • ${album.strGenre ?: ""}",
                    fontSize = 12.sp,
                    color = Color(0xFFD4AF37),
                    modifier = Modifier.padding(top = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = Color(0xFFD4AF37))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Loading...",
                color = Color(0xFFD4AF37),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ErrorView(@Suppress("UNUSED_PARAMETER") message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Error",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD4AF37)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Error: Tidak ada koneksi internet",
                fontSize = 14.sp,
                color = Color(0xFFFF6B6B),
                textAlign = TextAlign.Center
            )
        }
    }
}