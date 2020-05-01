package com.android.translator.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.android.translator.R
import com.android.translator.db.AppDatabase
import com.android.translator.db.ItemDAO
import com.android.translator.model.Item
import com.android.translator.repository.WordRepositoryProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_translate.*


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

        swapButton.setOnClickListener {
            changeLanguage()
        }

        translateButton.setOnClickListener {
            if(isOnline()){
                lang = if(initialLanguageTextView.text == "Русский")
                    "ru-en"
                else "en-ru"
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
            else Toast.makeText(context, "Отсутствует интернет соединение!", Toast.LENGTH_SHORT).show()
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

    private fun changeLanguage(){
        if (initialLanguageTextView.text == "Русский"){
            initialLanguageTextView.text = "English"
            finalLanguageTextView.text = "Русский"
        }
        else{
            initialLanguageTextView.text = "Русский"
            finalLanguageTextView.text = "English"
        }
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}
