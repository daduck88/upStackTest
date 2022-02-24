package com.duck.upstackgit.data.remote

import com.duck.upstackgit.data.remote.dto.Repo
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class PostsServiceImpl(
    private val client: HttpClient
) : GithubService {

    override suspend fun getRepos(userName: String): List<Repo> {
        return try {
            client.get { url(HttpRoutes.BASE_URL + userName + HttpRoutes.REPOS) }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch(e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }
}