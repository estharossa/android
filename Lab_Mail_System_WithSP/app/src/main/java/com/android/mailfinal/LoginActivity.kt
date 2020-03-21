package com.android.mailfinal

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

private const val LOGIN_PREF = "LOGIN_PREF"

class LoginActivity: AppCompatActivity() {

    private var email: String? = null
    private var password: String? = null
    private var prefs: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val data = this.getAuthorizationData()
        Toast.makeText(this, data, Toast.LENGTH_LONG).show()
        if (data.isNotEmpty()){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else {
            login()
        }
    }

    private fun login(){
        loginButton.setOnClickListener {
            email = emailEditText.text.toString()
            password = passwordEditText.text.toString()
            save()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun save(){
        editor = prefs?.edit()
        editor?.putString(LOGIN_PREF, "$email+$password")
        editor?.apply()
    }

    private fun getAuthorizationData():String = prefs?.getString(LOGIN_PREF, "") ?: ""

    fun clear(){
        editor = prefs?.edit()
        editor?.putString(LOGIN_PREF, "")
        editor?.apply()
    }
}