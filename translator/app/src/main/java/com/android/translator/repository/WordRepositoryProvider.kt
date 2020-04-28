package com.android.translator.repository

import com.android.translator.api.WordApi


object WordRepositoryProvider {
    fun provideWordRepository():WordRepository{
        return WordRepository( service = WordApi.create() )
    }
}