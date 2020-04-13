package com.android.developerjobs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.developerjobs.view.JobListFragment

class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        val jobListFragment = JobListFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainActivity, jobListFragment)
        fragmentTransaction.commit()
    }
}
