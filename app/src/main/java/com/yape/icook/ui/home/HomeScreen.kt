package com.yape.icook.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.yape.icook.R
import com.yape.icook.data.entity.FoodRecipeResponse
import com.yape.icook.mock.createMockData
import com.yape.icook.ui.theme.ICookTheme


/**
 * The home screen implementing State hoisting
 */
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToFoodDetail: (foodRecipeId: Int) -> Unit = { },
) {
    val homeUiState: HomeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()

   HomeContent(
       textToSearch = homeViewModel.textToSearch,
       onQueryChange = { homeViewModel.onQueryChange(query = it) },
       onSearch = { },
       onActiveChange = { },
       foodRecipeResponses = homeUiState.foodRecipeResponses,
       onClickFoodRecipe = navigateToFoodDetail,//{ navHostController.navigate("detail/${it}") },
       modifier = Modifier,
   )

    // Execute when the Composable is first composed and get
    // the food recipe list from the server at first time
    LaunchedEffect(key1 = Unit) {
        homeViewModel.loadFoodRecipes()
    }
}

/**
 * The home content
 */
@Composable
fun HomeContent(
    textToSearch: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    foodRecipeResponses: List<FoodRecipeResponse> = emptyList(),
    onClickFoodRecipe: (foodRecipeId: Int) -> Unit = { },
    modifier: Modifier,
) {
    Scaffold(
        topBar = {
                SearchTopBar(
                    textToSearch = textToSearch,
                    onQueryChange = onQueryChange,
                    onSearch = onSearch,
                    onActiveChange = onActiveChange,
                    modifier = modifier,
                )
        }
    ) { contentPadding: PaddingValues ->
        LazyColumn(
            modifier = modifier.padding(horizontal = 9.dp),
            contentPadding = contentPadding,
        ) {
            items(items = foodRecipeResponses) { foodRecipeResponse: FoodRecipeResponse ->
                HorizontalDivider()
                FoodRecipeItem(
                    foodRecipeResponse = foodRecipeResponse,
                    onClickFoodRecipe = onClickFoodRecipe,
                    modifier = modifier,
                )
            }
        }
    }
}

/**
 * Composable item for the lazy column
 */
@Composable
fun FoodRecipeItem(
    foodRecipeResponse: FoodRecipeResponse,
    onClickFoodRecipe: (foodRecipeId: Int) -> Unit,
    modifier: Modifier,
) {
    OutlinedCard(
        onClick = { onClickFoodRecipe(foodRecipeResponse.id) },
        border = BorderStroke(0.dp, color = Color.Transparent),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 3.dp, vertical = 6.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(foodRecipeResponse.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.size(50.dp),
//                imageLoader = LocalContext.current.imageLoader.newBuilder().logger(DebugLogger()).build(),
                error = painterResource(id = R.drawable.ic_launcher_background),
            )
            Text(
                text = foodRecipeResponse.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.weight(0.8f).padding(start = 6.dp),
            )
        }
    }
}

/**
 * Compose search bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    textToSearch: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier,
) {
        SearchBar(
        query = textToSearch,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = false,
        onActiveChange = onActiveChange,
        placeholder = { Text(text = stringResource(R.string.search_text)) },
        trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        modifier = modifier
            .fillMaxWidth()
            .padding(9.dp),
    ) {  }

}

/**
 * Preview of the screen
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeContentPreview() {
    ICookTheme {
        HomeContent(
            textToSearch = "",
            onQueryChange = { },
            onSearch = { },
            onActiveChange = { },
            foodRecipeResponses = createMockData(),
            modifier = Modifier,
        )
    }
}