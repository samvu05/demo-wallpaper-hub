package com.sam.demofirewallpaper

import com.google.firebase.firestore.DocumentId

data class WallpapersModel(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val thumbnail: String = ""
)