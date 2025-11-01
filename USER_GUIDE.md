# 🎵 Artist Search App - User Guide

## 📱 Cara Menggunakan Aplikasi

### Screen 1: Pilih Artist 🎤
```
┌─────────────────────────────────┐
│        Select Artist            │
├─────────────────────────────────┤
│  ┌──────┐  ┌──────┐            │
│  │ John │  │Radio │            │
│  │Mayer │  │ head │            │
│  │Blues │  │ Alt  │            │
│  └──────┘  └──────┘            │
│  ┌──────┐  ┌──────┐            │
│  │Billie│  │ Pink │            │
│  │Eilish│  │Floyd │            │
│  │ Pop  │  │ Rock │            │
│  └──────┘  └──────┘            │
│     ... 20 artists ...          │
└─────────────────────────────────┘
```
**Fitur:**
- Pilih dari 20 artis barat populer
- Gambar artist dari API
- Genre musik ditampilkan
- Tap card untuk melihat albums

---

### Screen 2: Lihat Albums 💿
```
┌─────────────────────────────────┐
│  ← John Mayer                   │
├─────────────────────────────────┤
│   ┌─────────────────┐           │
│   │                 │           │
│   │  Artist Photo   │           │
│   │                 │           │
│   └─────────────────┘           │
│        John Mayer               │
│           Indie                 │
├─────────────────────────────────┤
│          Albums                 │
│  ┌──────┐  ┌──────┐            │
│  │ Sob  │  │ New  │            │
│  │ Rock │  │Light │            │
│  │ 2021 │  │ 2018 │            │
│  └──────┘  └──────┘            │
│     ... all albums ...          │
└─────────────────────────────────┘
```
**Fitur:**
- Foto artist besar dari API
- Semua albums ditampilkan
- Cover art setiap album
- Tahun rilis & genre
- Tap album untuk lihat tracks

---

### Screen 3: Lihat Tracks 🎵
```
┌─────────────────────────────────┐
│  ← Sob Rock                     │
├─────────────────────────────────┤
│   ┌─────────────────┐           │
│   │                 │           │
│   │  Album Cover    │           │
│   │                 │           │
│   └─────────────────┘           │
│                                 │
│        Sob Rock                 │
│      2021 • Indie               │
│                                 │
│  [Album description from API]   │
│                                 │
│          Tracks                 │
│  ┌────────────────────┐         │
│  │ ① Last Train Home    3:07 │  │
│  └────────────────────┘         │
│  ┌────────────────────┐         │
│  │ ② Shouldn't Matter...3:56 │  │
│  └────────────────────┘         │
│  ┌────────────────────┐         │
│  │ ③ New Light         3:37 │  │
│  └────────────────────┘         │
│     ... all tracks ...          │
└─────────────────────────────────┘
```
**Fitur:**
- Cover album besar
- Info lengkap album
- Deskripsi album dari API
- **Semua tracks dari API:**
  - Nomor track (badge bulat)
  - Nama track
  - Durasi (MM:SS format)
- Scroll untuk lihat semua tracks

---

## 🎨 Tema & Desain

### Warna (Retro/Gruvbox)
- **Background**: Gelap (`#1A1A1A`)
- **Cards**: Medium gray (`#2A2A2A`)
- **Accent**: Golden yellow (`#D4AF37`)
- **Text**: Putih & abu-abu

### Efek Visual
- ✨ Drop shadow pada cards
- 🔲 Rounded corners (12dp)
- 📏 Elevated surfaces
- 🎯 Clean & modern layout

---

## 🎯 Fitur Utama

### ✅ Tidak Ada Hardcode
- Semua data dari AudioDB API
- Artist info dari API
- Albums dari API
- Tracks dari API

### ✅ Tidak Ada Search Bar
- Langsung pilih artist dari grid
- UI lebih clean
- Akses lebih cepat

### ✅ Artist Barat Saja
- Terbatas oleh AudioDB API
- 20 artis populer tersedia:
  - **Rock**: Queen, Pink Floyd, Nirvana, The Beatles
  - **Pop**: Billie Eilish, Taylor Swift, Ed Sheeran, Adele
  - **Alternative**: Radiohead, Coldplay, Muse, Foo Fighters
  - **Blues**: John Mayer
  - **Electronic**: Daft Punk
  - **Indie**: Arctic Monkeys, Oasis
  - **R&B**: The Weeknd, Bruno Mars
  - **Rock**: Imagine Dragons, David Bowie

---

## 🚀 Alur Penggunaan

```
START
  ↓
[Pilih Artist] → Lihat grid 20 artists
  ↓ (tap artist)
[Lihat Albums] → Lihat semua album artist
  ↓ (tap album)
[Lihat Tracks] → Lihat detail & tracks album
  ↓ (tap back)
[Kembali] → Back navigation tersedia
```

---

## 📊 Data dari API

Semua informasi berikut diambil dari AudioDB API:

### Artist Data:
- Nama artist
- Foto/thumbnail artist
- Genre musik
- Biografi (jika ada)

### Album Data:
- Nama album
- Cover art album
- Tahun rilis
- Genre
- Deskripsi album

### Track Data:
- Nama track
- Durasi track
- Nomor track (urutan)

---

## 🎨 Sesuai Desain

Aplikasi ini dibuat sesuai dengan screenshot yang diberikan:
- ✅ Layout artist dengan foto besar
- ✅ Grid albums dengan cover art
- ✅ Detail album dengan tracks list
- ✅ Warna tema retro/gruvbox
- ✅ Drop shadow & elevated cards
- ✅ Durasi tracks ditampilkan
- ✅ Artist barat saja

---

## 💡 Tips

1. **Scroll** untuk lihat semua albums/tracks
2. **Tap Back Button** untuk kembali
3. **Loading indicator** muncul saat fetch data
4. **Error message** ditampilkan jika ada masalah

---

**Selamat menjelajahi musik! 🎶**

