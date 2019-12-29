package com.example.financemanagement.domain

import androidx.room.TypeConverters
import com.example.financemanagement.converter.TimestampConverter
import java.util.*

class ChargeIconsAggregation {
    var iconColor: String? = null

    var backgroundColor: String? = null

    var drawableId: Int? = null

    var chargeDescription: String? = null

    var chargeAmount: Long? = null

    @TypeConverters(TimestampConverter::class)
    var date: Date? = null
}