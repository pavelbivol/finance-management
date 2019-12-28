package com.example.financemanagement.repository

import androidx.lifecycle.LiveData
import com.example.financemanagement.dao.ChargeDao
import com.example.financemanagement.domain.Charge
import com.example.financemanagement.domain.ChargeCategory
import com.example.financemanagement.domain.ChargeCategoryAggregation

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ChargeRepository(private val chargeDao: ChargeDao) {

    val allCharges: LiveData<List<Charge>> = chargeDao.getAllCharges()

    fun getChargesByCategory(charge: String) : LiveData<List<Charge>>{
        return chargeDao.getChargesByCategory(charge)
    }

    fun insertCharge(charge: Charge) {
        chargeDao.insertCharge(charge)
    }

    fun insertCategory(chargeCategory: ChargeCategory) {
        chargeDao.insertCategory(chargeCategory)
    }

    fun updateCategory(chargeCategory: ChargeCategory) {
        chargeDao.updateCategory(chargeCategory)
    }

    fun getAllCategories(): LiveData<List<ChargeCategory>> {
        return chargeDao.getAllCategories()
    }


    fun getCategoryByName(categoryName: String): List<ChargeCategory> {
        return chargeDao.getCategoryByName(categoryName)
    }

    fun getChargesAgregated(): LiveData<List<ChargeCategoryAggregation>> {
        return chargeDao.getChargesAgregated()
    }
}