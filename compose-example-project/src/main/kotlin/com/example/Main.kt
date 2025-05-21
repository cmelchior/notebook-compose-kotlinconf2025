package com.example

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.event.MouseWheelEvent
import javax.swing.JPanel


@Composable
fun ProjectButton(text: String, onClick: () -> Unit, background: Color) {
    Button(
        onClick = onClick,
    ) {
        Text(text)
    }
}

@Composable
fun ProjectApp() {
    var counter by remember { mutableStateOf(0) }
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ProjectDialog(
                title = "Counter",
                icon = {
                },
                content = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Counter(counter)
                    }
                },
                buttons = {
                    Button(onClick = {
                        counter++
                    }) {
                        Text(text = "Increment")
                    }
                }
            )
        }
    }
}

@Composable
fun Counter(value: Int, color: Color = Color.Black) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .border(2.dp, Color.Black.copy(alpha = 0.5f))
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = value.toString(),
            color = color,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        ProjectApp()
    }
}

class NonPropagatingPanel : JPanel() {
    override fun processMouseWheelEvent(e: MouseWheelEvent) {
        // Handle the scroll here if you want, or just consume it
        // For example, forward to a ComposePanel if needed

        if (shouldConsumeScroll(e)) {
            e.consume()
        } else {
            super.processMouseWheelEvent(e)
        }
    }

    private fun shouldConsumeScroll(e: MouseWheelEvent): Boolean {
        // Customize logic here to determine if the event should be consumed
        return true
    }
}