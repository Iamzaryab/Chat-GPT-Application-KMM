package com.z.chatapplication.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.z.chatapplication.Greeting
import com.z.chatapplication.datasource.network.KtorClientFactory
import com.z.chatapplication.datasource.network.dtoMapper.CategoryMapper
import com.z.chatapplication.datasource.network.dtoMapper.RemoteConfigValuesMapper
import com.z.chatapplication.datasource.network.dtoMapper.TemplateMapper
import com.z.chatapplication.datasource.remoteconfig.GetRemoteConfigParsed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView(Greeting().greet())
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}
