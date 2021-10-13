package com.scrapbays.mviwithjetpackcomposeprototype.features.counter

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun CounterScreen(
    state: CounterContract.CounterState,
    effectFlow: Flow<CounterContract.CounterEffect>?,
    onAddClicked: () -> Unit,
    onMinusClicked: () -> Unit,
    onNameChanged: (String) -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(effectFlow) {
        effectFlow?.collect { effect ->
            when (effect) {
                CounterContract.CounterEffect.DisplayToast -> showToast(
                    message = "Can't go below zero",
                    context = context
                )
                CounterContract.CounterEffect.Navigation.ToAboutScreen -> showToast(
                    "Eventually you will navigate somewhere, just wait",
                    context = context
                )
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Counter") }
            )
        }
    ) {
        CounterScreenContent(
            modifier = Modifier.fillMaxSize(),
            state = state,
            onAddClicked = onAddClicked,
            onMinusClicked = onMinusClicked,
            onNameChanged = onNameChanged
        )
    }
}

private fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG)
        .show()
}

@Composable
fun CounterScreenContent(
    modifier: Modifier = Modifier,
    state: CounterContract.CounterState,
    onAddClicked: () -> Unit,
    onMinusClicked: () -> Unit,
    onNameChanged: (String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello ${state.name}",
            style = MaterialTheme.typography.h4
        )
        Text(
            text = state.count.toString(),
            style = MaterialTheme.typography.h5
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onMinusClicked) {
                Text("Minus")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onAddClicked) {
                Text("Plus")
            }

        }
        TextField(
            value = state.name,
            onValueChange = onNameChanged
        )
    }
}
