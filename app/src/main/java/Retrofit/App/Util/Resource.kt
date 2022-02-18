package Retrofit.App.Util

sealed class Resource<T>(val data: T? = null) {
    class Ok<T>(data: T) : Resource<T>(data)
    class Failed<T> : Resource<T>()
}


