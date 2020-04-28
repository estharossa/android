package com.android.translator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.android.translator.view.AboutFragment
import com.android.translator.view.FavouritesFragment
import com.android.translator.view.TranslateFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fragmentManager
            .beginTransaction()
            .replace(R.id.container, TranslateFragment.create())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottomNavigation.setOnNavigationItemSelectedListener {item->
            when(item.itemId){
                R.id.home ->{
                    fragmentManager
                        .beginTransaction()
                        .replace(R.id.container, TranslateFragment.create())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.favourites ->{
                    fragmentManager
                        .beginTransaction()
                        .replace(R.id.container, FavouritesFragment.create())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.about ->{
                    fragmentManager
                        .beginTransaction()
                        .replace(R.id.container, AboutFragment.create())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }
    }
}
