package mx.com.charlyescaz.skilltest.utils.dialogs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object Dialogs {

    fun progressDialog(fragmentManager: FragmentManager, text: String = "Espere ..."): ProgressDialog {
        val prev: Fragment? = fragmentManager.findFragmentByTag("PROGRESS_DIALOG")
        if (prev != null && prev is ProgressDialog) {
            return prev
        }

        val progressDialog = ProgressDialog(text)
        progressDialog.isCancelable = false
        progressDialog.show(fragmentManager, "PROGRESS_DIALOG")
        return progressDialog
    }


}