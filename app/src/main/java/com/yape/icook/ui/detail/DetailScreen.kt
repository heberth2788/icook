package com.yape.icook.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.yape.icook.R
import com.yape.icook.data.entity.FoodRecipeResponse
import com.yape.icook.ui.theme.ICookTheme

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    navHostController: NavHostController,
    foodRecipeId: Int,
) {
    DetailContent(
        foodRecipeResponse = detailViewModel.foodRecipeResponse,
//        onClickBack = { detailViewModel.onClickBack() },
        onClickBack = { navHostController.navigateUp() },
        onClickMap = { navHostController.navigate("map/${foodRecipeId}") },
        modifier = Modifier,
    )

    // Execute when the Composable is first composed and get
    // the food recipe list from the server at first time and
    // filter it by foodRecipeId
    LaunchedEffect(key1 = Unit) {
        detailViewModel.loadFoodRecipe(foodRecipeId)
    }
}

@Composable
fun DetailContent(
    foodRecipeResponse: FoodRecipeResponse,
    onClickBack: () -> Unit = { },
    onClickMap: () -> Unit = { },
    modifier: Modifier,
) {
    Scaffold(
        topBar = {
            DetailTopBar(
                foodName = foodRecipeResponse.name,
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
                           .data(foodRecipeResponse.imageUrl)
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
                       text = foodRecipeResponse.desc,
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
                       text = foodRecipeResponse.ingredients,
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
                       text = foodRecipeResponse.preparation,
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