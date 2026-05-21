package com.neet.smarttracker.data.model

import java.time.LocalDate
import java.time.LocalDateTime

// Domain Models (Clean Layer)
data class Subject(
    val id: Long,
    val name: String,
    val classLevel: Int,
    val color: String,
    val totalChapters: Int,
    val completedChapters: Int,
    val completionPercentage: Float = if (totalChapters > 0) (completedChapters.toFloat() / totalChapters) * 100 else 0f
)

data class Chapter(
    val id: Long,
    val subjectId: Long,
    val chapterNumber: Int,
    val title: String,
    val isCompleted: Boolean,
    val completedAt: LocalDateTime?,
    val estimatedHours: Float,
    val actualHours: Float,
    val isBacklog: Boolean,
    val priority: Int,
    val notesCount: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

data class Notes(
    val id: Long,
    val chapterId: Long,
    val title: String,
    val content: String,
    val noteType: String,
    val isPinned: Boolean,
    val tags: List<String>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

data class DailyTask(
    val id: Long,
    val chapterId: Long,
    val taskDate: LocalDate,
    val plannedHours: Float,
    val actualHours: Float,
    val isCompleted: Boolean,
    val completedAt: LocalDateTime?
)

data class BacklogItem(
    val id: Long,
    val chapterId: Long,
    val priority: Int,
    val reason: String,
    val targetCompletionDate: LocalDate?,
    val isCleared: Boolean,
    val clearedAt: LocalDateTime?
)

data class Progress(
    val id: Long,
    val subjectId: Long,
    val recordDate: LocalDate,
    val chaptersCompleted: Int,
    val totalChapters: Int,
    val studyHours: Float,
    val revisionCount: Int
)

data class Reminder(
    val id: Long,
    val chapterId: Long?,
    val reminderType: String,
    val reminderDate: LocalDate,
    val reminderTime: String,
    val message: String,
    val isCompleted: Boolean
)

data class UserPreferences(
    val selectedClass: Int,
    val selectedSubjects: List<String>,
    val themeMode: String,
    val notificationEnabled: Boolean,
    val pomodoroDuration: Int,
    val shortBreakDuration: Int,
    val longBreakDuration: Int,
    val dailyStudyGoal: Float
)
