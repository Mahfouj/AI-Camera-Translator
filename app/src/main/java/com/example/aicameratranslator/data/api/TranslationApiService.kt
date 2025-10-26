package com.example.aicameratranslator.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

data class TranslationRequest(
    val q: String,
    val source: String = "auto",
    val target: String
)

data class TranslationResponse(
    @SerializedName("translatedText") val translatedText: String
)
data class Language(
    val code: String,
    val name: String
)
interface TranslationApiService {
    @FormUrlEncoded
    @POST("translate")
    suspend fun translateText(
        @Field("q") text: String,
        @Field("source") source: String = "auto",
        @Field("target") target: String
    ): TranslationResponse

    @GET("languages")
    suspend fun getSupportedLanguages(): List<Language>


}