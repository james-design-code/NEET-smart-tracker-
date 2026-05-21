package com.neet.smarttracker.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(
    tableName = "subjects",
    indices = [Index(value = ["class_level"])]
)
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,                    // Physics, Chemistry, Biology
    val class_level: Int,                 // 11, 12, or 13 (dropper)
    val color: String = "#FF6B35",       // Hex color for UI
    val total_chapters: Int = 0,
    val completed_chapters: Int = 0,
    val created_at: LocalDateTime = LocalDateTime.now(),
    val updated_at: LocalDateTime = LocalDateTime.now()
)

@Entity(
    tableName = "chapters",
    foreignKeys = [
        ForeignKey(
            entity = SubjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["subject_id"]),
        Index(value = ["chapter_number"])
    ]
)
data class ChapterEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val subject_id: Long,
    val chapter_number: Int,              // 1-indexed
    val title: String,
    val is_completed: Boolean = false,
    val completed_at: LocalDateTime? = null,
    val estimated_hours: Float = 0f,     // Estimated study time
    val actual_hours: Float = 0f,        // Actual study time
    val is_backlog: Boolean = false,     // Pending task
    val priority: Int = 0,                // 0: Low, 1: Medium, 2: High
    val notes_count: Int = 0,
    val created_at: LocalDateTime = LocalDateTime.now(),
    val updated_at: LocalDateTime = LocalDateTime.now()
)

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = ChapterEntity::class,
            parentColumns = ["id"],
            childColumns = ["chapter_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["chapter_id"])]
)
data class NotesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val chapter_id: Long,
    val title: String,
    val content: String,
    val note_type: String = "note",       // note, formula, concept, doubt, revision
    val is_pinned: Boolean = false,
    val tags: String = "",                // Comma-separated tags
    val created_at: LocalDateTime = LocalDateTime.now(),
    val updated_at: LocalDateTime = LocalDateTime.now()
)

@Entity(
    tableName = "daily_tasks",
    foreignKeys = [
        ForeignKey(
            entity = ChapterEntity::class,
            parentColumns = ["id"],
            childColumns = ["chapter_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["chapter_id"]),
        Index(value = ["task_date"])
    ]
)
data class DailyTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val chapter_id: Long,
    val task_date: LocalDate,
    val planned_hours: Float,
    val actual_hours: Float = 0f,
    val is_completed: Boolean = false,
    val completed_at: LocalDateTime? = null,
    val created_at: LocalDateTime = LocalDateTime.now(),
    val updated_at: LocalDateTime = LocalDateTime.now()
)

@Entity(
    tableName = "backlog_items",
    foreignKeys = [
        ForeignKey(
            entity = ChapterEntity::class,
            parentColumns = ["id"],
            childColumns = ["chapter_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["chapter_id"]),
        Index(value = ["priority"])
    ]
)
data class BacklogItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val chapter_id: Long,
    val priority: Int = 1,                 // 0: Low, 1: Medium, 2: High
    val reason: String = "",               // Why it's in backlog
    val target_completion_date: LocalDate? = null,
    val is_cleared: Boolean = false,
    val cleared_at: LocalDateTime? = null,
    val created_at: LocalDateTime = LocalDateTime.now(),
    val updated_at: LocalDateTime = LocalDateTime.now()
)

@Entity(
    tableName = "progress_records",
    foreignKeys = [
        ForeignKey(
            entity = SubjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["subject_id"]),
        Index(value = ["record_date"])
    ]
)
data class ProgressEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val subject_id: Long,
    val record_date: LocalDate,
    val chapters_completed: Int,
    val total_chapters: Int,
    val study_hours: Float,
    val revision_count: Int = 0,
    val created_at: LocalDateTime = LocalDateTime.now()
)

@Entity(
    tableName = "reminders",
    foreignKeys = [
        ForeignKey(
            entity = ChapterEntity::class,
            parentColumns = ["id"],
            childColumns = ["chapter_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["chapter_id"])]
)
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val chapter_id: Long? = null,         // Nullable for global reminders
    val reminder_type: String,             // study, revision, deadline, exam
    val reminder_date: LocalDate,
    val reminder_time: String,             // HH:mm format
    val message: String,
    val is_completed: Boolean = false,
    val created_at: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "user_preferences")
data class UserPreferenceEntity(
    @PrimaryKey val id: Int = 0,
    val selected_class: Int = 12,          // 11, 12, or 13
    val selected_subjects: String = "",   // Comma-separated: physics,chemistry,biology
    val theme_mode: String = "dark",
    val notification_enabled: Boolean = true,
    val pomodoro_duration: Int = 25,       // minutes
    val short_break_duration: Int = 5,
    val long_break_duration: Int = 15,
    val daily_study_goal: Float = 3f,      // hours
    val created_at: LocalDateTime = LocalDateTime.now(),
    val updated_at: LocalDateTime = LocalDateTime.now()
)
