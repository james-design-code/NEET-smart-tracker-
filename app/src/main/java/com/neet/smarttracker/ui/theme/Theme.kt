package com.neet.smarttracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object NeetColors {
    val DeepBlack = Color(0xFF0F0F0F)
    val DarkGray = Color(0xFF1A1A1A)
    val SurfaceLight = Color(0xFF2A2A2A)
    val Primary = Color(0xFFFF6B35)
    val Secondary = Color(0xFF00D9FF)
    val Success = Color(0xFF00D084)
    val Warning = Color(0xFFFFB703)
    val Error = Color(0xFFFF6B6B)
    val TextPrimary = Color(0xFFFFFFFF)
    val TextSecondary = Color(0xFFB0B0B0)
    val TextTertiary = Color(0xFF808080)
}

private val DarkColorScheme = darkColorScheme(
    primary = NeetColors.Primary,
    secondary = NeetColors.Secondary,
    tertiary = NeetColors.Warning,
    background = NeetColors.DeepBlack,
    surface = NeetColors.DarkGray,
    surfaceVariant = NeetColors.SurfaceLight,
    onPrimary = NeetColors.DeepBlack,
    onSecondary = NeetColors.DeepBlack,
    onBackground = NeetColors.TextPrimary,
    onSurface = NeetColors.TextPrimary,
    error = NeetColors.Error,
    onError = NeetColors.DeepBlack
)

@Composable
fun NeetSmartTrackerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
