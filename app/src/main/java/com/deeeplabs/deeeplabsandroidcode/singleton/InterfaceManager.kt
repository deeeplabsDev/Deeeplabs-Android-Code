package com.deeeplabs.deeeplabsandroidcode.singleton

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.kaopiz.kprogresshud.KProgressHUD
import java.nio.charset.StandardCharsets

class InterfaceManager {
    private val isErrMsgShown = false
    private lateinit var hud: KProgressHUD
    private var isLoading: Boolean = false

    fun showLoading(context: Context, title: String, detail: String) {
        if (isLoading) {
            return
        }
        isLoading = true
        hud = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel(title)
            .setDetailsLabel(detail)
            .setCancellable(false)
            .setAnimationSpeed(3)
            .setDimAmount(0.5f)
        hud.show()
    }

    fun isValidEmailAddress(email: String): Boolean {
        val ePattern =
            "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val p = java.util.regex.Pattern.compile(ePattern)
        val m = p.matcher(email)
        return m.matches()
    }

    fun getStringFromHex(hex: String): String {
        val l = hex.length
        val data = ByteArray(l / 2)
        var i = 0
        while (i < l) {
            data[i / 2] =
                ((Character.digit(hex[i], 16) shl 4) + Character.digit(hex[i + 1], 16)).toByte()
            i += 2
        }
        return String(data, StandardCharsets.UTF_8)
    }

    fun hideLoading() {
        if ((!isLoading)) {
            return
        }
        isLoading = false
        hud.dismiss()
    }

    fun minimizeApp(context: Context) {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(startMain)
    }

    //programmatic method to change the status bar. Using this won't handle if the status bar icons are white
    fun changeStatusWindowColorInActivity(activity: Activity, theColorResource: Int) {
        val window = activity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = theColorResource
    }

    fun setColouredSpan(textView: TextView, word: String, color: Int) {
        val spannableString = SpannableString(textView.text)
        val start = textView.text.indexOf(word)
        val end = textView.text.indexOf(word) + word.length
        try {
            spannableString.setSpan(
                ForegroundColorSpan(color),
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textView.text = spannableString
        } catch (e: IndexOutOfBoundsException) {
            println("'$word' was not not found in TextView text")
        }
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus?.windowToken, 0
        )
    }

    companion object {
        private var INTERFACEMANAGER: InterfaceManager? = null
        fun sharedInstance(): InterfaceManager {
            if (INTERFACEMANAGER == null) {
                INTERFACEMANAGER = InterfaceManager()

            }
            return INTERFACEMANAGER!!
        }
    }

}