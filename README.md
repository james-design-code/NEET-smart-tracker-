# NEET Smart Tracker

An offline-first Android app for NEET and Board exam students to track NCERT syllabus completion, daily study progress, manage backlogs, set reminders, and plan revisions.

## 🎯 Features

- ✅ **Offline-First Architecture** - Works 100% without internet
- ✅ **NCERT Curriculum** - Complete syllabi for Physics, Chemistry, Biology
- ✅ **Chapter Tracking** - Mark chapters complete with timestamps
- ✅ **Notes System** - Add notes, formulas, concepts, doubts per chapter
- ✅ **Backlog Manager** - Priority-based task management
- ✅ **Daily Planner** - Schedule and track daily study
- ✅ **Focus Timer** - Pomodoro-style study sessions
- ✅ **Calendar View** - Historical tracking
- ✅ **Analytics** - Progress metrics and consistency analysis
- ✅ **Reminders** - Study reminders with customizable timing
- ✅ **Dark Theme** - Eye-friendly, calming interface
- ✅ **Local Storage** - SQLite database for data privacy

## 🛠️ Tech Stack

- **UI**: Jetpack Compose
- **Database**: Room (SQLite)
- **State Management**: ViewModel + Flow/StateFlow
- **DI**: Hilt
- **Architecture**: MVVM + Repository Pattern
- **Language**: Kotlin

## 📱 Supported Classes

- Class 11
- Class 12
- Dropper

## 📚 Subject Coverage

### Physics (15 chapters)
Electric Charges and Fields, Electrostatic Potential and Capacitance, Current Electricity, Moving Charges and Magnetism, Magnetism and Matter, Electromagnetic Induction, Alternating Current, Electromagnetic Waves, Ray Optics and Optical Instruments, Wave Optics, Dual Nature of Radiation and Matter, Atoms, Nuclei, Semiconductor Electronics, Communication Systems

### Chemistry (16 chapters)
Solid State, Solutions, Electrochemistry, Chemical Kinetics, Surface Chemistry, General Principles and Processes of Isolation of Elements, The p-Block Elements, The d- and f-Block Elements, Coordination Compounds, Haloalkanes and Haloarenes, Alcohols, Phenols and Ethers, Aldehydes, Ketones and Carboxylic Acids, Organic Compounds Containing Nitrogen, Biomolecules, Polymers, Chemistry in Everyday Life

### Biology (22+24 chapters)
Class 11 and Class 12 complete NCERT curriculum

## 🚀 Getting Started

### Prerequisites
- Android Studio Flamingo or later
- Kotlin 1.9.0+
- Android SDK 28+
- Gradle 8.0+

### Clone & Build

```bash
git clone https://github.com/james-design-code/NEET-smart-tracker-.git
cd NEET-smart-tracker-

# Build debug APK
./gradlew build

# Install on device
./gradlew installDebug

# Run tests
./gradlew test

# Build release APK
./gradlew bundleRelease
```

## 📁 Project Structure

```
app/src/main/java/com/neet/smarttracker/
├── data/
│   ├── db/
│   │   ├── AppDatabase.kt
│   │   ├── entity/Entities.kt
│   │   ├── dao/Daos.kt
│   │   └── converter/DateTimeConverter.kt
│   ├── model/Models.kt
│   └── repository/Repositories.kt
├── di/
│   └── AppModule.kt
├── ui/
│   ├── theme/Theme.kt
│   ├── screens/
│   ├── components/
│   └── navigation/AppNavigation.kt
├── viewmodel/
│   └── HomeViewModel.kt
├── utils/
│   ├── CurriculumData.kt
│   └── Constants.kt
└── MainActivity.kt
```

## 🎨 Design System

### Dark Theme Colors
- **Deep Black** (`#0F0F0F`) - Main background
- **Dark Gray** (`#1A1A1A`) - Surface
- **Surface Light** (`#2A2A2A`) - Card background
- **Primary Orange** (`#FF6B35`) - Actions & focus
- **Cyan** (`#00D9FF`) - Accents
- **Green** (`#00D084`) - Success/completion
- **Amber** (`#FFB703`) - Priority

## 📊 Database Schema

### 8 Core Entities
1. **SubjectEntity** - Physics, Chemistry, Biology
2. **ChapterEntity** - NCERT chapters with progress
3. **NotesEntity** - Chapter notes and formulas
4. **DailyTaskEntity** - Daily study tasks
5. **BacklogItemEntity** - Pending chapters
6. **ProgressEntity** - Daily progress snapshots
7. **ReminderEntity** - Study reminders
8. **UserPreferenceEntity** - User settings

## 🏗️ Architecture

```
UI Layer (Compose Screens)
         ↓
ViewModel Layer (State Management)
         ↓
Repository Layer (Data Access)
         ↓
DAO Layer (Database Queries)
         ↓
SQLite Database
```

## 🔄 Data Flow

- **User Action** → ViewModel → Repository → DAO → Database
- **Database Change** → DAO → Repository → StateFlow → UI Recomposition
- All operations are **async** using Coroutines
- All data is **local** on device

## 🧪 Testing

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## 📈 Future Enhancements

- Google Calendar sync
- Cloud backup (Firebase)
- AI-powered revision suggestions
- Video/PDF resource integration
- Mock exam simulations
- Multi-language support
- Study group collaboration
- Export progress reports

## 📄 License

MIT License - See LICENSE file for details

## 👨‍💻 Contributing

Contributions are welcome! Please follow the existing code style and architecture patterns.

## 📞 Support

For issues and questions, please create a GitHub issue.

## 🙏 Acknowledgments

- NCERT curriculum structure
- Jetpack Compose documentation
- Android architecture guides
