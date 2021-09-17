package com.farinas.cocktailsapp.application

import com.zhuinden.eventemitter.EventEmitter
import com.zhuinden.eventemitter.EventSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * Created by Erick Fariñas on 17/09/2021.
 */
@ActivityRetainedScoped
class ToastHelper @Inject constructor() {

    private val toastEmitter: EventEmitter<String> = EventEmitter()
    val toastMessages: EventSource<String> = toastEmitter

    fun sendToast(message: String) {
        toastEmitter.emit(message)
    }
}