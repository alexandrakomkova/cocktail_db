package com.example.cocktail_db.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//abstract class BaseFlowUseCase<in Params, out Result> {
//
////		internal abstract fun invoke(params: Params): Flow<Result>
////
////		operator fun invoke(params: Params): Flow<Result> {
////				return invoke(params)
////						.catch { e ->
////								// emit(handleError(e))
////						}
////		}
//
//		operator fun invoke(params: Params): Flow<Resource> = flow {
//				try {
//						emit(Resource.Loading())
//						val cocktailsCategory = repository.getCategories().map { it.toCategory() }
//						emit(Resource.Success(cocktailsCategory))
//				} catch (e: IOException) {
//						emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
//				}
//		}
//
////		private fun handleError(exception: Throwable): Result {
////				return // здесь можно вернуть объект Result с информацией об ошибке
////		}
//}

abstract class IResultUseCase<in P, R> {
		operator fun invoke(params: P): Flow<R> = flow {
				emit(doWork(params))

		}

		protected abstract suspend fun doWork(params: P): R
}

//class GetCategoryUseCase(
//		private val repository: CocktailDbRepository
//): KoinComponent, BaseFlowUseCase<String, Result<Category>>() {
//		suspend fun doWork(): Resource<List<Category>> {
//				return Resource.Success(repository.getCategories().map { it.toCategory() })
//		}
//}

