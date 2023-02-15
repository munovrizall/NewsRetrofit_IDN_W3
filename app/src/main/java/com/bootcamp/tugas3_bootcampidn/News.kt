package com.bootcamp.tugas3_bootcampidn

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val imgNews: Int,
    val titleNews: String,
    val author: String,
    val dateNews: String,
    val description: String
) : Parcelable

val newsList: List<News> = listOf(
    News(
        imgNews = R.drawable.kotlin_android,
        titleNews = "Bootcamp Android IDN",
        author = "Ihsan",
        dateNews = "23 Januari 2023",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
    ),
    News(
        imgNews = R.drawable.flutter_android,
        titleNews = "Bootcamp Flutter IDN",
        author = "Miko",
        dateNews = "3 Maret 2023",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
    ),
)