package com.example.musicapp.data

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import com.example.musicapp.R

data class Song(
    val id: String,
    val title : String,
    val image : ByteArray?,
    val uri: Uri
)

fun getSongsFromRaw(context: Context): List<Song> {
    val songIds = listOf(
        R.raw.song_1,
        R.raw.song_2,
        R.raw.song_3
    )

    val songList = mutableListOf<Song>()

    for ((index, songId) in songIds.withIndex()) {
        val uri = Uri.parse("android.resource://${context.packageName}/$songId")
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(context, uri)

        val title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) ?: "Unknown Title"
        val image = retriever.embeddedPicture
        val id = index.toString()

        songList.add(Song(id, title, image, uri))
        retriever.release()
    }
    return songList
}

