package com.example.financemanagement.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.financemanagement.config.AppDatabase
import com.example.financemanagement.domain.db.Charge
import com.example.financemanagement.domain.db.ChargeCategory
import com.example.financemanagement.domain.ChargeCategoryAggregation
import com.example.financemanagement.repository.ChargeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FinanceManagementOverviewViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ChargeRepository

    init {
        val wordsDao = AppDatabase.getInstance(application)?.dataDao()
        repository = ChargeRepository(wordsDao)
    }

    fun getChargeCategoryAgregations() : LiveData<List<ChargeCategoryAggregation>>? {
        return repository.getChargesAgregated()
    }


    fun insertCharge (charge: Charge) : LiveData<Boolean> {
        val existCategory: MutableLiveData<Boolean> = MutableLiveData()

        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCharge(charge)

            if (repository.getCategoryByName(charge.categoryName!!).isNullOrEmpty()) {
                existCategory.postValue(false)
            } else {
                existCategory.postValue(true)
            }
        }

        return existCategory
    }

    fun insertCategory (chargeCategory: ChargeCategory) : LiveData<Boolean> {
        val existCategory: MutableLiveData<Boolean> = MutableLiveData()

        viewModelScope.launch(Dispatchers.IO) {
            if (repository.getCategoryByName(chargeCategory.name!!).isNullOrEmpty()) {
                repository.insertCategory(chargeCategory)
                existCategory.postValue(false)
            } else {
                existCategory.postValue(true)
            }
        }

        return existCategory
    }

    fun  getCharges () : LiveData<List<Charge>>? {
        return  repository.allCharges
    }
}