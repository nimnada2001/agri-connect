package com.agri.connect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.agri.connect.data.local.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): User?

    @Insert
    suspend fun insert(user: User)
}
