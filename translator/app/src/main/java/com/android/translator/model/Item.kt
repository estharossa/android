package com.android.translator.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val lang: String,
    val text: String
)