package com.scrapbays.mviwithjetpackcomposeprototype.features.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scrapbays.mviwithjetpackcomposeprototype.models.IModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel(), IModel<CounterState, CounterIntent, CounterEffect> {

    override val intents: Channel<CounterIntent> = Channel(Channel.UNLIMITED)

    private val _effects: Channel<CounterEffect> = Channel()
    override val effects: Flow<CounterEffect>
        get() = _effects.receiveAsFlow()

    private val _state = MutableStateFlow(CounterState())
    override val state: Flow<CounterState>
        get() = _state

    init {
        intentHandler()
    }

    private fun intentHandler() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { counterIntent ->
                when (counterIntent) {
                    CounterIntent.Decrease -> setCount(_state.value.count - 1)
                    CounterIntent.Increase -> setCount(_state.value.count + 1)
                    CounterIntent.NavigateToSecondScreen -> setNavigationToSecondScreenInEffects()
                }
            }
        }
    }

    private fun setNavigationToSecondScreenInEffects() {
        viewModelScope.launch {
            _effects.send(CounterEffect.NavigateToSecondScreen)
        }
    }

    private fun setCount(count: Int) {
        _state.value = _state.value.copy(
            count = count
        )
    }

}