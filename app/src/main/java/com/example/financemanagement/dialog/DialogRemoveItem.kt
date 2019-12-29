
import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.financemanagement.R
import com.example.financemanagement.dialog.DialogActionsListener
import kotlinx.android.synthetic.main.dialog_remove_item.*

class DialogRemoveItem constructor(context: Context, var listener: DialogActionsListener, var chargeId: Int) : Dialog(context) {

    init {
        setupWidndowConfigurations()

        actionAcceptButton.setOnClickListener {
            listener.onAccept(this, chargeId)
            dismiss()
        }

        actionDeclineButton.setOnClickListener {
            listener.onDecline(this)
            dismiss()
        }
    }

    private fun setupWidndowConfigurations() {
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.decorView?.setBackgroundResource(android.R.color.transparent)
        window?.setDimAmount(0.3f)
        //window?.attributes?.windowAnimations = R.style.DialogAnimation
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        setContentView(R.layout.dialog_remove_item)

        val displayMetrics = context.resources.displayMetrics
        val width = displayMetrics.widthPixels
        val wmlp = window?.attributes
        wmlp?.width = width
    }
}