package com.deeeplabs.deeeplabsandroidcode.activity

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.deeeplabs.deeeplabsandroidcode.R
import com.deeeplabs.deeeplabsandroidcode.singleton.InterfaceManager
import kotlinx.android.synthetic.main.activity_login.*

open class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun setImageSourceOnLogo(drawable:Drawable){
        loginLogoImageView.setImageDrawable(drawable)
    }

    fun getEmail():String{
        return emailLoginEditText.text.toString()
    }

    fun getPassword():String{
        return passwordLoginEditText.text.toString()
    }

    fun getSubmitButton(): Button {
        return loginButton
    }

    fun getErrorWhenTryToSubmit():String?{
        if(getEmail().isNullOrBlank()){
            return "Please insert your email"
        }

        if(getPassword().isNullOrBlank()){
            return "Please insert your password"
        }

        if(!InterfaceManager.sharedInstance().isValidEmailAddress(getEmail())){
            return "Please insert correct email"
        }

        return null
    }

}

