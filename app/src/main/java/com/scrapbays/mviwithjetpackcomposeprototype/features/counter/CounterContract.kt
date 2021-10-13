package com.scrapbays.mviwithjetpackcomposeprototype.features.counter

import com.scrapbays.mviwithjetpackcomposeprototype.models.IEffect
import com.scrapbays.mviwithjetpackcomposeprototype.models.IEvent
import com.scrapbays.mviwithjetpackcomposeprototype.models.IState

class CounterContract {
    sealed class CounterEvent: IEvent {
        object Increase: CounterEvent()
        object Decrease: CounterEvent()
        object NavigateToSecondScreen: CounterEvent()
        data class Name(val name: String): CounterEvent()
    }

    data class CounterState(
        val count: Int = 0,
        val name: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    ): IState

    sealed class CounterEffect: IEffect {
        object DisplayToast: CounterEffect()

        sealed class Navigation: CounterEffect() {
            object ToAboutScreen: Navigation()
        }
    }
}