/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.weatherbrkic
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherbrkic.data.model.AccuWeatherApiStatus
import com.example.weatherbrkic.data.model.Location
import com.example.weatherbrkic.ui.locations.adapters.LocationsAdapter


@BindingAdapter("cityNames")
fun bindCities(textView: TextView, name: String?) {

    name?.let {
        textView.text = name.toString()
    }
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: AccuWeatherApiStatus) {
    when (status) {
        AccuWeatherApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        AccuWeatherApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        AccuWeatherApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("options")
fun bindOptions(view: AutoCompleteTextView, options:List<Location>?) {
    val adapter = view.adapter as LocationsAdapter
    if (options != null) {
        adapter.clear()
        adapter.addAll(options)
    }
}

