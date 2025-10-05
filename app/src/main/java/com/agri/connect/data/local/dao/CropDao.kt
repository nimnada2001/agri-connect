package com.agri.connect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.agri.connect.data.local.entities.CropGuide

@Dao
interface CropDao {
    @Query("SELECT * FROM crop_guides")
    suspend fun getAll(): List<CropGuide>

    @Insert
    suspend fun insertAll(items: List<CropGuide>)

    @Query("SELECT COUNT(*) FROM crop_guides")
    suspend fun count(): Int
}
