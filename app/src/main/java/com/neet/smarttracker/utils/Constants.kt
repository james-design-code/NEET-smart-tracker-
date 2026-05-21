package com.neet.smarttracker.utils

object Constants {
    const val DATABASE_NAME = "neet_tracker_db"
    const val DEFAULT_CLASS_LEVEL = 12
    const val DEFAULT_DAILY_GOAL = 3f // hours
    const val DEFAULT_POMODORO_DURATION = 25 // minutes
    const val DEFAULT_SHORT_BREAK = 5 // minutes
    const val DEFAULT_LONG_BREAK = 15 // minutes

    object NotesType {
        const val NOTE = "note"
        const val FORMULA = "formula"
        const val CONCEPT = "concept"
        const val DOUBT = "doubt"
        const val REVISION = "revision"
    }

    object ReminderType {
        const val STUDY = "study"
        const val REVISION = "revision"
        const val DEADLINE = "deadline"
        const val EXAM = "exam"
    }

    object Priority {
        const val LOW = 0
        const val MEDIUM = 1
        const val HIGH = 2
    }
}
