package com.example

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.unit.dp
import com.example.ProjectTheme
import org.jetbrains.skia.ColorFilter
import org.jetbrains.skia.ColorMatrix
import org.jetbrains.skia.ISize
import org.jetbrains.skia.Shader

// Generates a "noise" shader that will introduce a paper-like quality to the background
// Need to investigate something better, but this seems okay for a first draft.
fun createGrayscaleNoiseShader(): Shader {

    // Create Noise
    val shader = Shader.makeFractalNoise(
        baseFrequencyX = 0.1f, // Adjust for desired texture
        baseFrequencyY = 0.1f,
        numOctaves = 5,
        seed = 0f,
        tileSize = ISize.make(4, 4)
    )

    // Apply a color filter to convert to grayscale
    return shader.makeWithColorFilter(
        ColorFilter.makeMatrix(
            // Use NCTS values to convert to grayscale
            // https://en.wikipedia.org/wiki/Grayscale#Converting_color_to_grayscale
            ColorMatrix(
                0.299f, 0.587f, 0.114f, 0f, 0f,   // Red to luminance
                0.299f, 0.587f, 0.114f, 0f, 0f,         // Green to luminance
                0.299f, 0.587f, 0.114f, 0f, 0f,         // Blue to luminance
                0f, 0f, 0f, 1f, 0f                      // Alpha unchanged
            )
        )
    )
}

/**
 * Add noise to a background color so it mimics a paper-like texture.
 */
fun Modifier.paperBackground(color: Color = ProjectTheme.rulebookPaper): Modifier {
    val paperShader = createGrayscaleNoiseShader()
    return this.drawBehind {
        // Add desired background color
        drawRect(color = color, size = size)
        // Add semi-transparent noise on top
        drawRect(
            size = size,
            brush = ShaderBrush(paperShader),
            alpha = 0.3f,
        )
        // Re-add background color to make the noise blend more into the background
        drawRect(color = color.copy(alpha = 0.5f), size = size)
    }
}

fun Modifier.stoneBackground(): Modifier {
    val color = Color(0x000000)
    val paperShader = createGrayscaleNoiseShader()
    return this.drawBehind {
        // Add desired background color
        drawRect(color = color, size = size)
        // Add semi-transparent noise on top
        drawRect(
            size = size,
            brush = ShaderBrush(paperShader),
            alpha = 0.8f,
        )
        // Re-add background color to make the noise blend more into the background
        drawRect(color = color.copy(alpha = 0.5f), size = size)
        drawRect(color = color.copy(alpha = 0.2f), size = size)
    }
}

/**
 * Background use for the Blue sidebar on menu screens.
 */
fun Modifier.paperBackgroundWithLine(color: Color): Modifier {
    val paperShader = createGrayscaleNoiseShader()
    return this.drawBehind {

        // Create the path we want to outline
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height - 25.dp.toPx())
            lineTo(0f, size.height)
            close()
        }

        drawPath(path = path, color = color)
        drawPath(
            path = path,
            brush = ShaderBrush(paperShader),
            alpha = 0.3f,
        )
        // Re-add background color to make the noise blend more into the background
        drawPath(path = path, color = color.copy(alpha = 0.5f))
    }
}
