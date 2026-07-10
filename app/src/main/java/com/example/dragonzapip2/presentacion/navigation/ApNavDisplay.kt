package com.example.dragonzapip2.presentacion.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.dragonzapip2.presentacion.personaje.detail.CharacterDetailScreen
import com.example.dragonzapip2.presentacion.personaje.list.CharacterListScreen
import com.example.dragonzapip2.presentacion.planet.detail.PlanetDetailScreen
import com.example.dragonzapip2.presentacion.planet.list.PlanetListScreen

@Composable
fun ApNavDisplay(
    backStack: NavBackStack<NavKey>,
    innerPadding: PaddingValues
) {
    NavDisplay(
        backStack = backStack,
        modifier = Modifier.padding(innerPadding),
        entryProvider = entryProvider {
            entry<Screen.PlanetList> {
                PlanetListScreen(
                    onPlanetClick = { planetId ->
                        backStack.add(Screen.PlanetDetail(planetId))
                    }
                )
            }

            entry<Screen.PlanetDetail> { key ->
                PlanetDetailScreen(
                    planetId = key.id,
                    onBack = {
                        if (backStack.isNotEmpty()) backStack.removeAt(backStack.size - 1)
                    }
                )
            }

            entry<Screen.CharacterList> {
                CharacterListScreen(
                    onCharacterClick = { characterId ->
                        backStack.add(Screen.CharacterDetail(characterId))
                    }
                )
            }

            entry<Screen.CharacterDetail> { key ->
                CharacterDetailScreen(
                    characterId = key.id,
                    onBack = {
                        if (backStack.isNotEmpty()) backStack.removeAt(backStack.size - 1)
                    }
                )
            }
        }
    )
}