package com.crazyvibes.tweet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.crazyvibes.tweet.api.TweetAPI
import com.crazyvibes.tweet.screens.CategoryScreen
import com.crazyvibes.tweet.screens.DetailScreen
import com.crazyvibes.tweet.ui.theme.TweetTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tweetAPI: TweetAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TweetTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {

                //

                //give an option to add topbar, bottom var, navbar etc
                Scaffold (topBar = {
                    TopAppBar (title = { Text(text = "Tweets")},
                        backgroundColor = Color.Blue,
                        contentColor = Color.White)
                }){
                    Box(modifier = Modifier.padding(it)){
                        App()
                    }
                }

            }



        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category"){
        composable(route = "category"){
            CategoryScreen {
                navController.navigate("detail/${it}") //passing the arguments
            }
        }

        composable(route = "detail/{category}", arguments = listOf(
            navArgument("category"){
                type = NavType.StringType   //type of arguments
            }
        )){
            DetailScreen()
        }
    }
}

/**
 * Scaffold:
 * - Scaffold provided by material design to add menu bars, title bar, bottom bar etc.
 *
 * NavHost:
 * - renders compose screens.
 *
 * NavGraph:
 * - represent the path of navigation screens.
 *
 * NavController:
 * - Manage the navigation.
 * - Manage the interaction between NavHost and NavGraph.
 * - Manage the backstack.
 *
 * NavArguments:
 * -Pass arguments/data between compose screens.
 *
 * Passing Arguments:
 *  * There are two ways to navigate one screen to another:
 *  * 1. by using navigation control. (mostly use for one screen to multiple screen)
 *  * 2. by using lamda function. (mostly use for one screen to limited screen)
 *  *
 *  * Passing argument:
 *  * - we use navArguments to pass arguments.
 *
 *  ViewModel:
 *  - ViewModel always associated with navigation screen, once screen destroy,
 *    viewModel is also destroyed.
 *  - It actually associated with NavBackStackEntry or ViewModel scopped with NavBackStackEntry.
 *  * */