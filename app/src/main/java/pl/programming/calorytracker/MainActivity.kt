package pl.programming.calorytracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.programming.calorytracker.navigation.navigate
import pl.programming.calorytracker.ui.theme.CaloryTrackerTheme
import pl.programming.core.navigation.Route
import pl.programming.onboarding_presentation.activity.ActivityScreen
import pl.programming.onboarding_presentation.age.AgeScreen
import pl.programming.onboarding_presentation.gender.GenderScreen
import pl.programming.onboarding_presentation.goal.GoalScreen
import pl.programming.onboarding_presentation.height.HeightScreen
import pl.programming.onboarding_presentation.weight.WeightScreen
import pl.programming.onboarding_presentation.welcome.WelcomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState,
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME,
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate,
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate,
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate,
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(
                                onNavigate = navController::navigate,
                            )
                        }
                        composable(Route.GOAL) {
                            GoalScreen(
                                onNavigate = navController::navigate,
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                        }
                        composable(Route.SEARCH) {
                        }
                    }
                }
            }
        }
    }
}
