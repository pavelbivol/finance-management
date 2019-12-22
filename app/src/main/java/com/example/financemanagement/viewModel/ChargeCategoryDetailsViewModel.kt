package com.example.financemanagement.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.financemanagement.config.AppDatabase
import com.example.financemanagement.domain.Charge
import com.example.financemanagement.repository.ChargeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChargeCategoryDetailsViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: ChargeRepository


    init {
        val wordsDao = AppDatabase.getDatabase(application).wordDao()
        repository = ChargeRepository(wordsDao)
    }


    fun  getCharges () : LiveData<List<Charge>>? {
        return  repository.allCharges
    }

    fun  getChargesByCategory (category: String) : LiveData<List<Charge>>? {
        return  repository.getChargesByCategory(category)
    }
}