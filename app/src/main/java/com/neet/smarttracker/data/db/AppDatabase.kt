package com.neet.smarttracker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.neet.smarttracker.data.db.converter.DateTimeConverter
import com.neet.smarttracker.data.db.dao.*
import com.neet.smarttracker.data.db.entity.*

@Database(
    entities = [
        SubjectEntity::class,
        ChapterEntity::class,
        NotesEntity::class,
        DailyTaskEntity::class,
        BacklogItemEntity::class,
        ProgressEntity::class,
        ReminderEntity::class,
        UserPreferenceEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun subjectDao(): SubjectDao
    abstract fun chapterDao(): ChapterDao
    abstract fun notesDao(): NotesDao
    abstract fun dailyTaskDao(): DailyTaskDao
    abstract fun backlogDao(): BacklogDao
    abstract fun progressDao(): ProgressDao
    abstract fun reminderDao(): ReminderDao
    abstract fun userPreferenceDao(): UserPreferenceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "neet_tracker_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
