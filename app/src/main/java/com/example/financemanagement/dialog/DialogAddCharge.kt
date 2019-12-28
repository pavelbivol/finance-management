
import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import com.example.financemanagement.R
import com.example.financemanagement.dialog.DialogActionsListener
import com.example.financemanagement.domain.Charge
import com.example.financemanagement.domain.ChargeCategory
import kotlinx.android.synthetic.main.dialog_add_charge.*
import java.util.*

class DialogAddCharge constructor(
        context: Context,
        var listener: DialogActionsListener
) : Dialog(context) {

    init {
        setupWidndowConfigurations()
        expenseDescriptionInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    //setClearText(false)
                } else {
                    //setClearText(true)
                }
            }
        })

        actionAcceptButton.setOnClickListener {
            dismiss()

            val charge = Charge()
            charge.categoryName = expenseCategory.text.toString()
            charge.chargeAmount = expenseAmount.text.toString().toLong()
            charge.chargeDescription = expenseDescriptionInput.text.toString()
            charge.date = Date()

            listener.onAccept(this, charge)
        }

        actionDeclineButton.setOnClickListener {
            dismiss()
            listener.onDecline(this)
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
        setContentView(R.layout.dialog_add_charge)

        val displayMetrics = context.resources.displayMetrics
        val width = displayMetrics.widthPixels
        val wmlp = window?.attributes
        wmlp?.width = width
    }
}