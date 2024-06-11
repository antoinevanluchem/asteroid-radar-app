package com.udacity.asteroidradar

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.main.AsteroidListAdapter

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
        imageView.contentDescription = imageView.context.getString(R.string.potentially_hazardous_asteroid_status_icon)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
        imageView.contentDescription = imageView.context.getString(R.string.not_hazardous_asteroid_status_icon)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
        imageView.contentDescription = imageView.context.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
        imageView.contentDescription = imageView.context.getString(R.string.not_hazardous_asteroid_image)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("android:text")
fun setText(view: TextView, value: Long) {
    view.text = value.toString()
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Asteroid>?) {
    val adapter = recyclerView.adapter as AsteroidListAdapter
    adapter.submitList(data)
}

@BindingAdapter("pictureOfDayImg")
fun bindPictureOfDayImg(imageView: ImageView, pictureOfDay: PictureOfDay?) {
    val context = imageView.context

    if (pictureOfDay == null) {
        imageView.apply {
            setImageResource(R.drawable.placeholder_picture_of_day)
            contentDescription = context.getString(R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet)
        }
        return
    }

    if (pictureOfDay.url.isBlank()) {
        imageView.apply {
            setImageResource(R.drawable.broken_image)
            contentDescription = context.getString(R.string.this_is_nasa_s_picture_of_day_but_something_went_wrong_while_fetching)
        }
        return
    }

    Picasso.with(context)
        .load(pictureOfDay.url)
        .placeholder(R.drawable.placeholder_picture_of_day)
        .error(R.drawable.broken_image)
        .fit()
        .centerCrop()
        .into(imageView, object : com.squareup.picasso.Callback {
            override fun onSuccess() {
                imageView.contentDescription = String.format(
                    context.getString(R.string.nasa_picture_of_day_content_description_format),
                    pictureOfDay.title
                )
            }

            override fun onError() {
                imageView.contentDescription =
                    context.getString(R.string.this_is_nasa_s_picture_of_day_but_something_went_wrong_while_fetching)
            }
        })
}

