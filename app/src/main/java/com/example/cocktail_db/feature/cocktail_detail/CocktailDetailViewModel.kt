package com.example.cocktail_db.feature.cocktail_detail

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktail_db.core.Resource
import com.example.cocktail_db.domain.use_case.cocktail_db_use_case.CocktailDbUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
		private val cocktailDbUseCases: CocktailDbUseCases,
		savedStateHandle: SavedStateHandle
): ViewModel() {
		private val _state = mutableStateOf(CocktailDetailState())
		val state: State<CocktailDetailState> = _state

		private val cocktailId: String = savedStateHandle.get<String>(COCKTAIL_ID_SAVED_STATE_KEY)!!

		init {
				Log.d("CocktailDetailViewModel", cocktailId)
				getCocktailById()
		}

		private fun getCocktailById() {

				viewModelScope.launch() {
						cocktailDbUseCases.getCocktailByIdUseCase(cocktailId)
								.flowOn(Dispatchers.IO)
								.collect { result ->
										when (result) {
												is Resource.Loading -> {
														_state.value = CocktailDetailState(isLoading = true)
												}

												is Resource.Success -> {
														_state.value = CocktailDetailState(cocktails = result.data ?: emptyList())
												}

												is Resource.Error -> {
														_state.value = CocktailDetailState(
																error = result.message ?: "An unexpected error occurred"
														)
												}
										}
								}
				}
		}

		fun refresh() {
				getCocktailById()
		}

		companion object {
				private const val COCKTAIL_ID_SAVED_STATE_KEY = "cocktailId"
		}

}