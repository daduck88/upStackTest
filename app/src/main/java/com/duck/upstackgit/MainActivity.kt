package com.duck.upstackgit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duck.upstackgit.data.remote.GithubService
import com.duck.upstackgit.data.remote.dto.Repo
import com.duck.upstackgit.ui.theme.UpstackGitTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpstackGitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InitView()
                }
            }
        }
    }
}

@Composable
fun InitView() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        var userName by remember { mutableStateOf("daduck88") }
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User name") }
        )

        var token by remember { mutableStateOf("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") }
        TextField(
            value = token,
            onValueChange = { token = it },
            label = { Text("Token") }
        )
        val composableScope = rememberCoroutineScope()
        Button(
            onClick = {
             composableScope.launch {
                 getRepos(userName, token) }
            }) { }

    }
}

suspend fun getRepos(userName: String, token: String) {
    val service = GithubService.create(userName, token)
    val repos = service.getRepos(userName)
    Log.e("response", repos.toString())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UpstackGitTheme {
        InitView()
    }
}