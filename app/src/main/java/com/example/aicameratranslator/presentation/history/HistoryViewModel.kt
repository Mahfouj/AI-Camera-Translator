package com.example.aicameratranslator.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aicameratranslator.data.model.TranslationResult
import com.example.aicameratranslator.domain.usecase.DeleteTranslationUseCase
import com.example.aicameratranslator.domain.usecase.GetTranslationHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(

    private val getHistoryUseCase: GetTranslationHistoryUseCase,
    private val deleteUseCase: DeleteTranslationUseCase



): ViewModel() {

    private val _history = MutableLiveData<List<TranslationResult>>(emptyList())
    val history: LiveData<List<TranslationResult>> = _history



    fun lodeHistory(){

        viewModelScope.launch {

            val res = getHistoryUseCase()
            res.onSuccess { _history.postValue(it) }
            res.onFailure { _history.postValue(emptyList()) }

        }

    }


    fun delete(item: TranslationResult) {
        viewModelScope.launch {
            deleteUseCase(item)
            lodeHistory() // refresh history after deletion
        }
    }



}