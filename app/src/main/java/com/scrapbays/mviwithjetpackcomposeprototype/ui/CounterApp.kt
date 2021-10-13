package com.scrapbays.mviwithjetpackcomposeprototype.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.scrapbays.mviwithjetpackcomposeprototype.features.counter.CounterContract
import com.scrapbays.mviwithjetpackcomposeprototype.features.counter.CounterScreen
import com.scrapbays.mviwithjetpackcomposeprototype.features.counter.CounterViewModel
import kotlinx.coroutines.launch

@Composable
fun CounterApp() {
    val viewModel: CounterViewModel = viewModel()
    val state = viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    CounterScreen(
        state = state.value,
        effectFlow = viewModel.effects,
        onAddClicked = {
            coroutineScope.launch {
                viewModel.intents.send(CounterContract.CounterEvent.Increase)
            }
        },
        onMinusClicked = {
            coroutineScope.launch {
                viewModel.intents.send(CounterContract.CounterEvent.Decrease)
            }
        },
        onNameChanged = { name ->
            coroutineScope.launch {
                viewModel.intents.send(
                    CounterContract.CounterEvent.Name(name)
                )
            }
        }
    )
}