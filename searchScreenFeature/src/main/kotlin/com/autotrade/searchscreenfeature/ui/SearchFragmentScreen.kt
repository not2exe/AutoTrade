package com.autotrade.searchscreenfeature.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.paging.compose.collectAsLazyPagingItems
import com.autotrade.common.carcommunication.CarFields
import com.autotrade.common.EditText
import com.autotrade.searchscreenfeature.R
import com.autotrade.searchscreenfeature.ui.stateholders.SearchCarViewModel
import com.google.firebase.firestore.Query

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFragmentScreen(
    viewModel: SearchCarViewModel,
    navigateToRedactFragment: () -> Unit
) {
    val cars = viewModel.getCars().collectAsLazyPagingItems()
    val isFiltersOrSortByApplied =
        viewModel.isFilterOrSortByApplied().collectAsState(initial = false).value
    val fabOnClick: () -> Unit
    val fabPos: FabPosition
    if (isFiltersOrSortByApplied) {
        fabOnClick = {
            viewModel.clearOrderedBy()
            viewModel.clearFilters()
        }
        fabPos = FabPosition.Center
    } else {
        fabOnClick = navigateToRedactFragment
        fabPos = FabPosition.End
    }


    var showFiltersDialog by remember { mutableStateOf(false) }
    var showSortDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = fabOnClick) {
                if (isFiltersOrSortByApplied) {
                    Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 20.dp)) {
                        Text(text = stringResource(id = R.string.clear_filters))
                        Spacer(modifier = Modifier.size(8.dp))
                        Icon(Icons.Filled.Clear, contentDescription = null)
                    }
                } else {
                    Icon(Icons.Filled.Add, contentDescription = null)
                }
            }
        },
        floatingActionButtonPosition = fabPos,
        topBar = {
            TopAppBar(modifier = Modifier.height(48.dp), title = {}, actions = {
                Icon(
                    modifier = Modifier
                        .clickable { showFiltersDialog = true }
                        .height(48.dp),
                    painter = painterResource(id = R.drawable.ic_filter_36),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Icon(modifier = Modifier
                    .clickable { showSortDialog = true }
                    .height(48.dp),
                    painter = painterResource(id = R.drawable.ic_sort_36),
                    contentDescription = null)
            }, colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            if (showFiltersDialog) {
                Dialog(onDismissRequest = { showFiltersDialog = false }) {
                    FiltersDialog(viewModel) { showFiltersDialog = false }
                }
            }
            if (showSortDialog) {
                Dialog(onDismissRequest = { showSortDialog = false }) {
                    SortByDialog(viewModel) { showSortDialog = false }
                }
            }
            LazyColumn {
                items(count = cars.itemCount) { i ->
                    if (cars[i] != null) {
                        val marginTop = if (i == 0) {
                            32.dp
                        } else 16.dp
                        Spacer(modifier = Modifier.size(marginTop))
                        CarCard(
                            card = cars[i]!!,
                            viewModel = viewModel,
                            navigateToRedactFragment = navigateToRedactFragment
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }

            }
        }
    }
}

@Composable
fun CarCard(card: CarVo, viewModel: SearchCarViewModel, navigateToRedactFragment: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                viewModel.onChooseCar(card.carDomain)
                navigateToRedactFragment.invoke()
            }, shape = RectangleShape
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = card.fullName, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = card.price, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.size(20.dp))
            DoubleTextRow(str1 = card.mileage, str2 = card.color)
            DoubleTextRow(str1 = card.engineCharacteristics, str2 = card.drive)
            DoubleTextRow(str1 = card.condition, str2 = card.body)
        }
    }

}

@Composable
fun DoubleTextRow(
    str1: String, str2: String, textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Row {
        Text(
            text = str1, modifier = Modifier.weight(1f), style = textStyle
        )
        Text(
            text = str2, modifier = Modifier.weight(1f), style = textStyle
        )
    }
}

@Composable
fun FiltersDialog(
    viewModel: SearchCarViewModel, closeDialog: () -> Unit
) {
    val brandText = remember { mutableStateOf("") }
    val modelText = remember { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        val filters = viewModel.getFilters()
        val brand = filters?.get(CarFields.BRAND.string) ?: ""
        val model = filters?.get(CarFields.MODEL.string) ?: ""
        brandText.value = brand
        modelText.value = model
    }

    CardDialogWithApplyButton(header = stringResource(id = R.string.filters), onClickApply = {
        viewModel.changeFilters(brandText.value, modelText.value)
        closeDialog.invoke()
    }) {
        EditText(
            modifier = Modifier.fillMaxWidth(),
            brandText,
            labelText = stringResource(id = R.string.brand)
        )
        Spacer(modifier = Modifier.size(16.dp))
        EditText(
            modifier = Modifier.fillMaxWidth(),
            modelText,
            labelText = stringResource(id = R.string.model)
        )
    }
}

@Composable
fun SortByDialog(
    viewModel: SearchCarViewModel, closeDialog: () -> Unit
) {
    var selected by remember { mutableStateOf(false) }
    var direction by remember { mutableStateOf(Query.Direction.DESCENDING) }
    LaunchedEffect(key1 = Unit) {
        val pair = viewModel.getSortedBy()
        selected = pair?.first == CarFields.PRICE.string
        direction = pair?.second ?: Query.Direction.DESCENDING
    }
    CardDialogWithApplyButton(header = stringResource(id = R.string.sort), onClickApply = {
        if (selected) {
            viewModel.setOrderedBy(CarFields.PRICE.string, direction)
        } else {
            viewModel.clearOrderedBy()
        }
        closeDialog.invoke()
    }) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { selected = !selected }
            .padding(8.dp)) {
            Text(
                modifier = Modifier.align(CenterVertically),
                text = stringResource(id = R.string.by_price),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            RadioButton(
                selected = selected,
                onClick = { selected = !selected },
                modifier = Modifier.align(CenterVertically)
            )
            val isDescending = direction == Query.Direction.DESCENDING
            IconButton(onClick = {
                direction = if (isDescending) {
                    Query.Direction.ASCENDING
                } else {
                    Query.Direction.DESCENDING
                }
            }) {
                if (isDescending) {
                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = null)
                } else {
                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription = null)

                }

            }
        }
    }
}

@Composable
fun CardDialogWithApplyButton(
    header: String,
    onClickApply: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ), shape = RoundedCornerShape(CornerSize(8.dp))
    ) {
        Text(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            text = header,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(16.dp))
        content.invoke(this)
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(CornerSize(8.dp)),
            onClick = onClickApply
        ) {
            Text(stringResource(id = R.string.apply))
        }
    }

}
