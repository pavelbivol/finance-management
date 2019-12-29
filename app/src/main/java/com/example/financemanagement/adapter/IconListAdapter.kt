package com.example.financemanagement.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.financemanagement.R
import com.example.financemanagement.domain.db.CategoryIcon


class IconListAdapter internal constructor(private val context: Context, data: Array<CategoryIcon>) : RecyclerView.Adapter<IconListAdapter.ViewHolder>() {
    var selectedPosition = -1
    private val mData: Array<CategoryIcon> = data
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    //private var mClickListener: ItemClickListener? = null


    // inflates the cell layout from xml when needed
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int)= ViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.recicler_view_grid_icon_item, parent, false)
    )


    // binds the data to the TextView in each cell
    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    // total number of cells
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView.findViewById(R.id.card_view)
        var imageView: ImageView =  itemView.findViewById(R.id.image_view)

        fun bind(position: Int) {

            val categoryIcon: CategoryIcon = mData[position]

            cardView.setCardBackgroundColor(Color.parseColor(categoryIcon.backgroundColor))
            val myIcon: Drawable? = ContextCompat.getDrawable(context.applicationContext,categoryIcon.drawableId!!)
            myIcon!!.setTint(Color.parseColor(categoryIcon.iconColor))
            imageView.setImageDrawable(myIcon)

            if(selectedPosition==position){
                cardView.setBackgroundColor(Color.parseColor("#000000"))
            }
            else {
                //holder.cardView.setBackgroundColor(0)
            }

            cardView.setOnClickListener(View.OnClickListener {
                selectedPosition = position
                notifyDataSetChanged()
            })
        }
    }

    // convenience method for getting data at click position
    fun getItem(iconTitle: String): CategoryIcon? {
        return mData.find { categoryIcon ->  categoryIcon.iconTitle.equals(iconTitle)}
    }


    fun getItem(position: Int): CategoryIcon? {
        return mData[position]
    }

}