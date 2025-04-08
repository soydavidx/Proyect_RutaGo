package com.example.myapplication.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserRouteScreen(
    onStartRoute: () -> Unit,
    onShowMore: () -> Unit
) {
    var showAllRoutes by remember { mutableStateOf(false) }
    val routes = listOf(
        "LOCALIDAD1 → LOCALIDAD2",
        "LOCALIDAD2 → LOCALIDAD3",
        "LOCALIDAD4 → LOCALIDAD1",
        "LOCALIDAD3 → LOCALIDAD8",
        "LOCALIDAD1 → LOCALIDAD9",
        "LOCALIDAD1 → LOCALIDAD3"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Título
            Text(
                text = "Ruta User",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Listado de rutas
            Text(
                text = "RUTAS RECIENTES",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn {
                items(if (showAllRoutes) routes else routes.take(3)) { route ->
                    Text(
                        text = route,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                    )
                }

                item {
                    TextButton(
                        onClick = { showAllRoutes = !showAllRoutes },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = if (showAllRoutes) "ver menos" else "ver más",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        // Botón de acción flotante
        Button(
            onClick = onStartRoute,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Empezar una ruta",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewUserRouteScreen() {
    MaterialTheme {
        UserRouteScreen(
            onStartRoute = {},
            onShowMore = {}
        )
    }
}