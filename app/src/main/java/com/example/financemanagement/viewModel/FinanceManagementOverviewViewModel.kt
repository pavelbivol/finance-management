package com.example.financemanagement.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.financemanagement.config.AppDatabase
import com.example.financemanagement.domain.Charge
import com.example.financemanagement.domain.ChargeCategory
import com.example.financemanagement.domain.ChargeCategoryAggregation
import com.example.financemanagement.repository.ChargeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FinanceManagementOverviewViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ChargeRepository
    var chargeList: MutableLiveData<List<ChargeCategoryAggregation>> = MutableLiveData()

    init {
        val wordsDao = AppDatabase.getDatabase(application).wordDao()
        repository = ChargeRepository(wordsDao)
        //chargeList.postValue(hardCodedListOfCharges)
        //allWords = repository.allWords
    }



    fun getChargeCategoryAgregations() : LiveData<List<ChargeCategoryAggregation>>{


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

    // The implementation of insert() is completely hidden from the UI.
    // We don't want insert to block the main thread, so we're launching a new
    // coroutine. ViewModels have a coroutine scope based on their lifecycle called
    // viewModelScope which we can use here.


 /*   fun insert(word: Charge) = viewModelScope.launch {
        repository.insert(word)
    }*/


 /*   fun launchDataLoad(word: Charge) {
        viewModelScope.launch {
            repository.insert(word)
            // Modify UI
        }
    }*/




    /*private val hardCodedListOfCharges: List<ChargeCategoryAggregation>
        get() {
            val categoryAggregations = ArrayList<ChargeCategoryAggregation>()
            val chargeCategoryAggregation = ChargeCategoryAggregation()
            val chargeCategory = ChargeCategory("Chirie si servicii", 340)
            chargeCategoryAggregation.chargeCategory = chargeCategory
            chargeCategoryAggregation.totalAmount = 340

            val chargeCategoryAggregation1 = ChargeCategoryAggregation()
            val chargeCategory1 = ChargeCategory("Mincare", 200)
            chargeCategoryAggregation1.chargeCategory = chargeCategory1
            chargeCategoryAggregation1.totalAmount = 200

            val chargeCategoryAggregation2 = ChargeCategoryAggregation()
            val chargeCategory2 = ChargeCategory("Calatorii", 200)
            chargeCategoryAggregation2.chargeCategory = chargeCategory2
            chargeCategoryAggregation2.totalAmount = 340

            val chargeCategoryAggregation3 = ChargeCategoryAggregation()
            val chargeCategory3 = ChargeCategory("clothing", 100)
            chargeCategoryAggregation3.chargeCategory = chargeCategory3
            chargeCategoryAggregation3.totalAmount = 200

            val chargeCategoryAggregation4 = ChargeCategoryAggregation()
            val chargeCategory4 = ChargeCategory("Transport", 25)
            chargeCategoryAggregation4.chargeCategory = chargeCategory4
            chargeCategoryAggregation4.totalAmount = 0

            val chargeCategoryAggregation6 = ChargeCategoryAggregation()
            val chargeCategory6 = ChargeCategory("Altele", 100)
            chargeCategoryAggregation6.chargeCategory = chargeCategory6
            chargeCategoryAggregation6.totalAmount = 0

            val chargeCategoryAggregation7 = ChargeCategoryAggregation()
            val chargeCategory7 = ChargeCategory("afdsfsa", 100)
            chargeCategoryAggregation7.chargeCategory = chargeCategory7
            chargeCategoryAggregation7.totalAmount = 0

            val chargeCategoryAggregation8 = ChargeCategoryAggregation()
            val chargeCategory8 = ChargeCategory("jsfgss", 100)
            chargeCategoryAggregation8.chargeCategory = chargeCategory8
            chargeCategoryAggregation8.totalAmount = 0



            categoryAggregations.add(chargeCategoryAggregation)
            categoryAggregations.add(chargeCategoryAggregation1)
            categoryAggregations.add(chargeCategoryAggregation2)
            categoryAggregations.add(chargeCategoryAggregation3)
            categoryAggregations.add(chargeCategoryAggregation4)
            categoryAggregations.add(chargeCategoryAggregation6)
            categoryAggregations.add(chargeCategoryAggregation7)
            categoryAggregations.add(chargeCategoryAggregation8)

            return categoryAggregations
        }*/
}