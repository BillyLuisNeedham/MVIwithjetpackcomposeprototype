package com.scrapbays.mviwithjetpackcomposeprototype.features.counter

import com.scrapbays.mviwithjetpackcomposeprototype.models.IState

data class CounterState(
    val count: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
): IState
