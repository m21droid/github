package com.m21droid.github

import android.util.Log
import androidx.navigation.NavBackStackEntry
import com.m21droid.github.data.BuildConfig.DEBUG

fun Any.logD(msg: String?) {
    if (DEBUG) Log.d(javaClass.simpleName, msg ?: return)
}

fun Any.logE(msg: String?) {
    if (DEBUG) Log.e(javaClass.simpleName, msg ?: return)
}

fun Any.logI(msg: String?) {
    if (DEBUG) Log.i(javaClass.simpleName, msg ?: return)
}

internal fun <T> NavBackStackEntry.take(key: String): T? {
    savedStateHandle.apply {
        get<T>(key)?.let {
            remove<T>(key)
            return it
        }
    }
    return null
}