package com.neet.smarttracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.neet.smarttracker.ui.components.SubjectCard
import com.neet.smarttracker.ui.theme.NeetColors
import com.neet.smarttracker.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val subjects by viewModel.subjects.collectAsState(initial = emptyList())
    val selectedClass by viewModel.selectedClass.collectAsState(initial = 12)
    val backlogCount by viewModel.backlogCount.collectAsState(initial = 0)
    val todayProgress by viewModel.todayProgress.collectAsState(initial = "0%")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NeetColors.DeepBlack)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header with Settings
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "NEET Smart Tracker",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    color = NeetColors.TextPrimary
                )
                Text(
                    "Class $selectedClass",
                    style = MaterialTheme.typography.bodySmall,
                    color = NeetColors.TextSecondary
                )
            }
            IconButton(onClick = { /* Navigate to settings */ }) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = NeetColors.TextSecondary
                )
            }
        }

        // Quick Stats
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Today's Progress
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = NeetColors.SurfaceLight
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Today's Progress",
                        style = MaterialTheme.typography.labelSmall,
                        color = NeetColors.TextSecondary
                    )
                    Text(
                        todayProgress,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = NeetColors.Success
                    )
                }
            }

            // Backlog
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = NeetColors.SurfaceLight
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Pending",
                        style = MaterialTheme.typography.labelSmall,
                        color = NeetColors.TextSecondary
                    )
                    Text(
                        "$backlogCount chapters",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = NeetColors.Warning
                    )
                }
            }
        }

        // Subjects
        Text(
            "Subjects",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = NeetColors.TextPrimary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 400.dp)
        ) {
            items(subjects.size) { index ->
                val subject = subjects[index]
                SubjectCard(
                    subject = subject,
                    onClick = {
                        // Navigate to subject chapters
                    }
                )
            }
        }

        // Quick Actions
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Quick Actions",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = NeetColors.TextPrimary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickActionButton(
                label = "Daily Plan",
                modifier = Modifier.weight(1f),
                onClick = { /* Navigate to daily planner */ }
            )
            QuickActionButton(
                label = "Focus Timer",
                modifier = Modifier.weight(1f),
                onClick = { /* Navigate to timer */ }
            )
            QuickActionButton(
                label = "Analytics",
                modifier = Modifier.weight(1f),
                onClick = { /* Navigate to analytics */ }
            )
        }
    }
}

@Composable
fun QuickActionButton(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeetColors.SurfaceLight
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = NeetColors.Primary
            )
        }
    }
}
