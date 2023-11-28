package pl.programming.calorytracker.navigation

import androidx.navigation.NavController
import pl.programming.core.utils.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}