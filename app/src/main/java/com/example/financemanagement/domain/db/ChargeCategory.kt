package com.example.financemanagement.domain.db

import androidx.annotation.NonNull
import androidx.room.*

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

    @ColumnInfo(name = "iconTitle")
    var iconTitle: String? = null

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
    constructor(name: String?, expectedAmount: Long?, percentage: Int, iconTitle: String?) {
        this.name = name
        this.expectedAmount = expectedAmount
        this.percentage = percentage
        this.iconTitle = iconTitle
    }
}
