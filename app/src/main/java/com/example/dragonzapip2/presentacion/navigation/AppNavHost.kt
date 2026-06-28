package com.example.dragonzapip2.presentacion.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dragonzapip2.presentacion.personaje.detail.CharacterDetailScreen
import com.example.dragonzapip2.presentacion.personaje.list.CharacterListScreen
import com.example.dragonzapip2.presentacion.planet.detail.PlanetDetailScreen
import com.example.dragonzapip2.presentacion.planet.list.PlanetListScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.CharacterList,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -it / 3 }, animationSpec = tween(300)) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -it / 3 }, animationSpec = tween(300)) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300)) + fadeOut(animationSpec = tween(300))
        }
    ) {
        composable<Screen.PlanetList> {
            PlanetListScreen(
                onPlanetClick = { planetId ->
                    navHostController.navigate(Screen.PlanetDetail(id = planetId))
                }
            )
        }
        composable<Screen.PlanetDetail> {
            PlanetDetailScreen(
                onBack = { navHostController.navigateUp() }
            )
        }

        composable<Screen.CharacterList> {
            CharacterListScreen(
                onCharacterClick = { charId ->
                    navHostController.navigate(Screen.CharacterDetail(id = charId))
                }
            )
        }
        composable<Screen.CharacterDetail> {
            CharacterDetailScreen(
                onBack = { navHostController.navigateUp() }
            )
        }
    }
}