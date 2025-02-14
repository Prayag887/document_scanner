package com.ag.documentscanner.data.local.converters

import androidx.room.TypeConverter
import java.util.Date

class DataTypeConverter {
    @TypeConverter
    fun fromTimeStamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimeStamp(date: Date?): Long? = date?.time
}
