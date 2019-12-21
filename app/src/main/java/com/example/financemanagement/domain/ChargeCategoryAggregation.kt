package com.example.financemanagement.domain

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "chargeCategories")
class ChargeCategoryAggregation {

    @ColumnInfo(name = "percentage")
    var chargeCategory: ChargeCategory? = null

    @ColumnInfo(name = "percentage")
    var totalAmount: Long? = null

    @ColumnInfo(name = "percentage")
    var charges: List<Charge>? = null

    constructor()

}
