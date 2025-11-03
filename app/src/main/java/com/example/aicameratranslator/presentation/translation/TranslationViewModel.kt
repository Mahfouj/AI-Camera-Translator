package com.example.aicameratranslator.presentation.translation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aicameratranslator.data.api.Language
import com.example.aicameratranslator.domain.usecase.GetSupportedLanguagesUseCase
import com.example.aicameratranslator.domain.usecase.TranslateTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(

    private val translateTextUseCase: TranslateTextUseCase,

    private val getSupportedLanguagesUseCase: GetSupportedLanguagesUseCase

): ViewModel() {



   private val _languages  = MutableLiveData<List<Language>>(emptyList())

    val  languages : LiveData<List<Language>> = _languages

    private  val _translationResult = MutableLiveData<String>()

    val translationResult : LiveData<String> = _translationResult

    fun  loadLanguages() {

        viewModelScope.launch {

            val res = getSupportedLanguagesUseCase()

            res.onSuccess { _languages.postValue(it) }

            res.onFailure { _languages.postValue(emptyList()) }
        }
    }

    fun translate(text: String, targetLang: String) {

        viewModelScope.launch {

            val result: Result<String> = translateTextUseCase(text, targetLang)

            // Handle both the success and failure cases of the Result
            result.onSuccess { translatedText ->
                // On success, post the actual String value to the LiveData
                _translationResult.postValue(translatedText)

            }.onFailure { error ->

                // On failure, post an error message or handle it as needed
                // This prevents crashes and gives feedback to the user.

                _translationResult.postValue("Translation Error: ${error.message}")
            }
        }
    }

    }