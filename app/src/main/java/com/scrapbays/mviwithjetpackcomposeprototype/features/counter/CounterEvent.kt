package com.scrapbays.mviwithjetpackcomposeprototype.features.counter

import com.scrapbays.mviwithjetpackcomposeprototype.models.IEvent

sealed class CounterEvent: IEvent {
    object Increase: CounterEvent()
    object Decrease: CounterEvent()
    object NavigateToSecondScreen: CounterEvent()
}