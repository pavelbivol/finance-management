package com.example.financemanagement.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financemanagement.domain.Charge
import com.example.financemanagement.domain.ChargeCategory
import com.example.financemanagement.domain.ChargeCategoryAggregation

@Dao
interface ChargeDao {

    @Query("SELECT * From charges")
    fun getAllCharges() : LiveData<List<Charge>>

    @Query("SELECT * From charges WHERE category_name LIKE :category")
    fun getChargesByCategory(category: String) : LiveData<List<Charge>>

    @Insert
    fun insertCharge(charges : Charge)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertCategory(chargeCategory: ChargeCategory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateCategory(chargeCategory: ChargeCategory)

    @Query("SELECT * From chargeCategories")
    fun getAllCategories() : LiveData<List<ChargeCategory>>

    @Query("SELECT * From chargeCategories WHERE name LIKE :categoryName")
    fun getCategoryByName(categoryName: String) : List<ChargeCategory>



    /*@Query("SELECT category_name as categoryName, SUM (charge_amount) as totalAmount FROM charges GROUP BY category_name")
    fun getChargesAgregated() : LiveData<List<ChargeCategoryAggregation>>*/

    @Query("SELECT category_name as categoryName, expected_Amount as expectedAmount, SUM (charge_amount) as totalAmount FROM charges INNER JOIN chargeCategories ON charges.category_name = chargeCategories.name GROUP BY category_name")
    fun getChargesAgregated() : LiveData<List<ChargeCategoryAggregation>>

}