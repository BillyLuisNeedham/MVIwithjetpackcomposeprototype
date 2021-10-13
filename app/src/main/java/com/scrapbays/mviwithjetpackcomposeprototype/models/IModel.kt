package com.scrapbays.mviwithjetpackcomposeprototype.models

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IModel<S: IState, I: IEvent, E: IEffect> {
    val intents: Channel<I>
    val effects: Flow<E>
    val state: StateFlow<S>
}