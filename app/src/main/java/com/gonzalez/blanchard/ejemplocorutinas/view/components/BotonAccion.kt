package com.gonzalez.blanchard.ejemplocorutinas.view.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BotonAccion(
    isLoading: Boolean,
    onClickEvent: () -> Unit,
) {
    Button(
        onClick = onClickEvent,
        enabled = !isLoading
    ) {
        Text(text = "Ejecutar acción")
    }
}