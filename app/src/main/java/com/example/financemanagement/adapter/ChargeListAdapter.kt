package com.example.financemanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financemanagement.R
import com.example.financemanagement.domain.Charge
import kotlinx.android.synthetic.main.recicler_view_charge_item.view.*
import java.text.SimpleDateFormat


class ChargeListAdapter(var charges: ArrayList<Charge>) :
        RecyclerView.Adapter<ChargeListAdapter.UserViewHolder>() {

    fun updateCharges(newUsers: List<Charge>) {
        charges.clear()
        charges.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recicler_view_charge_item, parent, false)
    )

    override fun getItemCount() = charges.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(charges[position])
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val chargeDescription = view.charge_description
        private val chargeDate = view.charge_date
        private val chargeAmount = view.charge_amt

        fun bind(charge: Charge) {
            chargeDescription.text = charge.chargeDescription
            chargeAmount.text = charge.chargeAmount.toString() + " Euro"


            val pattern = "dd MMM yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern)
            val date: String = simpleDateFormat.format(charge.date)

            chargeDate.text = date
        }
    }
}
