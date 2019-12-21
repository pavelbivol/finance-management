package com.example.financemanagement.repository

import androidx.lifecycle.LiveData
import com.example.financemanagement.dao.ChargeDao
import com.example.financemanagement.domain.Charge

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ChargeRepository(private val wordDao: ChargeDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Charge>> = wordDao.getAll()


    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    fun insert(word: Charge) {
        wordDao.insert(word)
    }
}