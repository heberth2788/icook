package com.yape.icook.ui.detail

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yape.icook.R
import com.yape.icook.ui.domainentity.FoodRecipe
import com.yape.icook.ui.theme.ICookTheme

@Composable
fun DetailScreen() {

}

@Composable
fun DetailContent(
    foodRecipe: FoodRecipe,
    onClickBack: () -> Unit = { },
    onClickMap: () -> Unit = { },
    modifier: Modifier
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
                   Image(
                       imageVector = Icons.Filled.Info,
                       contentDescription = null,
                       modifier = modifier.size(150.dp),
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
    val myFoodRecipe = FoodRecipe(id = 9, name = "Ceviche", desc = "Delicious peruvian ceviche", ingredients = "1 pound jumbo shrimp, peeled and deveined.\n" +
                "5 large lemons, juiced, or as needed.\n" +
                "2 white onions, finely chopped.\n" +
                "1 large tomato, seeded and chopped.\n" +
                "1 cucumber, peeled and finely chopped.\n" +
                "1 bunch radishes, finely diced.\n" +
                "2 cloves fresh garlic, minced.\n" +
                "3 fresh jalapeño peppers, seeded and minced.", preparation = "Chunks of raw fish, " +
                "marinated in freshly squeezed key lime, with sliced onions, chili peppers, salt and " +
                "pepper. Corvina or cebo (sea bass) was the fish traditionally used")
    ICookTheme {
        DetailContent(
            foodRecipe = myFoodRecipe,
            modifier = Modifier,
        )
    }
}