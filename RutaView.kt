package com.example.myapplication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ActiveRouteScreen(
    currentLocation: String,
    routeDetails: RouteDetails,
    nearbyStreets: List<String>,
    onStopRoute: () -> Unit
) {
    var elapsedTime by remember { mutableStateOf("00:00:00") }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            elapsedTime = calculateElapsedTime(routeDetails.startTime)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header con logo tipo Uber
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Ruta",
                style = MaterialTheme.typography.headlineMedium
            )
            /*Icon(
                painter = interResource(R.drawable.ic_uber_logo),
                contentDescription = "Logo Uber",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )*/
        }

        Spacer(modifier = Modifier.height(24.dp))

// Sección de datos principales
        RouteInfoCard(
            startTime = routeDetails.startTime,
            elapsedTime = elapsedTime,
            distance = routeDetails.distance,
            description = routeDetails.description
        )

        Spacer(modifier = Modifier.height(24.dp))

// Ubicación actual
        Text(
            text = "UBICACIÓN ACTUAL",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = currentLocation,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

// Lista de calles cercanas
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(nearbyStreets) { street ->
                Text(
                    text = street,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Divider()
            }
        }

// Botón para detener la ruta
        Button(
            onClick = onStopRoute,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Text("Finalizar Ruta")
        }
    }
}

@Composable
fun RouteInfoCard(
    startTime: String,
    elapsedTime: String,
    distance: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            InfoRow("Iniciada en:", startTime)
            InfoRow("Tiempo:", elapsedTime)
            InfoRow("KM:", distance)
            InfoRow("Descripción:", description)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(text = value)
    }
}

// Modelo de datos
data class RouteDetails(
    val startTime: String,
    val distance: String,
    val description: String
)

@Preview
@Composable
fun PreviewActiveRouteScreen() {
    MaterialTheme {
        ActiveRouteScreen(
            currentLocation = "South Ship Canal 7",
            routeDetails = RouteDetails(
                startTime = "09:41 AM",
                distance = "7.2 km",
                description = "Ruta comercial"
            ),
            nearbyStreets = listOf(
                "Burke Gilman 7",
                "Canal St",
                "Sushi St",
                "N-34th St",
                "N-35th St",
                "Evanston Ave N",
                "Burke-Gilman Trail",
                "N Northlake Way"
            ),
            onStopRoute = {}
        )
    }
}

fun calculateElapsedTime(startTime: String): String {
// Lógica para calcular tiempo transcurrido
    return "00:45:23"
}