package com.duck.upstackgit.data.remote

import com.duck.upstackgit.data.remote.dto.Repo
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface GithubService {

    suspend fun getRepos(userName: String): List<Repo>

    companion object {
        fun create(userName: String, token: String): GithubService {
            return PostsServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                            encodeDefaults = true
                        })
                    }
                    install(Auth) {
                        basic {
                            credentials {
                                BasicAuthCredentials(
                                    username = userName,
                                    password = token)
                            }
                            realm = "Access to the '/' path"
                        }
                    }
                }
            )
        }
    }
}