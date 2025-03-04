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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState
import com.yape.icook.R
import com.yape.icook.data.entity.FoodRecipeResponse
import com.yape.icook.mock.mockFoodRecipeResponse
import com.yape.icook.ui.theme.ICookTheme

@Composable
fun MapScreen(
    mapViewModel: MapViewModel = hiltViewModel(),
    foodRecipeId: Int,
    navigateBack: () -> Unit,
) {
    MapContent(
        foodRecipeResponse = mapViewModel.foodRecipeResponse,
        onClickBack = navigateBack,
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
    foodRecipeResponse: FoodRecipeResponse,
    onClickBack: () -> Unit = { },
    modifier: Modifier,
) {
    Scaffold(
        topBar = {
            MapTopBar(
                onClickBack = onClickBack,
                foodRecipeResponse = foodRecipeResponse,
                modifier = modifier,
            )
        }
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = modifier.padding(paddingValues)
        ) {
            CookMap(
                foodRecipeResponse = foodRecipeResponse,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun CookMap(
    foodRecipeResponse: FoodRecipeResponse,
    modifier: Modifier,
) {
    val recipePosition = LatLng(foodRecipeResponse.lat, foodRecipeResponse.lng)
    var uiSettings: MapUiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties: MapProperties by remember { mutableStateOf(MapProperties(mapType = MapType.NORMAL)) }
    val markerState: MarkerState = rememberUpdatedMarkerState(position = recipePosition)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(recipePosition, 11.5f)
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
            LaunchedEffect(key1 = recipePosition) {
                cameraPositionState.move(CameraUpdateFactory.newLatLng(recipePosition))
            }
            Marker(
                state = markerState,
                title = foodRecipeResponse.name,
                snippet = foodRecipeResponse.desc,
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
    foodRecipeResponse: FoodRecipeResponse,
    modifier: Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "${ foodRecipeResponse.name } ${ stringResource(R.string.map_text) }")
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
        MapContent(
            foodRecipeResponse = mockFoodRecipeResponse,
            modifier = Modifier,
        )
    }
}