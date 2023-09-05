package com.test.hiltapplication.data

import android.util.Log
import com.test.hiltapplication.data.local.ImageDatabase
import com.test.hiltapplication.data.local.ImageTable
import com.test.hiltapplication.data.remote.RemoteDataSource
import com.test.hiltapplication.model.BaseApiResponse
import com.test.hiltapplication.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.BufferedInputStream
import java.net.URL
import java.nio.ByteBuffer
import javax.inject.Inject


/**
 * Created by Chandan Jana on 21-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val imageDatabase: ImageDatabase
) : BaseApiResponse() {

    suspend fun getDog() = flow {
        emit(NetworkResult.loading(null))
        //if (Utils.hasInternetConnection()) {
            var rr = safeApiCall { remoteDataSource.getDog() }
            //var saveData = saveImage(rr.data.let { it?.let { it1 -> it1.message } ?: "" })
            //Log.d("TAGG", "saveData $saveData")
            emit(rr)
        /*}else{
            emit(NetworkResult.success(getImage()))
        }*/
    }.flowOn(Dispatchers.IO)
        .catch { e ->
            e.message?.let { NetworkResult.error(it, null) }?.let { emit(it) }
        }

    fun saveImage(url: String): ImageTable {
        Log.d("TAGG", "saveImage $url")
        //val byteArray = getBitmapFromUrlString(url, (4096 * 2) + (1024 * 1024))
        val name = getNameFromUrlString(url);
        var imageTable = ImageTable()
        imageTable.name = name
        //imageTable.data = byteArray
        imageTable.url = url
        Log.d("TAGG", "imageTable $imageTable")
        imageDatabase.imageDao().insertByReplacement(imageTable)
        return imageTable
    }

    private fun getBitmapFromUrlString(url: String, size: Int): ByteArray {

        /* fake the byte array allowing the size of the bytearray to be specified */
        val ba = ByteArray(size)
        var byte: Byte = 8
        for(i in 0 until (size)) {
            ba[i] = byte++.mod(byte)
        }
        return ba
        /* actual would be something like */
        /* warning image sunflower_from_silesia2.jpg is 2.7mb will likely cause issues */
        //return url(url).readbytes()
    }

    private fun getNameFromUrlString(url: String): String {
        val split = url.split("/")
        return split.get(split.size -1)
    }

    private fun getImage(): ImageTable {
        var minRow = imageDatabase.imageDao().findMinRow();
        var maxRow = imageDatabase.imageDao().findMaxRow();
        Log.d("TAGG", "minRow $minRow")
        Log.d("TAGG", "maxRow $maxRow")
        var randomRow = getRandomNumber(minRow, maxRow)
        Log.d("TAGG", "randomRow $randomRow")
        var imageTable = imageDatabase.imageDao().findByIds(randomRow)
        return imageTable
    }

    fun getRandomNumber(min: Int, max: Int): Int {
        return (Math.random() * (max - min) + min).toInt()
    }

}