package com.android.translator.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
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
        feedbackButton.setOnClickListener {
            sendFeedback()
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

    private fun sendFeedback() {
        var message = ""
        val type = InputType.TYPE_CLASS_TEXT or
                InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        val dialog: MaterialDialog = MaterialDialog(context!!).show {
            input(
                hint = "Напишите нам о приложении...",
                inputType = type
            ) { materialDialog: MaterialDialog, charSequence: CharSequence ->
                charSequence.forEach {
                    message += it
                }
            }
            positiveButton(R.string.submit) {
                val mIntent = Intent(Intent.ACTION_SEND)
                mIntent.data = Uri.parse("mailto:")
                mIntent.type = "text/plain"
                mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("danik.encore@gmail.com"))
                mIntent.putExtra(Intent.EXTRA_SUBJECT, "Easy Translator")
                mIntent.putExtra(Intent.EXTRA_TEXT, message)
                startActivity(Intent.createChooser(mIntent, "Выберите почтовый клиент"))
            }

        }
    }
}
