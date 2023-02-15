package com.baec23.composetemplate.repository

import com.baec23.composetemplate.model.Post
import com.baec23.composetemplate.remote.api.baseUrl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.here.oksse.OkSse
import com.here.oksse.ServerSentEvent
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

@ActivityScoped
class PostRepository @Inject constructor(
) {
    private val _posts = MutableStateFlow<List<Post>>(listOf())
    val posts = _posts.asStateFlow()

    private var sseConnection: ServerSentEvent
    private val postListener = object : ServerSentEvent.Listener {
        override fun onOpen(sse: ServerSentEvent?, response: Response?) {
        }

        override fun onMessage(
            sse: ServerSentEvent?,
            id: String?,
            event: String?,
            message: String?
        ) {
            val itemType = object : TypeToken<List<Post>>() {}.type
            val outputList = Gson().fromJson<List<Post>>(message, itemType)
            _posts.value = outputList
        }

        override fun onComment(sse: ServerSentEvent?, comment: String?) {
        }

        override fun onRetryTime(sse: ServerSentEvent?, milliseconds: Long): Boolean {
            return false
        }

        override fun onRetryError(
            sse: ServerSentEvent?,
            throwable: Throwable?,
            response: Response?
        ): Boolean {
            return false
        }

        override fun onClosed(sse: ServerSentEvent?) {
        }

        override fun onPreRetry(sse: ServerSentEvent?, originalRequest: Request?): Request {
            return originalRequest!!
        }
    }

    init {
        val request = Request.Builder()
            .url("$baseUrl/post")
            .build()
        sseConnection = OkSse().newServerSentEvent(request, postListener)
    }
}