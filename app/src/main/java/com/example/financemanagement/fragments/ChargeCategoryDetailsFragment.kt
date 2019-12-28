package com.example.financemanagement.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financemanagement.R
import com.example.financemanagement.adapter.ChargeListAdapter
import com.example.financemanagement.domain.Charge
import com.example.financemanagement.viewModel.ChargeCategoryDetailsViewModel
import kotlinx.android.synthetic.main.fragment_charge_category_details.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChargeCategoryDetailsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private val args: ChargeCategoryDetailsFragmentArgs by navArgs()


    private val chargesAdapter = ChargeListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View =  inflater.inflate(R.layout.fragment_charge_category_details, container, false)
        val viewModel = ViewModelProviders.of(requireActivity())
                .get(ChargeCategoryDetailsViewModel::class.java)
        val categories: Array<String> = args.expenseCategories

        configureReciclerView(view, categories, viewModel)

        return view
    }

    private fun configureReciclerView(view: View, categories: Array<String>,
                              viewModel: ChargeCategoryDetailsViewModel){
        val recycleView: RecyclerView = view.findViewById(R.id.charges)
        val dividerItemDecoration = DividerItemDecoration(recycleView.getContext(),
                DividerItemDecoration.VERTICAL)
        dividerItemDecoration
                .setDrawable(ContextCompat.getDrawable(getContext()!!, R.drawable.recicler_view_divider)!!)
        recycleView.addItemDecoration(dividerItemDecoration)

        recycleView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chargesAdapter
        }

        viewModel.getChargesByCategory(categories[0])?.observe(this, Observer { charges ->

            chargesAdapter.updateCharges(charges)
            recycleView.invalidate()

            totalAmount.text = "You spent a total of  " + calcTotAmt(charges) + " euro this month "


            if (activity != null && activity!!.actionBar != null) {
                activity!!.actionBar!!.setTitle(categories[0])
            }
        })
    }


    private fun calcTotAmt(charges: List<Charge>) : String{
        return charges.sumByDouble { charge -> charge.chargeAmount!!.toDouble() }.toString()
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
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ChargeCategoryDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
