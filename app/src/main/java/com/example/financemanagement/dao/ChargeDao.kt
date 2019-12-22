package com.example.financemanagement.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.financemanagement.domain.Charge

@Dao
interface ChargeDao {

    @Query("SELECT * From charges")
    fun getAll() : LiveData<List<Charge>>

    @Query("SELECT * From charges WHERE name LIKE :category")
    fun getChargesByCategory(category: String) : LiveData<List<Charge>>

    @Insert
    fun insert(charges : Charge)

}