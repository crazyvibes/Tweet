package com.crazyvibes.tweet.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crazyvibes.tweet.R
import com.crazyvibes.tweet.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen() {
    val categoryViewModel:CategoryViewModel = viewModel() //return viewModel object
    val categories = categoryViewModel.category.collectAsState() // provide a state by using state flow
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround){
        items(categories.value.distinct()){
            CategoryItem(category = it)
        }

    }

}

@Composable
fun CategoryItem(category: String) {
    Box(modifier = with(Modifier) {
        padding(4.dp)
        .size(160.dp)
        .clip(RoundedCornerShape(8.dp))
        .paint(painter = painterResource(id = R.drawable.wave),
            contentScale = ContentScale.Crop)
        .border(1.dp, Color(0xFFEEEEEE))
    },
        contentAlignment = Alignment.Center){
        Text(text = category,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.body1)
    }
}