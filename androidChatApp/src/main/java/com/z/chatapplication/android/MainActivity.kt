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
//        Log.d("zaryab123", "onCreate: ")
//
//        val templates="{  \"templates\": [    {      \"title\": \"Solve Queries Related to Any Subject\",      \"description\": \"Get answer to queries related to Math, Physics, Chemistry, Humanities, etc instantly\",      \"category\": \"education\",      \"question\": \"Explain trigonometry in simple words.\"    },    {      \"title\": \"Unlimited Ideas for Short Stories\",      \"description\": \"Make your own short stories on any theme instantly\",      \"category\": \"Short Story\",      \"question\": \"Give me some ideas for writing a fairytale short story\"    },    {      \"title\": \"Build a Social Media Account and Online Business\",      \"description\": \"Tips and tricks for building a successful social media account and online business\",      \"category\": \"Social Media\",      \"question\": \"Give me some tips for managing social media accounts\"    },    {      \"title\": \"Get Answers to all your Science and Technology Queries\",      \"description\": \"Get clear and precise answers to all your queries related to latest technology instantly\",      \"category\": \"Science and Technology\",      \"question\": \"What is artificial intelligence?\"    },    {      \"title\": \"Expert Advice for Managing Business and Finance\",      \"description\": \"Get expert advice to become a successful business person instantly\",      \"category\": \"Business and finance\",      \"question\": \"How to increase the profitability of a business?\"    },    {      \"title\": \"Get Instant Help in a Medical Emergency\",      \"description\": \"Let our health and medical expert help you with queries related to a common disease, good nutrition, essential vitamins, exercises, and more\",      \"category\": \"Health and Medicine\",      \"question\": \"How to treat a common disease?\"    },    {      \"title\": \"Explore Top Travel Destinations\",      \"description\": \"Read about top travel destinations, and get expert tips for planning a trip\",      \"category\": \"Travel and tourism\",      \"question\": \"Name some popular travel destinations for couples.\"    },    {      \"title\": \"Stay Updated with Latest News\",      \"description\": \"Know what is happening today in the world with our instant current affairs updates feature\",      \"category\": \"Current Affairs\",      \"question\": \"How is the political situation in the United States today?\"    },    {      \"title\": \"Learn Everything about your Favorite Sports\",      \"description\": \"Get all the information about your favorite football team within no time\",      \"category\": \"Sports and Recreation\",      \"question\": \"Who won the fifa world cup 2023?\"    },    {      \"title\": \"Latest Updates about Movies and Songs\",      \"description\": \"Get all the information about your favorite actors and their movies\",      \"category\": \"Arts and Entertainment\",      \"question\": \"Top 10 movies of the year.\"    },    {      \"title\": \"Self Care and Personal Development Advice\",      \"description\": \"Get tried and tested self care and personal development advice from experts instantly\",      \"category\": \"Arts and Entertainment\",      \"question\": \"Tips for self-improvement.\"    }  ]}"
//        val categories="{  \"category\": [    {      \"title\": \"all\"    },    {      \"title\": \"Education\"    },    {      \"title\": \"Short Story\"    },    {      \"title\": \"Social Media\"    },    {      \"title\": \"Science and Technology\"    },    {      \"title\": \"Business and Finance\"    },    {      \"title\": \"Health and Medicine\"    },    {      \"title\": \"Travel and Tourism\"    },    {      \"title\": \"Current Affairs\"    },    {      \"title\": \"Selfcare and Personal Development\"    }  ]}"
//        val remoteConfigParsed:GetRemoteConfigParsed= GetRemoteConfigParsed(RemoteConfigValuesMapper(
//            TemplateMapper(),
//            CategoryMapper()
//        ))

//
//        Log.d("zaryab123", "onCreate: ${remoteConfigParsed.getRemoteConfigValues(templates,categories).toString()}")
////        val api: KtorClientFactory = KtorClientFactory()
////        GlobalScope.launch {
////            val response = api.queryMessage(
////                InputDto(
////                    "text-davinci-003",
////                    "hello",
////                    0,
////                    50
////                )
////            )
//            Log.d("zaryab123", "onCreate: ${response.toString()}")
//        }
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
