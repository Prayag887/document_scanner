package com.ag.documentscanner.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ag.documentscanner.data.local.converters.DataTypeConverter
import com.ag.documentscanner.data.local.dao.PdfDao
import com.ag.documentscanner.data.model.Pdf

@Database(entities = [Pdf::class], version = 1, exportSchema = false)
@TypeConverters(DataTypeConverter::class)
abstract class PdfDatabase : RoomDatabase() {
    abstract val pdfDao: PdfDao

    companion object {
        @Volatile
        private var INSTANCE: PdfDatabase? = null

        fun getInstance(context: Context): PdfDatabase {
            synchronized(this) {
                return INSTANCE ?: Room
                    .databaseBuilder(
                        context.applicationContext,
                        PdfDatabase::class.java,
                        "pdf_db",
                    ).build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}
