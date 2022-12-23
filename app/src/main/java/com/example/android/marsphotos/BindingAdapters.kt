package com.example.android.marsphotos

import android.view.View
import com.example.android.marsphotos.overview.PhotoGridAdapter
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.MarsApiStatus

@BindingAdapter("imageUrl")
fun bindImage(iv: ImageView, imgurl: String?) {
    imgurl?.let {
        //val imgUri = imgurl.toUri().buildUpon().scheme("https").build()
        iv.load(imgurl){
            crossfade(true)
            error(R.drawable.ic_broken_image)
            placeholder(R.drawable.loading_animation)
        }


    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<MarsPhoto>?
) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MarsApiStatus?) {
    when(status){
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {}
    }

}