package com.baec23.composetemplate.model

data class Post(
    val id: String?,
    val userDisplayName: String,
    val message: String,
    val createdTimestamp: Long,
    val parentId: String?,
    val childIds: List<String>,
    val nestingLevel: Int = 0,
)
