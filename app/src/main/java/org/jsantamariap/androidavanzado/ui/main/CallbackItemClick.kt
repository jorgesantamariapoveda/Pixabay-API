package org.jsantamariap.androidavanzado.ui.main

import org.jsantamariap.androidavanzado.repository.model.ApodResponse

//! Es el equivalente a los protocolos de Swift
interface CallbackItemClick {
    fun onItemClick(apodResponse: ApodResponse)
}