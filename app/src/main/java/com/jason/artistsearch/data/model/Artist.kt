package com.jason.artistsearch.data.model

import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("artists")
    val artists: List<Artist>?
)

data class Artist(
    @SerializedName("idArtist")
    val idArtist: String?,
    @SerializedName("strArtist")
    val strArtist: String?,
    @SerializedName("strArtistThumb")
    val strArtistThumb: String?,
    @SerializedName("strArtistBanner")
    val strArtistBanner: String?,
    @SerializedName("strGenre")
    val strGenre: String?,
    @SerializedName("strBiographyEN")
    val strBiographyEN: String?
)

