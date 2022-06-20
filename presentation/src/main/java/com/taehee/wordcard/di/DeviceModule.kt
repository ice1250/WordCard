package com.taehee.wordcard.di

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DeviceModule {
    lateinit var textToSpeech: TextToSpeech

    @Provides
    @Singleton
    fun provideTextToSpeak(@ApplicationContext appContext: Context): TextToSpeech {
        Log.i("taehee", "provideTextToSpeak")
        textToSpeech = TextToSpeech(appContext) {
            textToSpeech.language = Locale.KOREAN
        }
        return textToSpeech
    }
}