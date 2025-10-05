package com.agri.connect.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.agri.connect.data.local.dao.CropDao
import com.agri.connect.data.local.dao.UserDao
import com.agri.connect.data.local.entities.CropGuide
import com.agri.connect.data.local.entities.User

@Database(entities = [CropGuide::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cropDao(): CropDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "agri.db")
                    .fallbackToDestructiveMigration()
                    .build()
                instance = db
                db
            }
        }
    }
}
