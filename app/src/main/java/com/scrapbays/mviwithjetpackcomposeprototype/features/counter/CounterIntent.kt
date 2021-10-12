package com.scrapbays.mviwithjetpackcomposeprototype.features.counter

import com.scrapbays.mviwithjetpackcomposeprototype.models.IIntent

sealed class CounterIntent: IIntent {
    object Increase: CounterIntent()
    object Decrease: CounterIntent()
    object NavigateToSecondScreen: CounterIntent()
}