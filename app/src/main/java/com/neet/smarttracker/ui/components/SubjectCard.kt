package com.neet.smarttracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neet.smarttracker.data.model.Subject
import com.neet.smarttracker.ui.theme.NeetColors

@Composable
fun SubjectCard(
    subject: Subject,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable(onClick = onClick),
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
                subject.name,
                style = androidx.compose.material3.MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                color = NeetColors.TextPrimary
            )

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "${subject.completedChapters}/${subject.totalChapters}",
                        style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
                        color = NeetColors.TextSecondary
                    )
                    Text(
                        "${subject.completionPercentage.toInt()}%",
                        style = androidx.compose.material3.MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = NeetColors.Success
                    )
                }
                LinearProgressIndicator(
                    progress = { subject.completionPercentage / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .background(NeetColors.DarkGray, RoundedCornerShape(3.dp)),
                    color = Color(android.graphics.Color.parseColor(subject.color)),
                    trackColor = NeetColors.DarkGray,
                    strokeCap = StrokeCap.Round
                )
            }
        }
    }
}
