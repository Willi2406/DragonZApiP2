package com.example.dragonzapip2.presentacion.planet.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailScreen(
    viewModel: DetailPlanetViewModel = hiltViewModel(),
    onBack: () -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Planeta")},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.padding(padding))
        }

        state.planet?.let { planet ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = planet.image,
                    contentDescription = planet.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(planet.name, style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = "Estado: ${if (planet.isDestroyed) "Destruido" else "Intacto"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Descripcion:", style = MaterialTheme.typography.titleMedium)
                Text(planet.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}