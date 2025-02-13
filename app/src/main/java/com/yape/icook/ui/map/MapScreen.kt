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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
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
fun MapScreen(
    mapViewModel: MapViewModel = hiltViewModel(),
    navHostController: NavHostController,
    foodRecipeId: Int,
) {
    MapContent(
        foodRecipe = mapViewModel.foodRecipe,
        onClickBack = { navHostController.navigateUp() },
        modifier = Modifier,
    )

    // Execute when the Composable is first composed and get
    // the food recipe list from the server at first time and
    // filter it by foodRecipeId
    LaunchedEffect(key1 = Unit) {
        mapViewModel.loadFoodRecipe(foodRecipeId)
    }
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
                foodRecipe = foodRecipe,
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
    val location = LatLng(foodRecipe.lat, foodRecipe.lon)
//    val location = LatLng(-7.241207, -79.4720119)
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
    val markerState = rememberMarkerState(position = location)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 11.5f)
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
                state = markerState,
                title = foodRecipe.name,
                snippet = "${ foodRecipe.desc } : lat=${ foodRecipe.lat } | lon=${ foodRecipe.lon }",
//                snippet = "${ foodRecipe.desc } : lat=${ location.latitude } | lon=${ location.longitude }",
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
    foodRecipe: FoodRecipe,
    modifier: Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "${ foodRecipe.name } ${ stringResource(R.string.map_text) }")
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
    ICookTheme {
//        MapScreen()
    }
}