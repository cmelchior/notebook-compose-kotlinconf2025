package com.example

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import java.awt.Color as AwtColor

private fun Color.toHsvTriple(): Triple<Float, Float, Float> {
    val awt = java.awt.Color(this.toArgb())
    val hsv = java.awt.Color.RGBtoHSB(awt.red, awt.green, awt.blue, null)
    return Triple(hsv[0], hsv[1], hsv[2]) // h ∈ [0,1], s ∈ [0,1], v ∈ [0,1]
}

// Normalize a float that is [0f, 1f], so it always has 3 digits
private fun normalizeValue(value: Float): String {
    val clamped = value.coerceIn(0f, 1f)
    return String.format("%.3f", clamped)
}

fun Color.toHsv(): String = toHsvTriple().let { (h, s, v) ->
    "${normalizeValue(h)},${normalizeValue(s)},${normalizeValue(v)}"
}


