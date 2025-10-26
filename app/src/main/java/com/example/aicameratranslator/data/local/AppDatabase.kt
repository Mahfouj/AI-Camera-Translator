package com.example.aicameratranslator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aicameratranslator.data.model.TranslationResult

@Database(entities = [TranslationResult::class], version = 1, exportSchema = false )
abstract class AppDatabase : RoomDatabase() {
    abstract fun translationDao(): TranslationDao
}