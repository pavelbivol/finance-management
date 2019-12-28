package com.example.financemanagement.dialog

import android.app.Dialog

interface DialogActionsListener {
    fun onAccept(dialog: Dialog, param : Any? = null)
    fun onDecline(dialog: Dialog)
}