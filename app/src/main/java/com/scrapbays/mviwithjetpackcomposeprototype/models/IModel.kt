package com.scrapbays.mviwithjetpackcomposeprototype.models

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

interface IModel<S: IState, I: IIntent> {
    val intents: Channel<I>
    val state: Flow<S>
}