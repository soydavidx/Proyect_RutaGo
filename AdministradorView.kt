package com.example.myapplication.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
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
fun AdminDashboardScreen(
    onAddUser: () -> Unit,
    onAddAdmin: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        // Tabs
        TabRow(selectedTabIndex = selectedTab) {
            listOf("Ruta", "Admin").forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(text = title) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de búsqueda
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar usuarios") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        )

        // Lista de usuarios
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(userList) { user ->
                UserListItem(user = user)
            }
        }

        // Variables disponibles
        Text(
            text = "Variables disponibles",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyColumn(modifier = Modifier.height(100.dp)) {
            items(variables) { variable ->
                Text(
                    text = variable,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

        // Botones de acción
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = onAddUser,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Usuario")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onAddAdmin,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Administrador")
            }
        }
    }
}

@Composable
fun UserListItem(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = user.name,
            modifier = Modifier.weight(1f)
        )
        Badge(
            containerColor = when(user.role) {
                "ADM" -> MaterialTheme.colorScheme.primary
                else -> MaterialTheme.colorScheme.secondary
            }
        ) {
            Text(user.role)
        }
    }
}

// Modelos de datos
data class User(
    val name: String,
    val role: String
)

val userList = listOf(
    User("David G", "ADM"),
    User("Marie", "User"),
    User("Diego", "User"),
    User("Anastasia", "ADM"),
    User("daniel", "User"),
    User("David R", "User")
)

val variables = listOf(
    "xxxx-xxxx-xxxx-x",
    "xxxx-xxxx-xxxx-x",
    "xxxx-xxxx-xxxx-x"
)

@Preview
@Composable
fun PreviewAdminDashboard() {
    MaterialTheme {
        AdminDashboardScreen(
            onAddUser = {},
            onAddAdmin = {}
        )
    }
}