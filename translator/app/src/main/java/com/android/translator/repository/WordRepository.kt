package com.android.translator.repository


import com.android.translator.api.WordApi
import com.android.translator.model.Language
import com.android.translator.model.Word
import io.reactivex.Observable
import io.reactivex.Single

class WordRepository(private val service: WordApi) {
    fun getTranslation(lang: String, text: String): Single<Word> {
        return service.get(lang, text)
    }
    fun getDetection(text: String): Single<Language>{
        return service.detect(text)
    }

}