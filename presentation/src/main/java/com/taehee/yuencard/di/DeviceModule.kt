package com.taehee.yuencard.di

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
    lateinit var textToSpeech: TextToSpeech

    @Provides
    @Singleton
    fun provideTextToSpeak(@ApplicationContext appContext: Context): TextToSpeech {
        textToSpeech = TextToSpeech(appContext) {
            textToSpeech.language = Locale.CANADA
        }
        return textToSpeech
    }
}