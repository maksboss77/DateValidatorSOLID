package com.savelev.datevalidatorsolid

class MyPresenter<T> {

    private lateinit var listener: (T) -> Unit

    fun addListener(listener: (T) -> Unit) {
        this.listener = listener
    }

    fun publishEvent(saveDateClicked: T) {
        listener(saveDateClicked)
    }
}