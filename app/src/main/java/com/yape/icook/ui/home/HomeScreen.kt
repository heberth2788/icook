package com.yape.icook.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yape.icook.R
import com.yape.icook.ui.domainentity.FoodRecipe
import com.yape.icook.ui.theme.ICookTheme


/**
 * The home screen implementing State hoisting
 */
@Composable
fun HomeScreen(
//    homeViewModel: HomeViewModel = hiltViewModel(), // when using hilt in ViewModel
//    homeViewModel: HomeViewModel = HomeViewModel(), // when not using hilt in ViewModel
//    homeViewModel: HomeViewModel = viewModel(), // when not using hilt in ViewModel
    homeViewModel: HomeViewModel = viewModel(),
) {
    val homeUiState: HomeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
    var textToSearch: String by remember { mutableStateOf("") }

   HomeContent(
       textToSearch = textToSearch,
       onQueryChange = { textToSearch = it },
       onSearch = { },
       onActiveChange = { },
       foodRecipes = homeUiState.foodRecipes,
       onClickFoodRecipe = { },
       modifier = Modifier,
   )
}

/**
 * The home content
 */
@Composable
fun HomeContent(
//    onClickSearch: () -> Unit = { },
    textToSearch: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    foodRecipes: List<FoodRecipe> = emptyList(),
    onClickFoodRecipe: () -> Unit = { },
    modifier: Modifier,
) {
    Scaffold(
        topBar = {
//                HomeTopBar(
//                    onClickSearch = onClickSearch,
//                    modifier = modifier,
//                )
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
            items(foodRecipes) {foodRecipe: FoodRecipe ->
                HorizontalDivider()
                FoodRecipeItem(
                    foodRecipe = foodRecipe,
                    onClickFoodRecipe = onClickFoodRecipe,
//                    onClickFoodRecipeMap = onClickFoodRecipeMap,
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
    foodRecipe: FoodRecipe,
    onClickFoodRecipe: () -> Unit,
//    onClickFoodRecipeMap: () -> Unit,
    modifier: Modifier,
) {
    OutlinedCard(
        onClick = onClickFoodRecipe,
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
//            AsyncImage(
//                model = foodRecipe.imageUrl,
//                placeholder = painterResource(android.R.drawable.ic_menu_report_image),
//                contentScale = ContentScale.Crop,
//                contentDescription = null,
//                modifier = modifier.weight(0.2f).size(50.dp)
//            )
            Image(
                imageVector = Icons.Filled.Info,
                contentDescription = null,
                modifier = modifier.weight(0.2f).size(50.dp)
            )
            Text(
                text = foodRecipe.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.weight(0.8f),
            )
//        IconButton(
//            modifier = modifier.weight(0.2f),
//            onClick = onClickFoodRecipeMap,
//        ) {
//            Icon(imageVector = Icons.Filled.Place, contentDescription = null)
//        }
        }
    }
}

/**
 * Compose Top app bar with title and search icon
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    onClickSearch: () -> Unit,
    modifier: Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        actions = {
            IconButton(
                onClick = onClickSearch,
            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            }
        },
        modifier = modifier,
    )
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
//        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
//        trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
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
//        HomeContent(
//            textToSearch = "",
//            onQueryChange = { },
//            onSearch = { },
//            onActiveChange = { },
//            foodRecipes = createMockData(),
//            modifier = Modifier,
//        )
        HomeScreen()
    }
}