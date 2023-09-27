package com.bhdrburak.vbtproject.di

import android.content.Context
import androidx.room.Room
import com.bhdrburak.vbtproject.R
import com.bhdrburak.vbtproject.common.Constant.BASE_URL
import com.bhdrburak.vbtproject.data.AppDatabase
import com.bhdrburak.vbtproject.data.RetrofitAPI
import com.bhdrburak.vbtproject.data.UserDao
import com.bhdrburak.vbtproject.data.repo.UserRepository
import com.bhdrburak.vbtproject.data.repo.UserRepositoryInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java,"VBTDb").build()

    @Singleton
    @Provides
    fun injectDao(
        database: AppDatabase
    ) = database.userDao()


    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(dao : UserDao, api: RetrofitAPI) = UserRepository(dao,api) as UserRepositoryInterface

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide
        .with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )

}