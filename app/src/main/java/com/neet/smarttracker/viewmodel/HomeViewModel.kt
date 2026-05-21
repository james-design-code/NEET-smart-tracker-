package com.neet.smarttracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neet.smarttracker.data.model.Subject
import com.neet.smarttracker.data.repository.*
import com.neet.smarttracker.utils.CurriculumData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val subjectRepository: SubjectRepository,
    private val chapterRepository: ChapterRepository,
    private val backlogRepository: BacklogRepository,
    private val dailyTaskRepository: DailyTaskRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel() {

    private val _selectedClass = MutableStateFlow(12)
    val selectedClass: StateFlow<Int> = _selectedClass.asStateFlow()

    val subjects: StateFlow<List<Subject>> = selectedClass.flatMapLatest { classLevel ->
        subjectRepository.getSubjectsByClass(classLevel)
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val backlogCount: StateFlow<Int> = backlogRepository.getPendingBacklogCount()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val todayProgress: StateFlow<String> = MutableStateFlow("0%").asStateFlow()

    init {
        // Load user preferences
        viewModelScope.launch {
            userPreferenceRepository.getPreferences().firstOrNull()?.let {
                _selectedClass.value = it.selectedClass
            }
        }
    }
}
