package com.example.financemanagement.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financemanagement.domain.db.CategoryIcon
import com.example.financemanagement.domain.db.Charge
import com.example.financemanagement.domain.db.ChargeCategory
import com.example.financemanagement.domain.ChargeCategoryAggregation
import com.example.financemanagement.domain.ChargeIconsAggregation

@Dao
interface ChargeDao {

    @Insert
    fun insertAll(dataEntities: Array<CategoryIcon>?)

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

    @Query("SELECT * From chargeCategories WHERE name LIKE :categoryName")
    fun getLiveDataCategoryByName(categoryName: String) : LiveData<List<ChargeCategory>>

    @Query("SELECT category_name as categoryName, expected_Amount as expectedAmount, SUM (charge_amount) as totalAmount FROM charges INNER JOIN chargeCategories ON charges.category_name = chargeCategories.name GROUP BY category_name")
    fun getChargesAgregated() : LiveData<List<ChargeCategoryAggregation>>

    @Query("DELETE FROM charges WHERE chargeId = :chargeId")
    fun deleteChargebyId(chargeId: Int)

    @Query("SELECT charges.charge_amount as chargeAmount, charges.date, charges.charge_description as chargeDescription, categoryIcons.backgroundColor, categoryIcons.drawableId, categoryIcons.iconColor FROM charges INNER JOIN chargeCategories on charges.category_name = chargeCategories.name INNER JOIN categoryIcons  on chargeCategories.iconTitle = categoryIcons.iconTitle")
    fun getChargesAndIcons() : LiveData<List<ChargeIconsAggregation>>

}