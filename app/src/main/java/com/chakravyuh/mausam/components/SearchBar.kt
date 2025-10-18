package com.chakravyuh.mausam.components

import androidx.annotation.ColorLong
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chakravyuh.mausam.ui.theme.TopBarColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearch: (String) -> Unit,
    drawerState: DrawerState
) {


    var query by remember{ mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    if (drawerState.isClosed){
        active=false
        query=""
    }
    DockedSearchBar(


        query = query,
        onQueryChange = { query = it },
        onSearch = {
            if (query.isNotEmpty()) onSearch(query)
            active = false },
        active = active,
        onActiveChange = { active = it},
        placeholder = { Text("Enter City Name and Country Name", color = Color.White) },
        leadingIcon = {
            if (active){
                null
            }else{
                Icon(
                    imageVector = Icons.Default.Search,
                    "",
                    tint = Color.White
                )
            }
        },
        trailingIcon = {
            TextButton(onClick = {
                if (query.isNotEmpty()) onSearch(query)

            }) {
                Text("Search", color = Color.White)
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = SearchBarDefaults.colors(
            containerColor = TopBarColor,
            dividerColor = Color.White,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = Color.White, unfocusedTextColor = Color.White
            )

        )

    ) {

        val suggestions = listOf("Delhi", "Mumbai", "Bangalore", "Chennai")
        suggestions.filter {
            it.contains(query, ignoreCase = true)
        }.forEach { suggestion ->
            Text(
                text = suggestion,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        query = suggestion
                        onSearch(suggestion)
                        active = false
                    }
                    .padding(12.dp)
            )
        }
    }
}