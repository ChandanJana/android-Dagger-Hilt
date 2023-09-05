package com.test.hiltapplication.utils

/**
 * Created by Chandan Jana on 21-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */

data class NetworkResult<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {

    companion object {
        fun <T> success(data: T): NetworkResult<T> {
            return NetworkResult<T>(Status.SUCCESS, data)
        }

        fun <T> error(message: String, data: T? = null): NetworkResult<T> {
            return NetworkResult<T>(Status.ERROR, data, message)
        }

        fun <T> loading(data: T?): NetworkResult<T> {
            return NetworkResult<T>(Status.LOADING, data, null)
        }
    }

    override fun toString(): String {
        return "NetworkResult(status=$status, data=$data, message=$message)"
    }


}