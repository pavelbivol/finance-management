package com.example.financemanagement.chart

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet

import com.example.financemanagement.R
import com.example.financemanagement.domain.ChargeCategoryAggregation
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import java.util.stream.Collectors


/*
* The order of the entries when being added to the entries array determines their position around the center of
* */
class ChargeCategoriesChart : RadarChart {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView()
    }

    private fun initView() {
        configureWeb()


        createMarkerView()
        configureXAxis()
        configureYAxis()
        configureLegend()
        animateXY(1400, 1400, Easing.EaseInOutBack)
    }

    private fun configureWeb() {
        //setBackgroundColor(Color.rgb(60, 65, 82))
        description.isEnabled = false
        webLineWidth = 1f
        webColor = Color.LTGRAY
        webLineWidthInner = 1f
        webColorInner = Color.LTGRAY
        webAlpha = 100
        isRotationEnabled = false
    }

    private fun configureXAxis() {
        val xAxis = xAxis
        //xAxis.setTypeface(tfLight);
        xAxis.textSize = 12f
        xAxis.yOffset = 0f
        xAxis.xOffset = 0f
        xAxis.textColor = Color.WHITE

        //setIAxisValue(xAxis);
    }

    private fun configureYAxis() {
        val yAxis = yAxis
        //yAxis.setTypeface(tfLight);
        yAxis.setLabelCount(5, false)
        yAxis.textSize = 9f
        yAxis.xOffset = 0f
        yAxis.yOffset = 0f
        yAxis.axisMinimum = 0f
        yAxis.textColor = Color.parseColor("#949494")
        yAxis.setDrawLabels(true)
    }

    private fun configureLegend() {
        val l = legend
        l.isEnabled = false
        /*l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        //l.setTypeface(tfLight);
        l.xEntrySpace = 7f
        l.yEntrySpace = 5f
        l.textColor = Color.WHITE*/
    }

    private fun createMarkerView() {
        val mv = MarkerView(context, R.layout.radar_markerview)
        mv.chartView = this // For bounds control
        marker = mv // Set the marker to the chart
    }

    private fun setIAxisValue(categoryAggregations: List<ChargeCategoryAggregation>) {
        if (categoryAggregations.isEmpty())
            return

        xAxis
                .setValueFormatter { value, axis ->
                    categoryAggregations[value.toInt() % categoryAggregations.size]
                            .categoryName

                }
    }

    fun setData(categoryAggregations: List<ChargeCategoryAggregation>) {

        //Todo insert chiar daca este una din valori este null ca sa pot adauga o categorie chiar daca nu are valoeare sau estimata
        val totalAmpountSpendEntries = categoryAggregations
                .stream()
                .filter { chargeCategoryAggregation -> chargeCategoryAggregation.totalAmount != null &&  chargeCategoryAggregation.categoryName != null}
                .map { categoryAggregation -> RadarEntry(categoryAggregation.totalAmount!!.toFloat(), categoryAggregation.categoryName) }
                .collect(Collectors.toList())

        val expectedEntries = categoryAggregations
                .stream()
                .filter { chargeCategoryAggregation -> chargeCategoryAggregation.expectedAmount != null &&  chargeCategoryAggregation.categoryName != null}
                .map { categoryAggregation -> RadarEntry(categoryAggregation.expectedAmount!!.toFloat(), categoryAggregation.categoryName) }
                .collect(Collectors.toList())

        val sets = ArrayList<IRadarDataSet>()
        sets.add(getRealValuesDataSet(totalAmpountSpendEntries))
        sets.add(getExpectedValuesDataSet(expectedEntries))

        val data = RadarData(sets)
        data.setValueTextSize(8f)
        data.setDrawValues(false)
        data.setValueTextColor(Color.WHITE)

        setData(data)
        setIAxisValue(categoryAggregations)
        invalidate()
    }

    private fun getExpectedValuesDataSet(entries: List<RadarEntry>): RadarDataSet {
        val dataSet = RadarDataSet(entries, "Last Week")
        dataSet.color = Color.rgb(103, 110, 129)
        dataSet.fillColor = Color.rgb(103, 110, 129)
        dataSet.setDrawFilled(true)
        dataSet.fillAlpha = 180
        dataSet.lineWidth = 2f
        dataSet.isDrawHighlightCircleEnabled = true
        dataSet.setDrawHighlightIndicators(false)
        return dataSet
    }

    private fun getRealValuesDataSet(entries: List<RadarEntry>): RadarDataSet {
        val dataSet = RadarDataSet(entries, "This Week")
        dataSet.color = Color.rgb(121, 162, 175)
        dataSet.fillColor = Color.rgb(121, 162, 175)
        dataSet.setDrawFilled(true)
        dataSet.fillAlpha = 180
        dataSet.lineWidth = 2f
        dataSet.isDrawHighlightCircleEnabled = true
        dataSet.setDrawHighlightIndicators(false)
        return dataSet
    }
}
