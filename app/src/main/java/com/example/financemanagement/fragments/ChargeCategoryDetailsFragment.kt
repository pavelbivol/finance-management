package com.example.financemanagement.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financemanagement.R
import com.example.financemanagement.adapter.ChargeListAdapter
import com.example.financemanagement.viewModel.ChargeCategoryDetailsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ChargeCategoryDetailsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ChargeCategoryDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChargeCategoryDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
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

        var view: View =  inflater.inflate(R.layout.fragment_charge_category_details, container, false)

        val categories: Array<String> = args.expenseCategories

        val viewModel = ViewModelProviders.of(requireActivity())
                .get(ChargeCategoryDetailsViewModel::class.java)
        var recycleView: RecyclerView = view.findViewById(R.id.charges)


        recycleView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chargesAdapter
        }


        val charges = viewModel.getChargesByCategory(categories[0])?.observe(this, Observer { charges ->
            println("Charges list size is : " + charges?.size)


            chargesAdapter.updateCharges(charges)

            recycleView.invalidate()



        })









        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChargeCategoryDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
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
