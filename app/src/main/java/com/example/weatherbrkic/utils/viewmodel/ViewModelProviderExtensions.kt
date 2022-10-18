package com.example.weatherbrkic.utils.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

private class InstanceViewModelFactory<T : ViewModel>(
    private val builder: () -> T
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return builder.invoke() as T
    }

}


fun <T : ViewModel> Fragment.viewModel(clazz: Class<T>, builder: () -> T): Lazy<T> {
    return lazy {
        ViewModelProvider(this, InstanceViewModelFactory(builder)).get(clazz)
    }
}

fun <T : ViewModel> AppCompatActivity.viewModel(clazz: Class<T>, builder: () -> T): Lazy<T> {
    return lazy {
        ViewModelProvider(this, InstanceViewModelFactory(builder)).get(clazz)
    }
}

inline fun <reified T : ViewModel> AppCompatActivity.viewModel(noinline builder: () -> T): Lazy<T> {
    return viewModel(T::class.java, builder)
}

inline fun <reified T : ViewModel> Fragment.viewModel(noinline builder: () -> T): Lazy<T> {
    return viewModel(T::class.java, builder)
}