package com.ghostapps.placapp.data.records

import androidx.room.*

@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recordEntity: RecordEntity)

    @Delete
    fun delete(recordEntity: RecordEntity)

    @Query("SELECT * FROM ${RecordEntity.TABLE_NAME}")
    fun getRecords(): List<RecordEntity>
}