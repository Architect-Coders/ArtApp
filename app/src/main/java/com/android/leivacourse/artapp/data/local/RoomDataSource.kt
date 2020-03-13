package com.android.leivacourse.artapp.data.local

import com.android.leivacourse.artapp.data.LocalDataSource
import com.android.leivacourse.artapp.data.local.model.ArtDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: ArtsDatabase) : LocalDataSource {

    private val artDao = db.artsDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { artDao.artsCount() <= 0 }

    override suspend fun saveArt(art: ArtDetail) {
        withContext(Dispatchers.IO) { artDao.insert(art) }
    }

    override suspend fun getFavoritesArt(): List<ArtDetail> = withContext(Dispatchers.IO) {
        artDao.getAll()
    }

    override suspend fun removeArt(art: ArtDetail) {
        withContext(Dispatchers.IO) { artDao.remove(art) }
    }
}