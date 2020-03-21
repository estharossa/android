package com.android.mailfinal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        val fragmentMail = FirstFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.left, fragmentMail)
        fragmentTransaction.commit()
    }
}
