package com.neet.smarttracker.di

import android.content.Context
import com.neet.smarttracker.data.db.AppDatabase
import com.neet.smarttracker.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideSubjectRepository(db: AppDatabase): SubjectRepository =
        SubjectRepository(db.subjectDao())

    @Singleton
    @Provides
    fun provideChapterRepository(db: AppDatabase): ChapterRepository =
        ChapterRepository(db.chapterDao())

    @Singleton
    @Provides
    fun provideNotesRepository(db: AppDatabase): NotesRepository =
        NotesRepository(db.notesDao())

    @Singleton
    @Provides
    fun provideDailyTaskRepository(db: AppDatabase): DailyTaskRepository =
        DailyTaskRepository(db.dailyTaskDao())

    @Singleton
    @Provides
    fun provideBacklogRepository(db: AppDatabase): BacklogRepository =
        BacklogRepository(db.backlogDao())

    @Singleton
    @Provides
    fun provideProgressRepository(db: AppDatabase): ProgressRepository =
        ProgressRepository(db.progressDao())

    @Singleton
    @Provides
    fun provideReminderRepository(db: AppDatabase): ReminderRepository =
        ReminderRepository(db.reminderDao())

    @Singleton
    @Provides
    fun provideUserPreferenceRepository(db: AppDatabase): UserPreferenceRepository =
        UserPreferenceRepository(db.userPreferenceDao())
}
