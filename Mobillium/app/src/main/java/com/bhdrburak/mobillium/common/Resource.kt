package com.bhdrburak.mobillium.common

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> succes(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, "")
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, "")
        }

    }
}


enum class Status {

    SUCCESS,
    ERROR,
    LOADING
}