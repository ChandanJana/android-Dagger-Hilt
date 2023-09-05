package com.test.hiltapplication.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Chandan Jana on 21-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */

@Entity(tableName = "image_table")
data class ImageTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var data: ByteArray? = null,

    @ColumnInfo(name = "imageName")
    var name: String? = null,

    @ColumnInfo(name = "imageUrl")
    var url: String? = null
){}
