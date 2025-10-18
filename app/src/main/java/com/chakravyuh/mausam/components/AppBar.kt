package com.chakravyuh.mausam.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    onNavIconClicked :() ->Unit
) {

    val navigationIcon : @Composable ()-> Unit = {
        if (!title.contains("Home")){
            IconButton(onClick = onNavIconClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }else{
            IconButton(onClick = onNavIconClicked) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Back",
                    tint = Color.White

                )
            }
        }
    }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            Color(22, 4, 135, 225),

            ),
        title = { Text(" ") },
        modifier= Modifier.heightIn(max = 100.dp),
        navigationIcon = navigationIcon,
//        actions = {
//            Row (
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ){
//
//                IconButton(onClick = {}) {
//                    Icon(
//                        imageVector = Icons.Default.Person,
//                        contentDescription = "Back",
//                        tint = Color.White,
//
//
//                    )
//                }
//            }
//        }
    )
}