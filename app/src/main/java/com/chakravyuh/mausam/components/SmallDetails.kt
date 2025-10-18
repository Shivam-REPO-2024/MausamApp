package com.chakravyuh.mausam.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SmallDetails(
    title:String,
    details:String,
) {
    Column {
        Text(
            text ="$title",
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.White,
            modifier=Modifier.padding(horizontal = 15.dp),

            )
        Spacer(modifier = Modifier.padding(2.dp))

        Text(
            text ="$details",
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Left,
            fontSize = 14.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.LightGray,
            modifier=Modifier.padding(horizontal = 15.dp),


            )
    }
}