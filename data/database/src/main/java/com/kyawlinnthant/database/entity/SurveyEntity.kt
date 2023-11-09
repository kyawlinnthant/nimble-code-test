package com.kyawlinnthant.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kyawlinnthant.database.entity.SurveyEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class SurveyEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = PRIMARY_ID)
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val createdAt: String,
    val activeAt: String
) {
    companion object {
        const val TABLE_NAME = "survey_table"
        const val PRIMARY_ID = "survey_id"
    }
}
