package com.jason.artistsearch.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import com.jason.artistsearch.data.model.Track
import com.jason.artistsearch.ui.viewmodel.AlbumDetailViewModel
import com.jason.artistsearch.ui.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailView(
    albumId: String,
    artistName: String,
    onBackClick: () -> Unit,
    viewModel: AlbumDetailViewModel = viewModel()
) {
    val albumState by viewModel.albumState.collectAsState()
    val tracksState by viewModel.tracksState.collectAsState()

    LaunchedEffect(albumId) {
        viewModel.loadAlbumDetails(albumId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    when (val state = albumState) {
                        is UiState.Success -> {
                            Text(
                                text = state.data?.strAlbum ?: "Album Details",
                                fontSize = 18.sp,
                                color = Color(0xFFD4AF37),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        else -> {
                            Text(
                                text = "Album Details",
                                fontSize = 18.sp,
                                color = Color(0xFFD4AF37)
                            )
                        }
                    }
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
        when (val state = albumState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFFD4AF37))
                }
            }
            is UiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        color = Color(0xFFFF6B6B),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }
            }
            is UiState.Success -> {
                state.data?.let { album ->
                    AlbumDetailContent(
                        album = album,
                        tracksState = tracksState,
                        modifier = Modifier.padding(padding)
                    )
                }
            }
            is UiState.Idle -> {}
        }
    }
}

@Composable
fun AlbumDetailContent(
    album: Album,
    tracksState: UiState<List<Track>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Album cover
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .aspectRatio(1f),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    AsyncImage(
                        model = album.strAlbumThumb,
                        contentDescription = album.strAlbum,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Album title
                Text(
                    text = album.strAlbum ?: "",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Album info
                Text(
                    text = "${album.intYearReleased ?: ""} • ${album.strGenre ?: ""}",
                    fontSize = 14.sp,
                    color = Color(0xFFD4AF37)
                )

                // Album description
                album.strDescriptionEN?.let { description ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF2A2A2A)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = description,
                            fontSize = 14.sp,
                            color = Color(0xFFB0B0B0),
                            textAlign = TextAlign.Justify,
                            lineHeight = 20.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Tracks section header
                Text(
                    text = "Tracks",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFD4AF37),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
            }
        }

        // Tracks list
        when (tracksState) {
            is UiState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFFD4AF37))
                    }
                }
            }
            is UiState.Error -> {
                item {
                    Text(
                        text = tracksState.message,
                        color = Color(0xFFFF6B6B),
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            is UiState.Success -> {
                if (tracksState.data.isEmpty()) {
                    item {
                        Text(
                            text = "No tracks available",
                            color = Color(0xFFB0B0B0),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    itemsIndexed(tracksState.data) { index, track ->
                        TrackItem(
                            track = track,
                            trackNumber = index + 1
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            is UiState.Idle -> {}
        }
    }
}

@Composable
fun TrackItem(
    track: Track,
    trackNumber: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A2A)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Track number
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD4AF37)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = trackNumber.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Track name
            Text(
                text = track.strTrack ?: "Unknown Track",
                fontSize = 15.sp,
                color = Color.White,
                modifier = Modifier.weight(1f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Track duration
            track.intDuration?.let { duration ->
                val durationInSeconds = duration.toLongOrNull()?.div(1000) ?: 0
                val minutes = durationInSeconds / 60
                val seconds = durationInSeconds % 60

                Text(
                    text = String.format("%d:%02d", minutes, seconds),
                    fontSize = 14.sp,
                    color = Color(0xFFD4AF37),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

