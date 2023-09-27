package com.bhdrburak.mobillium.di

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bhdrburak.mobillium.R
import com.bhdrburak.mobillium.common.Constant.BASE_URL
import com.bhdrburak.mobillium.data.api.RequestInterceptor
import com.bhdrburak.mobillium.data.api.RetrofitAPI
import com.bhdrburak.mobillium.data.repo.FavMovieRepository
import com.bhdrburak.mobillium.data.repo.FavMovieRepositoryImpl
import com.bhdrburak.mobillium.data.repo.MovieRepository
import com.bhdrburak.mobillium.data.repo.MovieRepositoryImpl
import com.bhdrburak.mobillium.data.room.AppDatabase
import com.bhdrburak.mobillium.data.room.MovieDAO
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "MovieDb").build()

    @Singleton
    @Provides
    fun injectDao(database: AppDatabase) = database.movieDao()

    @Singleton
    @Provides
    fun injectHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun injectRetrofitAPI(@NonNull okHttpClient: OkHttpClient): RetrofitAPI {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectMovieRepo(movieDao: MovieDAO, api: RetrofitAPI) =
        MovieRepositoryImpl(movieDao, api) as MovieRepository


    @Singleton
    @Provides
    fun injectFavRepo(movieDao: MovieDAO) =
        FavMovieRepositoryImpl(movieDao) as FavMovieRepository

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        )


}