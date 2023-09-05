package com.test.hiltapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Chandan Jana on 21-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertByReplacement(image: ImageTable)

    @Query("SELECT * FROM image_table")
    fun getAll(): List<ImageTable>

    @Query("SELECT * FROM image_table WHERE id = :imageTestId")
    fun findByIds(imageTestId: Int): ImageTable

    @Query("SELECT id FROM image_table ORDER BY id DESC LIMIT 1")
    fun findMinRow(): Int

    @Query("SELECT id FROM image_table ORDER BY id ASC LIMIT 1")
    fun findMaxRow(): Int

    @Delete
    fun delete(imageTest: ImageTable)
}