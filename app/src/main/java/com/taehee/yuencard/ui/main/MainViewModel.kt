package com.taehee.yuencard.ui.main

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taehee.yuencard.CardColor
import com.taehee.yuencard.Word

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
            if (!TextUtils.equals(currentColor.value, word)) {
                currentWord.value = word
                break;
            }
        }
        while (true) {
            val color = CardColor.getCardColors()
            if (!TextUtils.equals(currentColor.value, color)) {
                currentColor.value = color
                break;
            }
        }
    }
}