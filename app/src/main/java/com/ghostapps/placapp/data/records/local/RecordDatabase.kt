package com.ghostapps.placapp.data.records.local

import androidx.room.*
import com.ghostapps.placapp.data.records.RecordEntity

@Database(entities = [RecordEntity::class], version = 1)
abstract class RecordDatabase: RoomDatabase() {
    abstract fun recordDao(): RecordDao
}