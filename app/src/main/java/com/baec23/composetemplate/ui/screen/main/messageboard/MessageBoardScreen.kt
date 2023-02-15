package com.baec23.composetemplate.ui.screen.main.messageboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.composetemplate.model.Post
import com.baec23.composetemplate.navigation.NavService

const val messageBoardScreenRoute = "message_board_screen_route"
fun NavGraphBuilder.messageBoardScreen() {
    composable(route = messageBoardScreenRoute) {
        MessageBoardScreen()
    }
}

fun NavService.navigateToMessageBoardScreen(navOptions: NavOptions? = null) {
    navController.navigate(route = messageBoardScreenRoute, navOptions = navOptions)
}

@Composable
fun MessageBoardScreen(
    viewModel: MessageBoardViewModel = hiltViewModel()
) {
    val posts by viewModel.posts.collectAsState()
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            items(posts.size) {
                val post = posts[it]
                PostCard(post = post)
            }
        }
    }
}

@Composable
fun PostCard(
    post: Post
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(10.dp)
    ) {
        Column {
            Text(text = post.userDisplayName)
            Text(text = post.message, style = MaterialTheme.typography.titleLarge)
        }
    }
}