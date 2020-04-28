package com.android.translator.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.android.translator.MainActivity
import com.android.translator.R
import com.android.translator.db.AppDatabase
import com.android.translator.db.ItemDAO
import com.android.translator.model.Item
import com.android.translator.model.Word
import com.android.translator.repository.WordRepositoryProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_translate.*
import java.lang.Exception


class TranslateFragment : Fragment(){

    private var lang: String = "ru-en"
    private lateinit var text: String
    private var db: AppDatabase? = null
    private var itemDao: ItemDAO? = null

    companion object {
        fun create() = TranslateFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = WordRepositoryProvider.provideWordRepository()
        var x: Int
        var langs = arrayListOf("ru-en", "en-ru")
        swapButton.setOnClickListener {
            if (initialLanguageTextView.text == "Русский"){
                initialLanguageTextView.text = "English"
                finalLanguageTextView.text = "Русский"
            }
            else{
                initialLanguageTextView.text = "Русский"
                finalLanguageTextView.text = "English"
            }
            x =+ 1
            lang = if( x % 2 != 0)
                langs[1]
            else langs[0]

        }
        translateButton.setOnClickListener {
            if(initialLanguageTextView.text == "Русский")
                lang = "ru-en"
            else lang = "en-ru"
            text = initialEditText.text.toString()
            Log.d("LANG", lang)
            if(text.isNotEmpty()){
                repository.getTranslation(lang, text)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            val translation = it.text.toString().substring(1,it.text.toString().length-1)
                            translatedTextView.text = translation
                        },
                        {
                            Log.d("Error", it.message)
                        }
                    )
            }
            else{
                Toast.makeText(context, "Пожалуйста, заполните пустое поле!", Toast.LENGTH_SHORT).show()
            }

        }
        clearButton.setOnClickListener {
            initialEditText.text.clear()
        }
        saveButton.setOnClickListener {
            if(translatedTextView.text.isNotEmpty()){
                Observable.fromCallable {
                    db = AppDatabase.getAppDataBase(context!!)
                    itemDao = db?.itemDAO()
                    val item = Item(0, lang, "$text - ${translatedTextView.text}")
                    with(itemDao){
                        this?.saveItem(item)
                    }
                }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
                Toast.makeText(context, "Успешно сохранено!", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(context, "Вы ничего не перевели", Toast.LENGTH_SHORT).show()
        }
    }
}
