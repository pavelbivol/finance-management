package com.example.financemanagement.domain

import androidx.room.*
import java.math.BigDecimal

@Entity(tableName = "charges")
class Charge {

    @PrimaryKey(autoGenerate = true)
    var chargeId: Int? = null

    @Embedded
    var category: ChargeCategory? = null

    @ColumnInfo(name = "charge_description")
    var chargeDescription: String? = null

    @ColumnInfo(name = "charge_amount")
    var chargeAmount: Long? = null

    constructor()

    @Ignore
    constructor(category: ChargeCategory?, chargeDescription: String?, chargeAmount: Long?) {
        this.category = category
        this.chargeDescription = chargeDescription
        this.chargeAmount = chargeAmount
    }
}
