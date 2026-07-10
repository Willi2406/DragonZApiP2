package com.example.dragonzapip2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.dragonzapip2.presentacion.navigation.ApNavDisplay
import dagger.hilt.android.AndroidEntryPoint
import com.example.dragonzapip2.presentacion.navigation.Screen
import com.example.dragonzapip2.ui.theme.DragonZApiP2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DragonZApiP2Theme{
                val backStack = rememberNavBackStack(Screen.PlanetList)
                val items = listOf(
                    TopLevelRoute("Planetas", Screen.PlanetList, Icons.Default.Place),
                    TopLevelRoute("Personajes", Screen.CharacterList, Icons.Default.Face)
                )

                Scaffold(
                    bottomBar = {
                        val currentDestination = backStack.lastOrNull()

                        val isDetail = currentDestination is Screen.PlanetDetail ||
                                currentDestination is Screen.CharacterDetail

                        if (!isDetail) {
                            NavigationBar {
                                items.forEach { item ->
                                    NavigationBarItem(
                                        icon = { Icon(item.icono, contentDescription = item.nombre) },
                                        label = { Text(item.nombre) },
                                        selected = currentDestination == item.ruta,
                                        onClick = {
                                            if (currentDestination != item.ruta) {
                                                backStack.clear()
                                                backStack.add(item.ruta)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ApNavDisplay(
                        backStack = backStack,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

data class TopLevelRoute<T : Screen>(
    val nombre: String,
    val ruta: T,
    val icono: ImageVector
)
