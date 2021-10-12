package com.scrapbays.mviwithjetpackcomposeprototype.models

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

interface IModel<S: IState, I: IIntent, E: IEffect?> {
    val intents: Channel<I>
    val effects: Flow<E>
    val state: Flow<S>
}