package com.baec23.composetemplate.ui.screen.main.messageboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.composetemplate.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MessageBoardViewModel @Inject constructor(
    postRepository: PostRepository
) : ViewModel() {
    val posts = postRepository.posts.map {
        it.filter { post -> post.parentId == null }
            .sortedByDescending { post -> post.createdTimestamp }
    }.stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    fun onEvent(event: MessageBoardUiEvent) {
    }
}

sealed class MessageBoardUiEvent {
}