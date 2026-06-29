package com.example.urhobolingo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.urhobolingo.ui.screens.HomeScreen
import com.example.urhobolingo.ui.screens.LessonDetailScreen
import com.example.urhobolingo.ui.screens.LessonsScreen
import com.example.urhobolingo.ui.screens.OnboardingScreen
import com.example.urhobolingo.ui.screens.PathScreen
import com.example.urhobolingo.ui.screens.TranslatorScreen
import com.example.urhobolingo.ui.screens.VocabularyScreen
import com.example.urhobolingo.viewmodel.AppViewModel

sealed class NavItem(val route: String, val label: String) {
    data object Home : NavItem("home", "Home")
    data object Path : NavItem("path", "Path")
    data object Lessons : NavItem("lessons", "Lessons")
    data object Vocabulary : NavItem("vocabulary", "Words")
    data object Translator : NavItem("translator", "Translator")
}

@Composable
fun UrhoboLingoApp(viewModel: AppViewModel) {
    val navController = rememberNavController()
    var selectedRoute by rememberSaveable { mutableStateOf(NavItem.Home.route) }
    val items = listOf(NavItem.Home, NavItem.Path, NavItem.Lessons, NavItem.Vocabulary, NavItem.Translator)
    val state = viewModel.uiState

    if (!state.progress.hasOnboarded) {
        OnboardingScreen(onContinue = { viewModel.finishOnboarding() })
        return
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = selectedRoute == item.route,
                        onClick = {
                            selectedRoute = item.route
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            when (item) {
                                NavItem.Home -> Icon(Icons.Default.Home, contentDescription = item.label)
                                NavItem.Path -> Icon(Icons.Default.Map, contentDescription = item.label)
                                NavItem.Lessons -> Icon(Icons.Default.AutoStories, contentDescription = item.label)
                                NavItem.Vocabulary -> Icon(Icons.Default.Widgets, contentDescription = item.label)
                                NavItem.Translator -> Icon(Icons.Default.Translate, contentDescription = item.label)
                            }
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavItem.Home.route) { HomeScreen(viewModel) }
            composable(NavItem.Path.route) { PathScreen(viewModel) }
            composable(NavItem.Lessons.route) {
                LessonsScreen(
                    viewModel = viewModel,
                    onOpenLesson = { lessonId -> navController.navigate("lesson/$lessonId") }
                )
            }
            composable(NavItem.Vocabulary.route) { VocabularyScreen(viewModel) }
            composable(NavItem.Translator.route) { TranslatorScreen(viewModel) }
            composable(
                route = "lesson/{lessonId}",
                arguments = listOf(navArgument("lessonId") { type = NavType.StringType })
            ) { backStackEntry ->
                val lessonId = backStackEntry.arguments?.getString("lessonId").orEmpty()
                LessonDetailScreen(
                    viewModel = viewModel,
                    lessonId = lessonId,
                    onBack = {
                        viewModel.exitLesson()
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
