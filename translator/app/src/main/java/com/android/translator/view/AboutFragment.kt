package com.android.translator.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.translator.R
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment : Fragment() {

    companion object {
        fun create() = AboutFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shareButton.setOnClickListener {
            shareApp()
        }
    }

    private fun shareApp(){
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        val appLink = "https://play.google.com/store/apps/details?id=" + activity?.application?.packageName
        intent.putExtra(Intent.EXTRA_TEXT, appLink)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "ПОДЕЛИТЬСЯ ЧЕРЕЗ"))
    }
}
