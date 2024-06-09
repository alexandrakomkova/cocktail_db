package com.example.cocktail_db.di

import com.example.cocktail_db.domain.use_case.cocktail_use_case.CocktailUseCases
import com.example.cocktail_db.domain.use_case.cocktail_use_case.GetCategoriesUseCase
import com.example.cocktail_db.domain.use_case.cocktail_use_case.GetCocktailByIdUseCase
import com.example.cocktail_db.domain.use_case.cocktail_use_case.GetCocktailsByCategoryUseCase
import com.example.cocktail_db.domain.use_case.cocktail_use_case.GetRandomCocktailUseCase
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.AddFavCocktailUseCase
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.DeleteFavCocktailUseCase
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.FavCocktailUseCases
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.GetFavCocktailByIdUseCase
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.GetFavCocktailsUseCase
import org.koin.dsl.module

val domainModuleCocktailDb = module {

		// CocktailDb
		factory<CocktailUseCases> {
				CocktailUseCases(
						getRandomCocktailUseCase = get(),
						getCategoriesUseCase = get(),
						getCocktailsByCategoryUseCase = get(),
						getCocktailByIdUseCase = get()
				)
		}

		factory<GetRandomCocktailUseCase> {
				GetRandomCocktailUseCase(repository = get())
		}

		factory<GetCategoriesUseCase> {
				GetCategoriesUseCase(repository = get())
		}

		factory<GetCocktailsByCategoryUseCase> {
				GetCocktailsByCategoryUseCase(repository = get())
		}

		factory<GetCocktailByIdUseCase> {
				GetCocktailByIdUseCase(repository = get())
		}

}


val domainModuleFavCocktails = module {

		// Favourites cocktails
		factory<FavCocktailUseCases> {
				FavCocktailUseCases(
						getFavCocktailsUseCase = get(),
						getFavCocktailByIdUseCase = get(),
						addFavCocktailUseCase = get(),
						deleteFavCocktailUseCase = get()
				)
		}

		factory<GetFavCocktailsUseCase> {
				GetFavCocktailsUseCase(repository = get())
		}
		factory<GetFavCocktailByIdUseCase> {
				GetFavCocktailByIdUseCase(repository = get())
		}
		factory<AddFavCocktailUseCase> {
				AddFavCocktailUseCase(repository = get())
		}
		factory<DeleteFavCocktailUseCase> {
				DeleteFavCocktailUseCase(repository = get())
		}


}