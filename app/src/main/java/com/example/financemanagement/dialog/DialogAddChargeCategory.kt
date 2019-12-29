
import android.app.Dialog
import android.content.Context
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import com.example.financemanagement.R
import com.example.financemanagement.adapter.IconListAdapter
import com.example.financemanagement.dialog.DialogActionsListener
import com.example.financemanagement.domain.db.CategoryIcon
import com.example.financemanagement.domain.db.Charge
import com.example.financemanagement.domain.db.ChargeCategory
import com.example.financemanagement.domain.DialogAddChargeCategoryResponse
import kotlinx.android.synthetic.main.dialog_add_category.*

class DialogAddChargeCategory constructor(
        context: Context,
        var listener: DialogActionsListener,
        var charge: Charge
) : Dialog(context) {

    init {
        setupWidndowConfigurations()

        if (charge.categoryName != null) {
            categoryNameInput.setText(charge.categoryName)
        }




        val iconListAdapter = IconListAdapter(context, getData()!!)

        categoryIcons


        categoryIcons.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = iconListAdapter
        }
        categoryIcons.invalidate()



        actionAcceptButton.setOnClickListener {
            val category = ChargeCategory()
            category.name = categoryNameInput.text.toString()
            category.expectedAmount = expectedAmountInput.text.toString().toLong()
            category.iconTitle = iconListAdapter.getItem(iconListAdapter.selectedPosition)?.iconTitle

            listener.onAccept(this, DialogAddChargeCategoryResponse(charge, category))
            dismiss()
        }

        actionDeclineButton.setOnClickListener {
            listener.onDecline(this)
            dismiss()
        }



        println("DialogAddNote init")
    }


    fun getData(): Array<CategoryIcon>? {
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

    private fun setupWidndowConfigurations() {
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.decorView?.setBackgroundResource(android.R.color.transparent)
        window?.setDimAmount(0.3f)
        //window?.attributes?.windowAnimations = R.style.DialogAnimation
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        setContentView(R.layout.dialog_add_category)

        val displayMetrics = context.resources.displayMetrics
        val width = displayMetrics.widthPixels
        val wmlp = window?.attributes
        wmlp?.width = width
    }
}