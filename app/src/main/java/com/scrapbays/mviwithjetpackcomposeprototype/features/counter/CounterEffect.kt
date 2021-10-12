package com.scrapbays.mviwithjetpackcomposeprototype.features.counter

sealed class CounterEffect {
    object NavigateToSecondScreen : CounterEffect()
}
