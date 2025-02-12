package com.yape.icook.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.yape.icook.R
import com.yape.icook.ui.domainentity.FoodRecipe
import com.yape.icook.ui.theme.ICookTheme

@Composable
fun MapScreen() {

}

@Composable
fun MapContent(
    foodRecipe: FoodRecipe,
    onClickBack: () -> Unit = { },
    modifier: Modifier,
) {
    Scaffold(
        topBar = {
            MapTopBar(
                onClickBack = onClickBack,
                modifier = modifier,
            )
        }
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = modifier.padding(paddingValues)
        ) {
            CookMap(
                foodRecipe = foodRecipe,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun CookMap(
    foodRecipe: FoodRecipe,
    modifier: Modifier,
) {
    val singapore = LatLng(foodRecipe.lat, foodRecipe.lon)
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    var properties by remember { mutableStateOf(MapProperties(mapType = MapType.SATELLITE)) }
    val singaporeMarkerState = rememberMarkerState(position = singapore)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 18f)
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        GoogleMap(
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings,
            modifier = modifier.matchParentSize(),
        ) {
            Marker(
                state = singaporeMarkerState,
                title = foodRecipe.name,
                snippet = foodRecipe.desc,
            )
        }
        Switch(
            checked = uiSettings.zoomControlsEnabled,
            onCheckedChange = {
                uiSettings = uiSettings.copy(zoomControlsEnabled = it)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapTopBar(
    onClickBack: () -> Unit,
    modifier: Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.map_text))
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
fun MapContentPreview() {
    val myFoodRecipe = FoodRecipe(
        id = 9,
        name = "Ceviche",
        desc = "Delicious ceviche",
        lat = -13.163253,
        lon = -72.5449074,
        ingredients = "1 pound jumbo shrimp, peeled and deveined.\n" +
            "5 large lemons, juiced, or as needed.\n" +
            "2 white onions, finely chopped.\n" +
            "1 large tomato, seeded and chopped.\n" +
            "1 cucumber, peeled and finely chopped.\n" +
            "1 bunch radishes, finely diced.\n" +
            "2 cloves fresh garlic, minced.\n" +
            "3 fresh jalapeño peppers, seeded and minced.",
        preparation = "chunks of raw fish, " +
            "marinated in freshly squeezed key lime, with sliced onions, chili peppers, salt and " +
            "pepper. Corvina or cebo (sea bass) was the fish traditionally used"
    )
    ICookTheme {
        MapContent(
            foodRecipe = myFoodRecipe,
            modifier = Modifier,
        )
    }
}