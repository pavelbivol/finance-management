package com.example.financemanagement.domain

import androidx.annotation.NonNull
import androidx.room.*
import java.math.BigDecimal

@Entity(tableName = "chargeCategories")
class ChargeCategory {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "expected_Amount")
    var expectedAmount: Long? = null

    @ColumnInfo(name = "percentage")
    var percentage: Int = 0

    constructor()

    @Ignore
    constructor(name: String){
        this.name = name
    }

    @Ignore
    constructor(name: String, expectedAmount: Long) {
        this.name = name
        this.expectedAmount = expectedAmount
    }

    @Ignore
    constructor(name: String, expectedAmount: Long, percentage: Int) {
        this.name = name
        this.expectedAmount = expectedAmount
        this.percentage = percentage
    }
}
