package com.autotrade.fullscreencarfeature.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.autotrade.common.EditText
import com.autotrade.fullscreencarfeature.R
import com.autotrade.fullscreencarfeature.ui.stateholders.CarRedactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarRedactScreen(viewModel: CarRedactViewModel, navigateBack: () -> Unit) {
    val context = LocalContext.current
    var id = remember { "" }
    val list = remember { mutableStateOf(listOf<CarRedactVo.EditText>()) }
    LaunchedEffect(key1 = Unit) {
        val carVo = viewModel.getCarFromDataStore()
        id = carVo.id
        list.value = carVo.entries
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(48.dp),
                title = {},
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                navigateBack.invoke()
                            }
                            .padding(6.dp),
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (list.value.any { it.mutableStateText.value == "" }) {
                    Toast.makeText(context, R.string.fill_all_fields, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    navigateBack.invoke()
                    viewModel.saveToFireStore(
                        id,
                        list.value.map { pair -> pair.labelStringId to pair.mutableStateText.value })
                }
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
                val bottomSpace = if (i == list.value.size - 1) 350.dp else 8.dp
                Spacer(modifier = Modifier.size(8.dp))
                EditText(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    sourceText = list.value[i].mutableStateText,
                    labelText = stringResource(id = list.value[i].labelStringId),
                    keyboardType = list.value[i].keyboardType,
                    conditionToInput = list.value[i].condition
                )
                Spacer(modifier = Modifier.size(bottomSpace))
            }
        }
    }
}