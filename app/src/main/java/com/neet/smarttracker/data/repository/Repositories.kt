package com.neet.smarttracker.data.repository

import com.neet.smarttracker.data.db.dao.*
import com.neet.smarttracker.data.db.entity.*
import com.neet.smarttracker.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class SubjectRepository @Inject constructor(
    private val subjectDao: SubjectDao
) {
    fun getSubjectsByClass(classLevel: Int): Flow<List<Subject>> =
        subjectDao.getSubjectsByClass(classLevel).map { entities ->
            entities.map { it.toModel() }
        }

    fun getSubjectById(id: Long): Flow<Subject?> =
        subjectDao.getSubjectById(id).map { it?.toModel() }

    suspend fun insertSubject(subject: Subject) =
        subjectDao.insertSubject(subject.toEntity())

    suspend fun updateSubject(subject: Subject) =
        subjectDao.updateSubject(subject.toEntity())

    suspend fun deleteSubject(subject: Subject) =
        subjectDao.deleteSubject(subject.toEntity())

    private fun SubjectEntity.toModel() = Subject(
        id, name, class_level, color, total_chapters, completed_chapters
    )

    private fun Subject.toEntity() = SubjectEntity(
        id, name, classLevel, color, totalChapters, completedChapters
    )
}

class ChapterRepository @Inject constructor(
    private val chapterDao: ChapterDao
) {
    fun getChaptersBySubject(subjectId: Long): Flow<List<Chapter>> =
        chapterDao.getChaptersBySubject(subjectId).map { entities ->
            entities.map { it.toModel() }
        }

    fun getChapterById(id: Long): Flow<Chapter?> =
        chapterDao.getChapterById(id).map { it?.toModel() }

    fun getBacklogChapters(): Flow<List<Chapter>> =
        chapterDao.getBacklogChapters().map { entities ->
            entities.map { it.toModel() }
        }

    fun getCompletedChapters(subjectId: Long): Flow<List<Chapter>> =
        chapterDao.getCompletedChapters(subjectId).map { entities ->
            entities.map { it.toModel() }
        }

    suspend fun markChapterComplete(id: Long) =
        chapterDao.markChapterComplete(id)

    suspend fun markChapterIncomplete(id: Long) =
        chapterDao.markChapterIncomplete(id)

    suspend fun updateActualHours(id: Long, hours: Float) =
        chapterDao.updateActualHours(id, hours)

    suspend fun insertChapter(chapter: Chapter) =
        chapterDao.insertChapter(chapter.toEntity())

    private fun ChapterEntity.toModel() = Chapter(
        id, subject_id, chapter_number, title, is_completed, completed_at,
        estimated_hours, actual_hours, is_backlog, priority, notes_count,
        created_at, updated_at
    )

    private fun Chapter.toEntity() = ChapterEntity(
        id, subjectId, chapterNumber, title, isCompleted, completedAt,
        estimatedHours, actualHours, isBacklog, priority, notesCount,
        createdAt, updatedAt
    )
}

class NotesRepository @Inject constructor(
    private val notesDao: NotesDao
) {
    fun getNotesByChapter(chapterId: Long): Flow<List<Notes>> =
        notesDao.getNotesByChapter(chapterId).map { entities ->
            entities.map { it.toModel() }
        }

    fun getNoteById(id: Long): Flow<Notes?> =
        notesDao.getNoteById(id).map { it?.toModel() }

    fun getPinnedNotes(): Flow<List<Notes>> =
        notesDao.getPinnedNotes().map { entities ->
            entities.map { it.toModel() }
        }

    fun getNoteCount(chapterId: Long): Flow<Int> =
        notesDao.getNoteCount(chapterId)

    suspend fun insertNote(notes: Notes) =
        notesDao.insertNote(notes.toEntity())

    suspend fun updateNote(notes: Notes) =
        notesDao.updateNote(notes.toEntity())

    suspend fun deleteNote(notes: Notes) =
        notesDao.deleteNote(notes.toEntity())

    private fun NotesEntity.toModel() = Notes(
        id, chapter_id, title, content, note_type, is_pinned,
        tags.split(",").filter { it.isNotEmpty() },
        created_at, updated_at
    )

    private fun Notes.toModel() = NotesEntity(
        id, chapterId, title, content, noteType, isPinned,
        tags.joinToString(","),
        createdAt, updatedAt
    )
}

