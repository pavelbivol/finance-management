package com.example.financemanagement.domain

import androidx.room.*
import java.math.BigDecimal

@Entity(tableName = "chargeCategories")
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
class ChargeCategory {

    @PrimaryKey(autoGenerate = true)
    var chargeCategoryId: Int? = null

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
