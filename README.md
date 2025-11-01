# Artist Explorer App

Aplikasi Android untuk menjelajahi informasi artis musik menggunakan TheAudioDB API dengan arsitektur MVVM dan Jetpack Compose.

## Fitur Utama

- 🎵 Pencarian informasi artis musik
- 📀 Menampilkan daftar album dari artis
- 📝 Detail album dengan deskripsi dan daftar lagu
- 🎨 UI dengan tema retro/gruvbox (dark theme)
- ⚡ Async data loading dengan Coroutines
- 🏗️ Arsitektur MVVM (Model-View-ViewModel)

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM + Repository Pattern
- **Networking**: Retrofit 2 + Gson
- **Image Loading**: Coil
- **Async**: Kotlin Coroutines + Flow
- **Navigation**: Jetpack Navigation Compose

## Struktur Proyek

```
app/src/main/java/com/jason/artistsearch/
├── data/
│   ├── model/          # Data classes (Artist, Album, Track)
│   ├── remote/         # API service & Retrofit client
│   └── repository/     # Repository pattern
├── ui/
│   ├── view/           # Composable screens
│   ├── viewmodel/      # ViewModels
│   ├── route/          # Navigation
│   └── theme/          # Theme & styling
└── MainActivity.kt
```

## API Source

Menggunakan **TheAudioDB API** (Public API - Free)
- Base URL: `https://www.theaudiodb.com/api/v1/json/2/`
- Endpoints:
  - `search.php?s={artistName}` - Cari artis
  - `searchalbum.php?s={artistName}` - Cari album artis
  - `album.php?m={albumId}` - Detail album
  - `track.php?m={albumId}` - Track album

## Cara Menjalankan

1. Clone repository
2. Buka project di Android Studio
3. Sync Gradle
4. Run aplikasi di emulator atau device

```bash
./gradlew assembleDebug
```

## Arsitektur MVVM

### Model (Data Layer)
- `Artist.kt`, `Album.kt`, `Track.kt` - Data classes
- `AudioDbApi.kt` - API interface
- `ArtistRepository.kt` - Data repository

### ViewModel (Business Logic)
- `ArtistViewModel.kt` - Mengelola state artis & album
- `AlbumDetailViewModel.kt` - Mengelola state detail album

### View (UI Layer)
- `Soal2View.kt` - Halaman utama (Artist & Albums)
- `AlbumDetailView.kt` - Halaman detail album

## Desain UI

- **Tema**: Retro/Gruvbox dengan warna gelap
- **Warna Utama**: Gold (#D4AF37) & Dark Gray (#1A1A1A)
- **Typography**: Modern dengan hierarchy yang jelas
- **Cards**: Rounded corners dengan elevation

## Fitur yang Diimplementasikan

### ✅ Halaman Artis (Soal2View)
- [x] Nama artis di top bar
- [x] Gambar/banner artis
- [x] Genre artis
- [x] Biografi singkat (max 300 karakter)
- [x] Daftar album dalam LazyVerticalGrid (2 kolom)
- [x] Loading indicator saat memuat data
- [x] Error handling dengan pesan yang jelas
- [x] Auto-load artis "John Mayer" saat app dibuka

### ✅ Halaman Detail Album (AlbumDetailView)
- [x] Cover album
- [x] Nama album di top bar
- [x] Tahun rilis dan genre
- [x] Deskripsi album lengkap
- [x] Daftar track dalam LazyColumn
- [x] Durasi setiap track (format mm:ss)
- [x] Back button untuk kembali
- [x] Loading indicator dan error handling

### ✅ Arsitektur & Best Practices
- [x] MVVM Architecture
- [x] Repository Pattern untuk data access
- [x] Coroutines untuk async operations
- [x] StateFlow untuk state management
- [x] UiState sealed class (Idle, Loading, Success, Error)
- [x] Navigation dengan Jetpack Compose Navigation
- [x] Dependency Injection (manual via constructor)
- [x] Separation of concerns

### ✅ Technical Requirements
- [x] Retrofit untuk HTTP requests
- [x] Gson untuk JSON parsing
- [x] Coil untuk image loading
- [x] Material3 design
- [x] Internet permission di AndroidManifest
- [x] Error handling untuk network issues
- [x] No compilation errors or warnings

## Screenshots

### Halaman Artis
- Header dengan foto artis, nama, genre, dan biografi
- Grid layout untuk album covers
- Smooth scrolling dengan LazyVerticalGrid

### Halaman Detail Album
- Full screen album cover
- Informasi album lengkap
- List track dengan nomor urut dan durasi

## State Management

Menggunakan `StateFlow` untuk reactive state management:

```kotlin
sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
```

## Dependencies

```gradle
// Jetpack Compose
implementation "androidx.compose.ui:ui"
implementation "androidx.compose.material3:material3"
implementation "androidx.activity:activity-compose"

// Navigation
implementation "androidx.navigation:navigation-compose:2.7.4"

// ViewModel
implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2"

// Networking
implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.retrofit2:converter-gson:2.9.0"

// Coroutines
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1"

// Image Loading
implementation "io.coil-kt:coil-compose:2.4.0"
```

## Catatan Pengembangan

1. **Artist Selection**: Aplikasi default menggunakan "John Mayer" sebagai artis yang ditampilkan
2. **API Limitations**: TheAudioDB API free tier memiliki batasan request
3. **Error Messages**: Semua error ditampilkan dalam Bahasa Indonesia
4. **Theme Consistency**: Menggunakan color scheme yang konsisten di semua halaman
5. **Performance**: LazyVerticalGrid dan LazyColumn untuk efficient scrolling

## License

Educational project for learning purposes.

