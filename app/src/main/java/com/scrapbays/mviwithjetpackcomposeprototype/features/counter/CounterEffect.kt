package com.scrapbays.mviwithjetpackcomposeprototype.features.counter

import com.scrapbays.mviwithjetpackcomposeprototype.models.IEffect

sealed class CounterEffect: IEffect {
    object NavigateToSecondScreen: CounterEffect()
}
