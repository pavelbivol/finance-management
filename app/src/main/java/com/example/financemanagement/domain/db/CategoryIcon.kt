package com.example.financemanagement.domain.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.financemanagement.R


@Entity(tableName = "categoryIcons")
class CategoryIcon {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "iconTitle")
    var iconTitle: String? = null

    @ColumnInfo(name = "iconColor")
    var iconColor: String? = null

    @ColumnInfo(name = "backgroundColor")
    var backgroundColor: String? = null

    @ColumnInfo(name = "drawableId")
    var drawableId: Int? = null

    constructor()

    @Ignore
    constructor(iconTitle: String?, iconColor: String?, backgroundColor: String?, drawableId: Int?) {
        this.iconTitle = iconTitle
        this.iconColor = iconColor
        this.backgroundColor = backgroundColor
        this.drawableId = drawableId
    }


    companion object {
        fun populateData(): Array<CategoryIcon>? {
            return arrayOf(
                    CategoryIcon("healthy", "#ffffff", "#0EC9AA", R.drawable.ic_healthy_living),
                    CategoryIcon("voyage", "#ffffff", "#ff0000", R.drawable.ic_voyage),
                    CategoryIcon("shopping", "#ffffff", "#5694C5", R.drawable.ic_shopping),
                    CategoryIcon("food", "#ffffff", "#E89336", R.drawable.ic_shopping_trolley),
                    CategoryIcon("bills", "#ffffff", "#205E5B", R.drawable.ic_bill_or_report),
                    CategoryIcon("party", "#ffffff", "#E7277D", R.drawable.ic_birthday_greeting),
                    CategoryIcon("university", "#ffffff", "#3F345F", R.drawable.ic_student)
            )
        }
    }
}