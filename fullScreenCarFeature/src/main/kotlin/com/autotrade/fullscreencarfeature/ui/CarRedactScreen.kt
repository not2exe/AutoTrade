package com.autotrade.fullscreencarfeature.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.autotrade.common.EditText
import com.autotrade.fullscreencarfeature.ui.stateholders.CarRedactViewModel

@Composable
fun CarRedactScreen(viewModel: CarRedactViewModel, navigateBack: () -> Unit) {
    var id = remember { "" }
    val list = remember { mutableStateOf(listOf<CarRedactVo.EditText>()) }
    LaunchedEffect(key1 = Unit) {
        val carVo = viewModel.getCarFromDataStore()
        id = carVo.id
        list.value = carVo.entries
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigateBack.invoke()
                viewModel.saveToFireStore(
                    id,
                    list.value.map { pair -> pair.labelStringId to pair.mutableStateText.value })
            }) {
                Icon(Icons.Filled.Done, contentDescription = null)
            }
        })
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(list.value.size) { i ->
                EditText(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    sourceText = list.value[i].mutableStateText,
                    labelText = stringResource(id = list.value[i].labelStringId),
                    keyboardType = list.value[i].keyboardType
                )
            }
        }

    }
}