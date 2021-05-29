package com.example.kakao_search.support.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.kakao_search.exception.Failure


fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T: Any> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, task: (T?) -> Unit) =
    this.observe(lifecycleOwner, Observer(task))

fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer(body))