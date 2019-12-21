package com.example.financemanagement.chart.listeners

import android.content.Context
import android.view.View
import androidx.navigation.Navigation
import com.example.financemanagement.fragments.FinanceManagementOverviewFragmentDirections
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class ChartValueSelectListener(var rootView: View) : OnChartValueSelectedListener {

    override fun onNothingSelected() {}

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        val expensesCategories = arrayOf(e!!.data.toString())
        val action = FinanceManagementOverviewFragmentDirections
                .actionFinanceManagementOverviewFragmentToChargeCategoryDetailsFragment(expensesCategories)
        Navigation.findNavController(rootView).navigate(action)
    }
}