class DailyTaskRepository @Inject constructor(
    private val dailyTaskDao: DailyTaskDao
) {
    fun getTasksByDate(date: LocalDate): Flow<List<DailyTask>> =
        dailyTaskDao.getTasksByDate(date).map { entities ->
            entities.map { it.toModel() }
        }

    fun getPendingTasksByDate(date: LocalDate): Flow<List<DailyTask>> =
        dailyTaskDao.getPendingTasksByDate(date).map { entities ->
            entities.map { it.toModel() }
        }

    fun getTotalPlannedHours(date: LocalDate): Flow<Float> =
        dailyTaskDao.getTotalPlannedHours(date).map { it ?: 0f }

    suspend fun completeTask(id: Long) =
        dailyTaskDao.completeTask(id)

    suspend fun insertTask(task: DailyTask) =
        dailyTaskDao.insertTask(task.toEntity())

    private fun DailyTaskEntity.toModel() = DailyTask(
        id, chapter_id, task_date, planned_hours, actual_hours,
        is_completed, completed_at
    )

    private fun DailyTask.toEntity() = DailyTaskEntity(
        id, chapterId, taskDate, plannedHours, actualHours,
        isCompleted, completedAt
    )
}

class BacklogRepository @Inject constructor(
    private val backlogDao: BacklogDao
) {
    fun getPendingBacklogItems(): Flow<List<BacklogItem>> =
        backlogDao.getPendingBacklogItems().map { entities ->
            entities.map { it.toModel() }
        }

    fun getPendingBacklogCount(): Flow<Int> =
        backlogDao.getPendingBacklogCount()

    suspend fun clearBacklogItem(id: Long) =
        backlogDao.clearBacklogItem(id)

    suspend fun insertBacklogItem(item: BacklogItem) =
        backlogDao.insertBacklogItem(item.toEntity())

    private fun BacklogItemEntity.toModel() = BacklogItem(
        id, chapter_id, priority, reason, target_completion_date,
        is_cleared, cleared_at
    )

    private fun BacklogItem.toEntity() = BacklogItemEntity(
        id, chapterId, priority, reason, targetCompletionDate,
        isCleared, clearedAt
    )
}

class ProgressRepository @Inject constructor(
    private val progressDao: ProgressDao
) {
    fun getProgressBySubject(subjectId: Long): Flow<List<Progress>> =
        progressDao.getProgressBySubject(subjectId).map { entities ->
            entities.map { it.toModel() }
        }

    fun getAverageStudyHours(subjectId: Long): Flow<Float> =
        progressDao.getAverageStudyHours(subjectId).map { it ?: 0f }

    suspend fun insertProgress(progress: Progress) =
        progressDao.insertProgress(progress.toEntity())

    private fun ProgressEntity.toModel() = Progress(
        id, subject_id, record_date, chapters_completed, total_chapters,
        study_hours, revision_count
    )

    private fun Progress.toEntity() = ProgressEntity(
        id, subjectId, recordDate, chaptersCompleted, totalChapters,
        studyHours, revisionCount
    )
}

class ReminderRepository @Inject constructor(
    private val reminderDao: ReminderDao
) {
    fun getRemindersByDate(date: LocalDate): Flow<List<Reminder>> =
        reminderDao.getRemindersByDate(date).map { entities ->
            entities.map { it.toModel() }
        }

    fun getUpcomingReminders(date: LocalDate): Flow<List<Reminder>> =
        reminderDao.getUpcomingReminders(date).map { entities ->
            entities.map { it.toModel() }
        }

    suspend fun insertReminder(reminder: Reminder) =
        reminderDao.insertReminder(reminder.toEntity())

    private fun ReminderEntity.toModel() = Reminder(
        id, chapter_id, reminder_type, reminder_date, reminder_time,
        message, is_completed
    )

    private fun Reminder.toEntity() = ReminderEntity(
        id, chapterId, reminderType, reminderDate, reminderTime,
        message, isCompleted
    )
}

class UserPreferenceRepository @Inject constructor(
    private val userPreferenceDao: UserPreferenceDao
) {
    fun getPreferences(): Flow<UserPreferences?> =
        userPreferenceDao.getPreferences().map { it?.toModel() }

    suspend fun updatePreferences(preferences: UserPreferences) =
        userPreferenceDao.updatePreference(preferences.toEntity())

    private fun UserPreferenceEntity.toModel() = UserPreferences(
        selected_class, selected_subjects.split(",").filter { it.isNotEmpty() },
        theme_mode, notification_enabled, pomodoro_duration,
        short_break_duration, long_break_duration, daily_study_goal
    )

    private fun UserPreferences.toEntity() = UserPreferenceEntity(
        selectedClass = selectedClass,
        selected_subjects = selectedSubjects.joinToString(","),
        theme_mode = themeMode,
        notification_enabled = notificationEnabled,
        pomodoro_duration = pomodoroDuration,
        short_break_duration = shortBreakDuration,
        long_break_duration = longBreakDuration,
        daily_study_goal = dailyStudyGoal
    )
}
