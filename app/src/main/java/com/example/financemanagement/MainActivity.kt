package com.example.financemanagement

import android.net.Uri
import android.os.Bundle
import android.view.WindowManager

import androidx.appcompat.app.AppCompatActivity
import com.example.financemanagement.fragments.ChargeCategoryDetailsFragment
import com.example.financemanagement.fragments.FinanceManagementOverviewFragment

class MainActivity : AppCompatActivity(), ChargeCategoryDetailsFragment.OnFragmentInteractionListener,
        FinanceManagementOverviewFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        title = "RadarChartActivity"

    }

    override fun onFragmentInteraction(uri: Uri) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        println("sfdsdfsdfsdf")
    }
}
