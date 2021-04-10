package com.example.simple_blog.data.remote.sources

import android.util.Log
import com.example.simple_blog.utils.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                Log.d(Companion.TAG, "getResult: isSuccessful true")
                val body = response.body()
                if (body != null){
                    Log.d(TAG, "getResult: body not null")
                    return Resource.success(body)
                }
            }
            Log.d(TAG, "getResult: outside")
            return Resource.error(" ${response.code()} ${response.message()}", data = response.body())
        } catch (throwable: Throwable) {
            Log.d(TAG, "getResult: inside catch ${throwable.toString()}")
            return when(throwable){
                is HttpException ->{
                    Resource.error(throwable.message ?: throwable.toString(), false, throwable.code(), throwable.response()?.errorBody())
                }
                is UnknownHostException ->{
                    Resource.error(throwable.message ?: throwable.toString(), true)
                }
                else ->{
                    Resource.error(throwable.message ?: throwable.toString(), true)
                }
            }
        }
    }

    /*private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network call has failed for a following reason: $message")
    }*/
    companion object {
        private const val TAG = "BaseDataSource"
    }

}