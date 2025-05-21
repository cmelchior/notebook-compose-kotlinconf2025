package com.example

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Customizable dialog with title, icon, icon and button row.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProjectDialog(
    title: String,
    dialogColor: Color = ProjectTheme.rulebookRed,
    icon: @Composable () -> Unit = { },
    width: Dp = 650.dp,
    content: @Composable () -> Unit = { /* Do nothing */  },
    buttons: @Composable RowScope.() -> Unit = { },
    onDismissRequest: () -> Unit = { /* Ignore ESC as dismiss */ },
) {
    val textColor = ProjectTheme.contentTextColor
    val buttonTextColor = ProjectTheme.white
    val inputDialogColors: @Composable (String) -> TextFieldColors = { text: String ->
        TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = dialogColor,
            focusedBorderColor = dialogColor,
            unfocusedLabelColor = if (text.isEmpty()) textColor.copy(alpha = 0.4f) else textColor,
            unfocusedBorderColor = textColor,
            textColor = textColor,
        )
    }
    Box {
        Surface(
            elevation = 8.dp,
            modifier = Modifier
                .width(width)
                .defaultMinSize(minHeight = 200.dp, minWidth = width)
                .paperBackground(color = ProjectTheme.rulebookPaper)
            ,
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(8.dp, color = dialogColor),
            color = ProjectTheme.rulebookPaper,
            contentColor = textColor,
        ) {
            Row(Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .paperBackground(ProjectTheme.rulebookPaper)
            ) {
                Box(modifier = Modifier
                    .width(130.dp)
                    .fillMaxHeight()
                    .padding(start = 24.dp)
                    .bannerBackground(dialogColor)
                ) {
                    icon()
                }
                Column(modifier = Modifier
                    .padding(start  = 24.dp, top = 16.dp, end = 24.dp, bottom = 16.dp)
                    .wrapContentHeight()
                ) {
                    JervisDialogContent(
                        title = title,
                        dialogColor = dialogColor,
                        textColor = textColor,
                        inputDialogColors = inputDialogColors,
                        content = content,
                        buttons = buttons,
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.JervisDialogContent(
    title: String,
    dialogColor: Color,
    textColor: Color,
    inputDialogColors: @Composable (String) -> TextFieldColors,
    content: @Composable (() -> Unit) = { /* Do nothing */ },
    buttons: @Composable RowScope.() -> Unit = { },
) {
    ProjectDialogHeader(title, dialogColor)
    TitleBorder(dialogColor)
    Column(modifier = Modifier.weight(1f).padding(top = 8.dp)) {
        content()
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        buttons()
    }
}

@Composable
private fun ColumnScope.ProjectDialogHeader(title: String, dialogColor: Color) {
    Box(
        modifier = Modifier.height(36.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            modifier = Modifier.padding(bottom = 2.dp),
            text = title.uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = dialogColor
        )
    }
}

@Composable
fun TitleBorder(color: Color = ProjectTheme.rulebookRed, alpha: Float = 1f) {
    Divider(
        modifier = Modifier
            .alpha(alpha)
            .wrapContentWidth()
            .height(3.dp)
        ,
        color = color
    )
}
