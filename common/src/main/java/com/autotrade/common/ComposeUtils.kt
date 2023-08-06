package com.autotrade.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EditText(
    modifier: Modifier,
    sourceText: MutableState<String>,
    labelText: String,
    labelStyle: TextStyle = MaterialTheme.typography.titleSmall,
    keyboardType: KeyboardType = KeyboardType.Ascii
) {
    TextField(
        modifier = modifier,
        value = sourceText.value,
        onValueChange = { str -> sourceText.value = str },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        label = {
            Text(
                text = labelText,
                style = labelStyle
            )
        }
    )
}