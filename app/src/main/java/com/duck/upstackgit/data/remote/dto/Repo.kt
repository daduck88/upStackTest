package com.duck.upstackgit.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Repo(
    val id: Int,
    val node_id: String,
    val name: String
)
