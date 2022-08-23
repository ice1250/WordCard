package com.taehee.wordcard.di

import android.content.Context
import android.speech.tts.TextToSpeech
import com.taehee.wordcard.device.repository.TtsRepositoryImpl
import com.taehee.wordcard.domain.repository.TtsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*

@InstallIn(ViewModelComponent::class)
@Module
class TtsModule {

    @Provides
    fun provideTts(@ApplicationContext appContext: Context): TextToSpeech {
        lateinit var textToSpeech: TextToSpeech
        textToSpeech = TextToSpeech(appContext) {
            if (it != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.KOREAN
            }
        }
        return textToSpeech
    }

    @Provides
    fun provideTtsRepository(repository: TtsRepositoryImpl): TtsRepository {
        return repository
    }
}