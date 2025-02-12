package com.yape.icook.ui.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.yape.icook.R
import com.yape.icook.mock.mockFoodRecipe
import com.yape.icook.ui.domainentity.FoodRecipe
import com.yape.icook.ui.theme.ICookTheme

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = viewModel(),
    navHostController: NavHostController,
    foodRecipeId: Int,
) {
    DetailContent(
        foodRecipe = detailViewModel.foodRecipe,
//        onClickBack = { detailViewModel.onClickBack() },
        onClickBack = { navHostController.navigateUp() },
        onClickMap = { navHostController.navigate("map/${foodRecipeId}") },
        modifier = Modifier,
    )
}

@Composable
fun DetailContent(
    foodRecipe: FoodRecipe,
    onClickBack: () -> Unit = { },
    onClickMap: () -> Unit = { },
    modifier: Modifier,
) {
    Scaffold(
        topBar = {
            DetailTopBar(
                foodName = foodRecipe.name,
                onClickBack = onClickBack,
                modifier = modifier,
            )
        }
    ) { contentPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(contentPadding)
                .padding(horizontal = 9.dp)
                .fillMaxWidth()
        ) {
           item {
               // Image & Map button
               Box(
                   contentAlignment = Alignment.BottomEnd
               ) {
                   // Food image
                   AsyncImage(
                       model = ImageRequest.Builder(LocalContext.current)
                           .data(foodRecipe.imageUrl)
                           .crossfade(true)
                           .build(),
                       placeholder = painterResource(id = R.drawable.ic_launcher_background),
                       contentDescription = null,
                       contentScale = ContentScale.Crop,
                       modifier = modifier.size(200.dp),
//                       imageLoader = LocalContext.current.imageLoader.newBuilder().logger(DebugLogger()).build(),
                       error = painterResource(id = R.drawable.ic_launcher_background),
                   )
                   // Map button
                   IconButton(
                       onClick = onClickMap,
                   ) {
                       Icon(
                           imageVector = Icons.Filled.Place,
                           modifier = modifier.size(50.dp),
                           contentDescription = null,
                       )
                   }
               }
               // Description
               ElevatedCard(
                   modifier = modifier.padding(vertical = 6.dp)
               ) {
                   Text(
                       text = stringResource(R.string.description_text),
                       style = MaterialTheme.typography.titleMedium,
                       modifier = modifier
                           .fillMaxWidth()
                           .padding(all = 9.dp)
                   )
                   HorizontalDivider()
                   Text(
                       text = foodRecipe.desc,
                       style = MaterialTheme.typography.bodyLarge,
                       modifier = modifier
                           .fillMaxWidth()
                           .padding(all = 9.dp)
                   )
               }
               // Ingredients
               ElevatedCard(
                   modifier = modifier.padding(vertical = 6.dp)
               ) {
                   Text(
                       text = stringResource(R.string.ingredients_text),
                       style = MaterialTheme.typography.titleMedium,
                       modifier = modifier
                           .fillMaxWidth()
                           .padding(all = 9.dp)
                   )
                   HorizontalDivider()
                   Text(
                       text = foodRecipe.ingredients,
                       style = MaterialTheme.typography.bodyLarge,
                       modifier = modifier
                           .fillMaxWidth()
                           .padding(all = 9.dp)
                   )
               }

               // Preparation
               ElevatedCard(
                   modifier = modifier.padding(vertical = 6.dp)
               ) {
                   Text(
                       text = stringResource(R.string.preparation_text),
                       style = MaterialTheme.typography.titleMedium,
                       modifier = modifier
                           .fillMaxWidth()
                           .padding(all = 9.dp)
                   )
                   HorizontalDivider()
                   Text(
                       text = foodRecipe.preparation,
                       style = MaterialTheme.typography.bodyLarge,
                       modifier = modifier
                           .fillMaxWidth()
                           .padding(all = 9.dp)
                   )
               }
           }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    foodName: String,
    onClickBack: () -> Unit,
    modifier: Modifier,
) {
    CenterAlignedTopAppBar(
        title ={
            Text(text = foodName)
        },
        navigationIcon = {
            IconButton(
                onClick = onClickBack,
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailContentPreview() {
    ICookTheme {


//        DetailScreen()
    }
}