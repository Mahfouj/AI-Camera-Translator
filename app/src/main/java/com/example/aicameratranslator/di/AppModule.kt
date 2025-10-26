package com.example.aicameratranslator.di


import android.content.Context
import androidx.room.Room
import com.example.aicameratranslator.data.api.ApiConstants
import com.example.aicameratranslator.data.api.TranslationApiService
import com.example.aicameratranslator.data.local.AppDatabase
import com.example.aicameratranslator.data.local.TranslationDao
import com.example.aicameratranslator.data.repository.TranslationRepositoryImpl
import com.example.aicameratranslator.domain.repository.TranslationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            // Use BODY only in debug builds; avoid in production
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTranslationApiService(retrofit: Retrofit): TranslationApiService {
        return retrofit.create(TranslationApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            // For development: fallbackToDestructiveMigration() can speed dev iterations.
            // For production, provide proper migrations instead of destructive fallback.
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTranslationDao(database: AppDatabase): TranslationDao {
        return database.translationDao()
    }

    @Provides
    @Singleton
    fun provideTranslationRepository(
        apiService: TranslationApiService,
        dao: TranslationDao,
        @ApplicationContext context: Context
    ): TranslationRepository {
        return TranslationRepositoryImpl(apiService, dao, context)
    }
}