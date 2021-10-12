package com.scrapbays.mviwithjetpackcomposeprototype.features.counter

sealed class CounterEvent {
    object Increase: CounterEvent()
    object Decrease: CounterEvent()
    object NavigateToSecondScreen: CounterEvent()
}