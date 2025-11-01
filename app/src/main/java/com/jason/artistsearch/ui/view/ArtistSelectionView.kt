package com.jason.artistsearch.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ArtistItem(
    val name: String,
    val genre: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistSelectionView(
    onArtistSelected: (String) -> Unit
) {
    // List of popular western artists available in AudioDB API
    val artists = listOf(
        ArtistItem("John Mayer", "Blues Rock", "https://www.theaudiodb.com/images/media/artist/thumb/uxrqxy1347913147.jpg"),
        ArtistItem("Radiohead", "Alternative Rock", "https://www.theaudiodb.com/images/media/artist/thumb/vvwqxu1347983980.jpg"),
        ArtistItem("Billie Eilish", "Pop", "https://www.theaudiodb.com/images/media/artist/thumb/qqwwuu1548338768.jpg"),
        ArtistItem("Pink Floyd", "Progressive Rock", "https://www.theaudiodb.com/images/media/artist/thumb/tquwtv1347890156.jpg"),
        ArtistItem("Coldplay", "Alternative Rock", "https://www.theaudiodb.com/images/media/artist/thumb/vpqpxu1434031010.jpg"),
        ArtistItem("Adele", "Soul/Pop", "https://www.theaudiodb.com/images/media/artist/thumb/uxpqxy1347913147.jpg"),
        ArtistItem("Taylor Swift", "Pop/Country", "https://www.theaudiodb.com/images/media/artist/thumb/taylor-swift-5318c0c0c6e85.jpg"),
        ArtistItem("Ed Sheeran", "Pop", "https://www.theaudiodb.com/images/media/artist/thumb/ed-sheeran-52f5ad89e53ae.jpg"),
        ArtistItem("The Beatles", "Rock", "https://www.theaudiodb.com/images/media/artist/thumb/uxrqxy1347913147.jpg"),
        ArtistItem("Arctic Monkeys", "Indie Rock", "https://www.theaudiodb.com/images/media/artist/thumb/arctic-monkeys-5363a5c90d96e.jpg"),
        ArtistItem("Queen", "Rock", "https://www.theaudiodb.com/images/media/artist/thumb/wpvwys1349714918.jpg"),
        ArtistItem("Daft Punk", "Electronic", "https://www.theaudiodb.com/images/media/artist/thumb/daft-punk-4fa0d5b7db7a1.jpg"),
        ArtistItem("Muse", "Alternative Rock", "https://www.theaudiodb.com/images/media/artist/thumb/muse-5054d1a7a7c8a.jpg"),
        ArtistItem("Nirvana", "Grunge", "https://www.theaudiodb.com/images/media/artist/thumb/tqspvv1344720227.jpg"),
        ArtistItem("Oasis", "Britpop", "https://www.theaudiodb.com/images/media/artist/thumb/oasis-4fa4a5b5e5c71.jpg"),
        ArtistItem("The Weeknd", "R&B/Pop", "https://www.theaudiodb.com/images/media/artist/thumb/the-weeknd-59b9c0d7a4f1a.jpg"),
        ArtistItem("Bruno Mars", "Pop/R&B", "https://www.theaudiodb.com/images/media/artist/thumb/bruno-mars-4fa3f4b7bc5c1.jpg"),
        ArtistItem("Imagine Dragons", "Alternative Rock", "https://www.theaudiodb.com/images/media/artist/thumb/imagine-dragons-53b5e5c9e5c8a.jpg"),
        ArtistItem("Foo Fighters", "Alternative Rock", "https://www.theaudiodb.com/images/media/artist/thumb/foo-fighters-4fa3b5c7d5c71.jpg"),
        ArtistItem("David Bowie", "Rock", "https://www.theaudiodb.com/images/media/artist/thumb/uuqxwy1421584979.jpg")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Select Artist",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFD4AF37)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF1A1A1A)
                )
            )
        },
        containerColor = Color(0xFF1A1A1A)
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(artists) { artist ->
                ArtistCard(
                    artist = artist,
                    onClick = { onArtistSelected(artist.name) }
                )
            }
        }
    }
}

@Composable
fun ArtistCard(
    artist: ArtistItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.75f)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2A2A)
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = artist.imageUrl,
                contentDescription = artist.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = artist.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = artist.genre,
                    fontSize = 12.sp,
                    color = Color(0xFFD4AF37),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

