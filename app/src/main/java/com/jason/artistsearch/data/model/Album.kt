package com.jason.artistsearch.data.model

import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("album")
    val album: List<Album>?
)

data class Album(
    @SerializedName("idAlbum")
    val idAlbum: String?,
    @SerializedName("idArtist")
    val idArtist: String?,
    @SerializedName("strAlbum")
    val strAlbum: String?,
    @SerializedName("strAlbumThumb")
    val strAlbumThumb: String?,
    @SerializedName("intYearReleased")
    val intYearReleased: String?,
    @SerializedName("strGenre")
    val strGenre: String?,
    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String?
)

