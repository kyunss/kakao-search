package com.example.kakao_search.presentation.core

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import timber.log.Timber


abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        initializeListeners()
        observeViewModel()
    }

    abstract fun initializeViews()

    abstract fun initializeListeners()

    abstract fun observeViewModel()

    protected fun <T : Any> LiveData<T>.observeNonNull(observer: (T) -> Unit) {
        observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (this.value != null) {
                observer.invoke(this.value!!)
            } else {
                Timber.e("LiveData value is null")
            }
        })
    }

    protected fun <T : Any?> LiveData<T>.observe(observer: (T?) -> Unit) {
        observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            observer.invoke(this.value)
        })
    }

    protected fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }

}