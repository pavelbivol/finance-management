package com.example.financemanagement.fragments

import DialogAddCharge
import DialogAddChargeCategory
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.financemanagement.R
import com.example.financemanagement.chart.ChargeCategoriesChart
import com.example.financemanagement.chart.listeners.ChartValueSelectListener
import com.example.financemanagement.viewModel.FinanceManagementOverviewViewModel
import androidx.lifecycle.Observer
import com.example.financemanagement.dialog.DialogActionsListener
import com.example.financemanagement.domain.Charge
import com.example.financemanagement.domain.ChargeCategory
import com.example.financemanagement.domain.DialogAddChargeCategoryResponse

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FinanceManagementOverviewFragment : Fragment(), DialogActionsListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private var viewModel: FinanceManagementOverviewViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //Am detectat o categorie noua , cit ati vrea sa cheltuiti in aceasta categorie ?/
        val view : View = inflater
                .inflate(R.layout.fragment_finance_management_overview, container, false)
        viewModel = ViewModelProviders
                .of(requireActivity()).get(FinanceManagementOverviewViewModel::class.java)

        val chargeCategoriesChart = view.findViewById<ChargeCategoriesChart>(R.id.radarChart)
        val spendButton : Button = view.findViewById(R.id.spend_button)
        val incomingButton : Button = view.findViewById(R.id.incoming_button)
        val totalSpend: TextView = view.findViewById(R.id.total_amount_spend)

        chargeCategoriesChart.setOnChartValueSelectedListener(ChartValueSelectListener(chargeCategoriesChart))

        viewModel?.getChargeCategoryAgregations()?.observe(this, Observer { charges ->
            chargeCategoriesChart.setData(charges)
        })

        viewModel?.getCharges()?.observe(this, Observer { charges ->
            totalSpend.text = calcTotAmt(charges).toString()
        })

        spendButton.setOnClickListener {
            val dialog: DialogAddCharge = this.context?.let { it1 -> DialogAddCharge(it1, this) }!!
            dialog.show()
        }

        incomingButton.setOnClickListener {
            viewModel?.getChargeCategoryAgregations()?.observe(this, Observer { charges ->


                println(charges.size)
                println(charges.get(0).totalAmount)
                println(charges.get(0).categoryName)
                println(charges.get(0).expectedAmount)
            })
        }

        return view
    }

    fun calcTotAmt(charges: List<Charge>): Double{
        return charges
                .map { charge -> charge.chargeAmount }
                .filter { l -> l != null }
                .sumByDouble { l -> l!!.toDouble() }
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FinanceManagementOverviewFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onAccept(dialog: Dialog, param: Any?) {

        if (param is Charge) {
            viewModel?.insertCharge(param)?.observe(this, Observer { existCategory ->
                if (!existCategory) {
                    this.context?.let { it1 -> DialogAddChargeCategory(it1, this, param) }!!.show()
                }
            })
        } else if (param is DialogAddChargeCategoryResponse) {
            viewModel?.insertCategory(param.chargeCategory)?.observe(this, Observer { categoryAlreadyExist ->
                if (categoryAlreadyExist) {
                    Toast.makeText(context,"This category already exist!",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onDecline(dialog: Dialog) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
