package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.view

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.R
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.model.RecyclerViewNavigationModel
import java.lang.Exception

import java.util.*


class AdapterRecyclerViewNavigation internal constructor(private val arrayListNavigation: ArrayList<RecyclerViewNavigationModel>) : RecyclerView.Adapter<AdapterRecyclerViewNavigation.ViewHolderRV>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRV {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewInflated = layoutInflater.inflate(R.layout.fragment_navigation_recyclerview, parent, false)
        return ViewHolderRV(viewInflated)
    }

    override fun onBindViewHolder(holder: ViewHolderRV, position: Int) {

        val (imageName, title, subtitle, rating) = arrayListNavigation[position]


        // set image resource
        val context = holder.ivNavigationImage.context
        val id = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        try {
            holder.ivNavigationImage.setImageResource(id)
        }catch (e : Exception){
            Log.d("ImageException", e.message)
        }

        // set other views values
        holder.tvNavigationTitle.text = title
        holder.tvNavigationLocation.text = subtitle
        holder.tvNavigationPrice.text = rating
    }

    override fun getItemCount(): Int {
        return arrayListNavigation.size
    }

    inner class ViewHolderRV(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivNavigationImage: ImageView = itemView.findViewById<ImageView>(R.id.iv_fragment_navigation_image)
        val tvNavigationTitle: TextView = itemView.findViewById<TextView>(R.id.tv_fragment_navigation_title)
        val tvNavigationLocation: TextView = itemView.findViewById<TextView>(R.id.tv_fragment_navigation_subtitle)
        val tvNavigationPrice: TextView = itemView.findViewById<TextView>(R.id.tv_fragment_right_navigation_hint)
    }
}