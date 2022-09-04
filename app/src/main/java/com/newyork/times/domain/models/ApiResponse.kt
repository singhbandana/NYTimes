package com.newyork.times.domain.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val results: List<NewsResponse>,
    val num_results: Int
)

data class NewsResponse(
    val uri: String,
    val title: String,
    val byline: String?,
    val published_date: String,
    val section: String,
    val url: String,
    @SerializedName("abstract")
    val abstractData: String,
    val media: List<Media>,
    val id: String
)

data class Media(
    val type: String,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata")
    val media_metadata: List<MediaMetaData>
)

data class MediaMetaData(
    val url: String,
    val format: String,
    val height: Int,
    val width: Int
)

fun ApiResponse.toNewsList(): List<NewsEntity> {
    val list = mutableListOf<NewsEntity>()

    this.results.forEach {
        list.add(
            NewsEntity(
                title = it.title,
                byLine = it.byline,
                publishedDate = it.published_date,
                section = it.section,
                url = it.url,
                abstractData = it.abstractData,
                id = it.id,
                imageUrl = it.media[0].media_metadata.lastOrNull()?.url,
                imageCaption = it.media[0].caption
            )
        )
    }
    return list
}