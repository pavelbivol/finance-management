package com.example.financemanagement.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.financemanagement.R
import com.example.financemanagement.domain.ChargeIconsAggregation
import com.example.financemanagement.domain.db.Charge
import com.example.financemanagement.fragments.interfaces.DeleteItemCalbackInterface
import kotlinx.android.synthetic.main.recicler_view_charge_item.view.*
import java.text.SimpleDateFormat
import java.util.*


class ChargeListAdapter(var context: Context, var charges: ArrayList<ChargeIconsAggregation>,
                        var callback: DeleteItemCalbackInterface) :
        RecyclerView.Adapter<ChargeListAdapter.UserViewHolder>() {

    fun updateCharges(newUsers: List<ChargeIconsAggregation>) {

        println("size is : " + newUsers.size)
        charges.clear()
        charges.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recicler_view_charge_item, parent, false)
    )

    override fun getItemCount() = charges.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(position)
    }

    fun notifyToDeleteItem(position: Int) {
        //.deleteItem(charges[position].chargeId!!)

    }

    fun removeItemByChargeid(chargeId: Int){
        /*val charge = charges
                .find { charge -> charge.chargeId!! == chargeId }

        if (charge != null) {
            charges.remove(charge)
            notifyDataSetChanged()

        }*/
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val chargeDescription = view.charge_description
        private val chargeDate = view.charge_date
        private val chargeAmount = view.charge_amt
        private val removeChargeicon = view.delete_charge_image
        private val cartView = view.cardview
        private val icon: ImageView = view.image_view

        fun bind(position: Int) {

            val charge = charges[position]
            chargeDescription.text = charge.chargeDescription
            chargeAmount.text = charge.chargeAmount.toString() + " Euro"
            chargeDate.text = formatDate(charge)
            removeChargeicon.setOnClickListener { notifyToDeleteItem(position) }


            cartView.setCardBackgroundColor(Color.parseColor(charge.backgroundColor))
            val myIcon: Drawable? = ContextCompat.getDrawable(context.applicationContext, charge.drawableId!!)
            myIcon!!.setTint(Color.parseColor(charge.iconColor))

            icon.setImageDrawable(myIcon)

        }

        fun formatDate(charge: ChargeIconsAggregation) : String{
            val pattern = "dd MMM yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            return simpleDateFormat.format(charge.date)
        }
    }
}
