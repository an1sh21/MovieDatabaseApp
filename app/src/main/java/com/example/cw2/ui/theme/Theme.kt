package com.example.cw2.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Shapes // ✅ CORRECT
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
val CustomShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(12.dp)
)
private val DarkColorScheme = darkColorScheme(
    primary = RedPrimary,
    secondary = RedDark,
    background = Black,
    surface = DarkGray,
    onPrimary = WhiteText,
    onSecondary = WhiteText,
    onBackground = WhiteText,
    onSurface = WhiteText,
)

@Composable
fun MyAppTheme(
    darkTheme: Boolean = true, // Force dark mode
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        shapes = CustomShapes,
        content = content
    )
}