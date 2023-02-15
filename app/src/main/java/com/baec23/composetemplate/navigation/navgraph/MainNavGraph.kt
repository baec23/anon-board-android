package com.baec23.composetemplate.navigation.navgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import com.baec23.composetemplate.navigation.NavService
import com.baec23.composetemplate.ui.screen.main.messageboard.messageBoardScreen
import com.baec23.composetemplate.ui.screen.main.messageboard.messageBoardScreenRoute
import com.baec23.ludwig.component.navbar.BottomNavigationItem

const val mainNavGraphRoute = "main_nav_graph_route"
fun NavGraphBuilder.mainNavGraph() {
    navigation(startDestination = messageBoardScreenRoute, route = mainNavGraphRoute) {
        messageBoardScreen()
    }
}

fun NavService.navigateToMainNavGraph() {
    navController.navigate(
        mainNavGraphRoute,
        navOptions = navOptions {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        })
}

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = messageBoardScreenRoute,
        iconImageVector = Icons.Default.Home,
        label = "Message Board",
    )
)