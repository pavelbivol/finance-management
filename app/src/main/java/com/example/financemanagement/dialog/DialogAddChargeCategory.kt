
import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.financemanagement.R
import com.example.financemanagement.dialog.DialogActionsListener
import com.example.financemanagement.domain.Charge
import com.example.financemanagement.domain.ChargeCategory
import com.example.financemanagement.domain.DialogAddChargeCategoryResponse
import kotlinx.android.synthetic.main.dialog_add_category.*
import java.util.*

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

        actionAcceptButton.setOnClickListener {
            val category = ChargeCategory()
            category.name = categoryNameInput.text.toString()
            category.expectedAmount = expectedAmountInput.text.toString().toLong()

            listener.onAccept(this, DialogAddChargeCategoryResponse(charge, category))
            dismiss()
        }

        actionDeclineButton.setOnClickListener {
            listener.onDecline(this)
            dismiss()
        }

        println("DialogAddNote init")
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