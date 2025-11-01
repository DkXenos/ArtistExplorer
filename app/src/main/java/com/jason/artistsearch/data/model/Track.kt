package com.jason.artistsearch.data.model

import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("track")
    val track: List<Track>?
)

data class Track(
    @SerializedName("idTrack")
    val idTrack: String?,
    @SerializedName("strTrack")
    val strTrack: String?,
    @SerializedName("intDuration")
    val intDuration: String?
)

