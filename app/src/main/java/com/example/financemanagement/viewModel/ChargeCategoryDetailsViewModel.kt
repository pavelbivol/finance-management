package com.example.financemanagement.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.financemanagement.config.AppDatabase
import com.example.financemanagement.domain.db.Charge
import com.example.financemanagement.domain.db.ChargeCategory
import com.example.financemanagement.domain.ChargeCategoryAggregation
import com.example.financemanagement.domain.ChargeIconsAggregation
import com.example.financemanagement.repository.ChargeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChargeCategoryDetailsViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: ChargeRepository


    init {
        val wordsDao = AppDatabase.getInstance(application)?.dataDao()
        repository = ChargeRepository(wordsDao)
    }


    fun  getCharges () : LiveData<List<Charge>>? {
        return  repository.allCharges
    }

    fun  getChargesByCategory (category: String) : LiveData<List<Charge>>? {
        return  repository.getChargesByCategory(category)
    }

    fun deleteItem(chargeId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeCharge(chargeId)
        }
    }

    fun getCategoryByName(categoryname: String): LiveData<List<ChargeCategory>> ?{
        return repository.getLiveDataCategoryByName(categoryname)
    }

    fun getChargeCategoryAgregations() : LiveData<List<ChargeCategoryAggregation>> ?{
        return repository.getChargesAgregated()
    }


    fun getChargesAndIcons() : LiveData<List<ChargeIconsAggregation>> ?{
        return repository.getChargesAndIcons()
    }

}