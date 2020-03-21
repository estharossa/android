package com.android.mailfinal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        logoutButton.setOnClickListener {
            logout()
            goToLoginPage()
        }
    }

    private fun init(){
        val fragmentMail = FirstFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.left, fragmentMail)
        fragmentTransaction.commit()
    }

    private fun logout(){
        LoginActivity().clear()
    }

    private fun goToLoginPage(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
