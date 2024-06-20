package com.example.cocktail_db.di

import android.app.Application
import androidx.room.Room
import com.example.cocktail_db.core.Constants
import com.example.cocktail_db.data.data_source.FavCocktailDatabase
import com.example.cocktail_db.data.remote.AuthInterceptor
import com.example.cocktail_db.data.remote.CocktailDbApi
import com.example.cocktail_db.data.repository.CocktailDbRepositoryImpl
import com.example.cocktail_db.data.repository.FavCocktailRepositoryImpl
import com.example.cocktail_db.domain.repository.CocktailDbRepository
import com.example.cocktail_db.domain.repository.FavCocktailRepository
import com.example.cocktail_db.domain.use_case.cocktail_db_use_case.CocktailDbUseCases
import com.example.cocktail_db.domain.use_case.cocktail_db_use_case.GetCategoriesUseCase
import com.example.cocktail_db.domain.use_case.cocktail_db_use_case.GetCocktailByIdUseCase
import com.example.cocktail_db.domain.use_case.cocktail_db_use_case.GetCocktailsByCategoryUseCase
import com.example.cocktail_db.domain.use_case.cocktail_db_use_case.GetRandomCocktailUseCase
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.AddFavCocktailUseCase
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.DeleteFavCocktailUseCase
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.FavCocktailUseCases
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.GetFavCocktailByIdUseCase
import com.example.cocktail_db.domain.use_case.fav_cocktails_use_case.GetFavCocktailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

		@Provides
		@Singleton
		fun provideFavCocktailDatabase(app: Application): FavCocktailDatabase {
				return Room.databaseBuilder(
						app,
						FavCocktailDatabase::class.java,
						FavCocktailDatabase.DATABASE_NAME
				).build()
		}

		@Provides
		@Singleton
		fun provideFavCocktailRepository(db: FavCocktailDatabase): FavCocktailRepository {
				return FavCocktailRepositoryImpl(db.favCocktailDao)
		}

		@Provides
		@Singleton
		fun provideFavCocktailUseCases(repository: FavCocktailRepository): FavCocktailUseCases {
				return FavCocktailUseCases(
						getFavCocktailsUseCase = GetFavCocktailsUseCase(repository),
						getFavCocktailByIdUseCase = GetFavCocktailByIdUseCase(repository),
						addFavCocktailUseCase = AddFavCocktailUseCase(repository),
						deleteFavCocktailUseCase = DeleteFavCocktailUseCase(repository)
				)
		}

		@Provides
		@Singleton
		fun provideCocktailDbApi(okHttpClient: OkHttpClient): CocktailDbApi {
				return Retrofit.Builder()
						.baseUrl(Constants.API_COCKTAIL_DB_URL)
						.client(okHttpClient)
						.addConverterFactory(MoshiConverterFactory.create())
						.build()
						.create(CocktailDbApi::class.java)
		}

		@Provides
		@Singleton
		fun provideCocktailDbRepository(api: CocktailDbApi): CocktailDbRepository {
				return CocktailDbRepositoryImpl(api)
		}

		@Provides
		@Singleton
		fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
				return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
		}

		@Provides
		@Singleton
		fun provideCocktailDbUseCases(repository: CocktailDbRepository): CocktailDbUseCases {
				return CocktailDbUseCases(
						getCocktailsByCategoryUseCase = GetCocktailsByCategoryUseCase(repository),
						getRandomCocktailUseCase = GetRandomCocktailUseCase(repository),
						getCategoriesUseCase = GetCategoriesUseCase(repository),
						getCocktailByIdUseCase = GetCocktailByIdUseCase(repository)
				)
		}

}