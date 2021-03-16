package com.duyha.github.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duyha.github.R
import com.duyha.github.utils.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.io.IOException

abstract class BaseViewModel : ViewModel() {

    companion object {
        const val TAG = "BaseViewModel"
    }

    protected val _msg  = MutableLiveData<Event<Int>>()
    val msg: LiveData<Event<Int>>
        get() = _msg

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }

    private fun handleException(e: Throwable) {
        Log.e(TAG, e.stackTraceToString())
        when (e) {
            is HttpException -> {
                if (e.code() == 401)
                    _msg.postValue(Event(R.string.err_msg_unauthorized))
                else
                    _msg.postValue(Event(R.string.err_msg_unexpected_error_occurred))
            }
            is IOException -> {
                _msg.postValue(Event(R.string.err_msg_no_internet_connection))
            }
            else -> {
                _msg.postValue(Event(R.string.err_msg_unexpected_error_occurred))
            }
        }
    }

    protected fun executeWithExceptionHandler(ignore: Boolean = false, block: () -> Unit) {
        try {
            block()
        } catch (e: Exception) {
            if (!ignore)
                handleException(e)
        }
    }
}