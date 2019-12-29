package com.example.financemanagement.domain.db

import androidx.room.*
import com.example.financemanagement.converter.TimestampConverter
import java.math.BigDecimal
import java.util.*

@Entity(tableName = "charges")
class Charge {

    @PrimaryKey(autoGenerate = true)
    var chargeId: Int? = null

    @ColumnInfo(name = "category_name")
    var categoryName: String? = null

    @ColumnInfo(name = "charge_description")
    var chargeDescription: String? = null

    @ColumnInfo(name = "charge_amount")
    var chargeAmount: Long? = null

    @ColumnInfo(name = "date")
    @TypeConverters(TimestampConverter::class)
    var date: Date? = null

    constructor()

    @Ignore
    constructor(chargeId: Int?, categoryName: String?, chargeDescription: String?, chargeAmount: Long?, date: Date?) {
        this.chargeId = chargeId
        this.categoryName = categoryName
        this.chargeDescription = chargeDescription
        this.chargeAmount = chargeAmount
        this.date = date
    }


}
