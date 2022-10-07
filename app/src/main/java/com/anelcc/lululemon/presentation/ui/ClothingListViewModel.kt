package com.anelcc.lululemon.presentation.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anelcc.lululemon.data.ClothingSortType
import com.anelcc.lululemon.domain.Clothing
import com.anelcc.lululemon.domain.ClothingUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClothingListViewModel @Inject constructor(
    private val userCase:  ClothingUserCase ) : ViewModel() {

    val clothingList = MutableLiveData<List<Clothing>>()

    fun fetchData() {
        viewModelScope.launch {
            val _clothingList =  userCase.getClothingList()
            Log.d(TAG, "fetchData: ${_clothingList.size}")
            clothingList.postValue(_clothingList)
        }
    }

    fun insertClothes(clothing: Clothing) {
        viewModelScope.launch {
            userCase.onInsertClothes(clothing)
            val _clothingList =  userCase.getClothingList()
            Log.d(TAG, "fetchData: ${_clothingList.size}")
            clothingList.postValue(_clothingList)
        }
    }

    fun sortByType(clothingSortType: ClothingSortType) {
        when (clothingSortType) {
            ClothingSortType.ALPHA-> {
                val sortedList = clothingList.value?.let { list ->
                    list.sortedBy { it.name }
                }
                sortedList?.let { clothingList.value = it }
                Log.d(TAG, "sortByType: ALPHA ")
            }
            ClothingSortType.TIME-> {
                val sortedList = clothingList.value?.let { list ->
                    list.sortedBy { it.date }
                }
                sortedList?.let { clothingList.value = it }
                Log.d(TAG, "sortByType: TIME ")
            }
        }
    }

    companion object {
        private val TAG = ClothingListViewModel::class.simpleName
    }
}