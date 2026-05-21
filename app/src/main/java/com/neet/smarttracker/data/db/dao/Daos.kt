package com.neet.smarttracker.data.db.dao

import androidx.room.*
import com.neet.smarttracker.data.db.entity.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface SubjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: SubjectEntity): Long

    @Query("SELECT * FROM subjects WHERE class_level = :classLevel ORDER BY name")
    fun getSubjectsByClass(classLevel: Int): Flow<List<SubjectEntity>>

    @Query("SELECT * FROM subjects WHERE id = :id")
    fun getSubjectById(id: Long): Flow<SubjectEntity?>

    @Update
    suspend fun updateSubject(subject: SubjectEntity)

    @Delete
    suspend fun deleteSubject(subject: SubjectEntity)
}

@Dao
interface ChapterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(chapter: ChapterEntity): Long

    @Query("SELECT * FROM chapters WHERE subject_id = :subjectId ORDER BY chapter_number")
    fun getChaptersBySubject(subjectId: Long): Flow<List<ChapterEntity>>

    @Query("SELECT * FROM chapters WHERE id = :id")
    fun getChapterById(id: Long): Flow<ChapterEntity?>

    @Query("SELECT * FROM chapters WHERE is_completed = 0 AND is_backlog = 1 ORDER BY priority DESC")
    fun getBacklogChapters(): Flow<List<ChapterEntity>>

    @Query("SELECT * FROM chapters WHERE subject_id = :subjectId AND is_completed = 1")
    fun getCompletedChapters(subjectId: Long): Flow<List<ChapterEntity>>

    @Query("UPDATE chapters SET is_completed = 1, completed_at = datetime('now') WHERE id = :id")
    suspend fun markChapterComplete(id: Long)

    @Query("UPDATE chapters SET is_completed = 0, completed_at = NULL WHERE id = :id")
    suspend fun markChapterIncomplete(id: Long)

    @Query("UPDATE chapters SET actual_hours = :hours WHERE id = :id")
    suspend fun updateActualHours(id: Long, hours: Float)

    @Query("UPDATE chapters SET is_backlog = :isBacklog WHERE id = :id")
    suspend fun updateBacklogStatus(id: Long, isBacklog: Boolean)

    @Update
    suspend fun updateChapter(chapter: ChapterEntity)

    @Delete
    suspend fun deleteChapter(chapter: ChapterEntity)
}

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NotesEntity): Long

    @Query("SELECT * FROM notes WHERE chapter_id = :chapterId ORDER BY updated_at DESC")
    fun getNotesByChapter(chapterId: Long): Flow<List<NotesEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Long): Flow<NotesEntity?>

    @Query("SELECT * FROM notes WHERE is_pinned = 1 ORDER BY updated_at DESC")
    fun getPinnedNotes(): Flow<List<NotesEntity>>

    @Query("SELECT COUNT(*) FROM notes WHERE chapter_id = :chapterId")
    fun getNoteCount(chapterId: Long): Flow<Int>

    @Update
    suspend fun updateNote(note: NotesEntity)

    @Delete
    suspend fun deleteNote(note: NotesEntity)

    @Query("DELETE FROM notes WHERE chapter_id = :chapterId")
    suspend fun deleteNotesByChapter(chapterId: Long)
}

@Dao
interface DailyTaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: DailyTaskEntity): Long

    @Query("SELECT * FROM daily_tasks WHERE task_date = :date ORDER BY is_completed")
    fun getTasksByDate(date: LocalDate): Flow<List<DailyTaskEntity>>

    @Query("SELECT * FROM daily_tasks WHERE task_date = :date AND is_completed = 0")
    fun getPendingTasksByDate(date: LocalDate): Flow<List<DailyTaskEntity>>

    @Query("UPDATE daily_tasks SET is_completed = 1, completed_at = datetime('now') WHERE id = :id")
    suspend fun completeTask(id: Long)

    @Query("UPDATE daily_tasks SET actual_hours = :hours WHERE id = :id")
    suspend fun updateActualTaskHours(id: Long, hours: Float)

    @Update
    suspend fun updateTask(task: DailyTaskEntity)

    @Delete
    suspend fun deleteTask(task: DailyTaskEntity)

    @Query("SELECT SUM(planned_hours) FROM daily_tasks WHERE task_date = :date")
    fun getTotalPlannedHours(date: LocalDate): Flow<Float?>
}

@Dao
interface BacklogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBacklogItem(item: BacklogItemEntity): Long

    @Query("SELECT * FROM backlog_items WHERE is_cleared = 0 ORDER BY priority DESC, created_at ASC")
    fun getPendingBacklogItems(): Flow<List<BacklogItemEntity>>

    @Query("SELECT COUNT(*) FROM backlog_items WHERE is_cleared = 0")
    fun getPendingBacklogCount(): Flow<Int>

    @Query("UPDATE backlog_items SET is_cleared = 1, cleared_at = datetime('now') WHERE id = :id")
    suspend fun clearBacklogItem(id: Long)

    @Update
    suspend fun updateBacklogItem(item: BacklogItemEntity)

    @Delete
    suspend fun deleteBacklogItem(item: BacklogItemEntity)
}

@Dao
interface ProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: ProgressEntity): Long

    @Query("SELECT * FROM progress_records WHERE subject_id = :subjectId ORDER BY record_date DESC")
    fun getProgressBySubject(subjectId: Long): Flow<List<ProgressEntity>>

    @Query("SELECT * FROM progress_records WHERE subject_id = :subjectId AND record_date = :date")
    fun getProgressForDate(subjectId: Long, date: LocalDate): Flow<ProgressEntity?>

    @Query("SELECT AVG(study_hours) FROM progress_records WHERE subject_id = :subjectId")
    fun getAverageStudyHours(subjectId: Long): Flow<Float?>

    @Update
    suspend fun updateProgress(progress: ProgressEntity)

    @Delete
    suspend fun deleteProgress(progress: ProgressEntity)
}

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderEntity): Long

    @Query("SELECT * FROM reminders WHERE reminder_date = :date ORDER BY reminder_time")
    fun getRemindersByDate(date: LocalDate): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE reminder_date >= :date AND is_completed = 0 ORDER BY reminder_date, reminder_time")
    fun getUpcomingReminders(date: LocalDate): Flow<List<ReminderEntity>>

    @Query("UPDATE reminders SET is_completed = 1 WHERE id = :id")
    suspend fun markReminderComplete(id: Long)

    @Update
    suspend fun updateReminder(reminder: ReminderEntity)

    @Delete
    suspend fun deleteReminder(reminder: ReminderEntity)
}

@Dao
interface UserPreferenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreference(preference: UserPreferenceEntity)

    @Query("SELECT * FROM user_preferences WHERE id = 0")
    fun getPreferences(): Flow<UserPreferenceEntity?>

    @Update
    suspend fun updatePreference(preference: UserPreferenceEntity)
}
