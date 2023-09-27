package com.bhdrburak.vbtmoviedb.common

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> succes(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, "istek başarılı")
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}