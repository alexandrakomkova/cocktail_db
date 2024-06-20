package com.example.cocktail_db.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(): Interceptor {
		override fun intercept(chain: Interceptor.Chain): Response {
				var req = chain.request()
				Log.d("AuthInterceptor", req.url.toString())
				//val url = req.url.newBuilder().addQueryParameter("APPID", "your_key_here").build()
				//req = req.newBuilder().url(url).build()
				return chain.proceed(req)
		}
}