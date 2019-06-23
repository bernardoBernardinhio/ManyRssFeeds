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


class AdapterRecyclerViewNavigation internal constructor(private val arrayListProducts: ArrayList<RecyclerViewNavigationModel>) : RecyclerView.Adapter<AdapterRecyclerViewNavigation.ViewHolderRV>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRV {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewInflated = layoutInflater.inflate(R.layout.fragment_navigation_recyclerview, parent, false)
        return ViewHolderRV(viewInflated)
    }

    override fun onBindViewHolder(holder: ViewHolderRV, position: Int) {

        val (imageName, title, subtitle, rating) = arrayListProducts[position]


        // set image resource
        val context = holder.ivProductImage.context
        val id = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        try {
            holder.ivProductImage.setImageResource(id)
        }catch (e : Exception){
            Log.d("Bernard", e.message)
        }

        // set other views values
        holder.tvProductTitle.text = title
        holder.tvProductLocation.text = "In $subtitle"
        holder.tvProductPrice.text = "$rating€"
    }

    override fun getItemCount(): Int {
        return arrayListProducts.size
    }

    inner class ViewHolderRV(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProductImage: ImageView = itemView.findViewById<ImageView>(R.id.iv_fragment_image)
        val tvProductTitle: TextView = itemView.findViewById<TextView>(R.id.tv_fragment_title)
        val tvProductLocation: TextView = itemView.findViewById<TextView>(R.id.tv_fragment_subtitle)
        val tvProductPrice: TextView = itemView.findViewById<TextView>(R.id.tv_fragment_right_hint)
    }
}