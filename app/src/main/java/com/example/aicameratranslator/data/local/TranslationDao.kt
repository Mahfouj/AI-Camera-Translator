package com.example.aicameratranslator.data.local


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aicameratranslator.data.model.TranslationResult
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(result: TranslationResult)


    @Query("SELECT * FROM translation_history ORDER BY id DESC")
    suspend fun getAll(): List<TranslationResult>

    @Query("SELECT * FROM translation_history ORDER BY id DESC")
    fun getAllFlow(): Flow<List<TranslationResult>>

    @Query("SELECT * FROM translation_history WHERE originalText = :text AND targetLang = :targetLang LIMIT 1")
    suspend fun findByTextAndLang(text: String, targetLang: String): TranslationResult?

    @Delete
    suspend fun delete(result: TranslationResult)

    @Query("DELETE FROM translation_history")
    suspend fun clearAll()

}