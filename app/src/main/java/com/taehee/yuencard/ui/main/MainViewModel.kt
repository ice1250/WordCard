package com.taehee.yuencard.ui.main

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taehee.yuencard.CardColor
import com.taehee.yuencard.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val currentWord: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also {
            it.value = Word.getRandomWord()
        }
    }

    private val currentColor: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also {
            it.value = CardColor.getCardColors()
        }
    }

    fun getWord(): LiveData<String> {
        return currentWord
    }

    fun getColor(): LiveData<String> {
        return currentColor
    }

    fun refresh() {
        while (true) {
            val word = Word.getRandomWord()
            val color = CardColor.getCardColors()
            if (!TextUtils.equals(currentWord.value, word) && !TextUtils.equals(currentColor.value,
                    color)
            ) {
                viewModelScope.launch(Dispatchers.IO) {
                    delay(1000)
                    viewModelScope.launch(Dispatchers.Main) {
                        currentWord.value = word
                        currentColor.value = color
                    }
                }
                break
            }
        }
    }
}