package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.view

import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.R
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.model.RecyclerViewContentItemModel
import com.squareup.picasso.Picasso
import java.net.URL


class AdapterRecyclerViewRssContent internal constructor(private val arrayListRssContent: ArrayList<RecyclerViewContentItemModel>) : RecyclerView.Adapter<AdapterRecyclerViewRssContent.ViewHolderRV>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRV {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewInflated = layoutInflater.inflate(R.layout.fragment_rss_content_recyclerview, parent, false)
        return ViewHolderRV(viewInflated)
    }

    override fun onBindViewHolder(holder: ViewHolderRV, position: Int) {

        val (description, image, link, publishDate, title) = arrayListRssContent[position]

        holder.tvFeedTitle.text = title?:""

        if (image.isNullOrEmpty()){
            holder.ivFeedImage.visibility = View.GONE
        }

        try {
            val bitmap1  = Picasso.get().load(image).into(holder.ivFeedImage)
        }catch (e : Exception){
            Log.d("ImageException", e.message!!)
        }

        holder.tvFeedPublishDate.text = publishDate?:""
        holder.tvFeedDescription.text = description?:""
        holder.tvFeedLink.text = link?:""
    }

    override fun getItemCount(): Int {
        return arrayListRssContent.size
    }

    inner class ViewHolderRV(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFeedTitle: TextView = itemView.findViewById<TextView>(R.id.tv_fragment_feed_title)
        val ivFeedImage: ImageView = itemView.findViewById<ImageView>(R.id.iv_fragment_feed_image)
        val tvFeedPublishDate: TextView = itemView.findViewById<TextView>(R.id.tv_fragment_feed_publish_date)
        val tvFeedDescription: TextView = itemView.findViewById<TextView>(R.id.tv_fragment_feed_description)
        val tvFeedLink: TextView = itemView.findViewById<TextView>(R.id.tv_fragment_feed_link)
    }
}