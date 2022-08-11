package com.taehee.wordcard.di

import android.content.Context
import android.speech.tts.TextToSpeech
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

    @Provides
    @Singleton
    fun provideTextToSpeak(@ApplicationContext appContext: Context): TextToSpeech {
        lateinit var textToSpeech: TextToSpeech
        textToSpeech = TextToSpeech(appContext) {
            if (it != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.KOREAN
            }
        }
        return textToSpeech

    }
}