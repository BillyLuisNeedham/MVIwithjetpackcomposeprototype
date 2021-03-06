package com.scrapbays.mviwithjetpackcomposeprototype.features.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scrapbays.mviwithjetpackcomposeprototype.models.IModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel(),
    IModel<CounterContract.CounterState, CounterContract.CounterEvent, CounterContract.CounterEffect> {

    override val intents: Channel<CounterContract.CounterEvent> = Channel(Channel.UNLIMITED)

    private val _effects: Channel<CounterContract.CounterEffect> = Channel()
    override val effects: Flow<CounterContract.CounterEffect>
        get() = _effects.receiveAsFlow()

    private val _state = MutableStateFlow(CounterContract.CounterState())
    override val state: StateFlow<CounterContract.CounterState>
        get() = _state

    init {
        intentHandler()
    }

    private fun intentHandler() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { counterIntent ->
                when (counterIntent) {
                    CounterContract.CounterEvent.Decrease -> setCountHandler(_state.value.count - 1)
                    CounterContract.CounterEvent.Increase -> setCountHandler(_state.value.count + 1)
                    CounterContract.CounterEvent.NavigateToSecondScreen -> sendNavigationToSecondScreenEffect()
                    is CounterContract.CounterEvent.Name -> setName(counterIntent.name)
                }
            }
        }
    }

    private fun setCountHandler(count: Int) {
        when {
            count < 0 -> sendDisplayToastEffect()
            count > 10 -> sendNavigationToSecondScreenEffect()
            else -> setCount(count = count)
        }
    }

    private fun setName(name: String) {
        _state.value = _state.value.copy(
            name = name
        )
    }

    private fun sendNavigationToSecondScreenEffect() {
        viewModelScope.launch {
            _effects.send(CounterContract.CounterEffect.Navigation.ToAboutScreen)
        }
    }

    private fun sendDisplayToastEffect() {
        viewModelScope.launch {
            _effects.send(CounterContract.CounterEffect.DisplayToast("Can't go below zero"))
        }
    }

    private fun setCount(count: Int) {
        _state.value = _state.value.copy(
            count = count
        )
    }

}