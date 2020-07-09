package com.sam.demofirewallpaper

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.list_single_item.view.*


class WallpapersListAdaper(
    var wallpaperList: List<WallpapersModel>,
    private val clickListener:
    HomeFragment
) : RecyclerView.Adapter<WallpapersListAdaper.WallpapersViewHolder>() {

    class WallpapersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(wallpapers: WallpapersModel, clickListener: HomeFragment) {
            // Load the Image
            Glide.with(itemView.context).load(wallpapers.thumbnail).listener(
                object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.list_single_progress.visibility = View.GONE
                        return false
                    }
                }
            ).into(itemView.list_single_image)
            //click Listener
            itemView.setOnClickListener{
                clickListener(wallpapers)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpapersViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_single_item, parent, false)
        return WallpapersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wallpaperList.size
    }

    override fun onBindViewHolder(holder: WallpapersViewHolder, position: Int) {
        (holder as WallpapersViewHolder).bind(wallpaperList[position], clickListener)
    }
}