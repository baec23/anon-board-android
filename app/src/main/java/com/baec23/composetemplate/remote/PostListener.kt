package com.baec23.composetemplate.remote

import android.util.Log
import com.baec23.composetemplate.model.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.here.oksse.ServerSentEvent
import okhttp3.Request
import okhttp3.Response

private const val TAG = "PostListener"
