package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.dataprovider

import android.util.Log
import android.widget.Toast
import bernardo.bernardinhio.retrofit2rssconverterfactory.service.RetrofitInstance
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.model.RecyclerViewContentItemModel
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RssContentDataProvider{

    companion object {

        fun getDataFromRssResponse(baseUrl : String, endPoint : String) : ArrayList<RecyclerViewContentItemModel>{

            val arrayListContentRecyclerView : ArrayList<RecyclerViewContentItemModel> = ArrayList<RecyclerViewContentItemModel>()

            // ex   https://www.cinemablend.com/rss/topic/reviews/movies
            val call : Call<RssFeed> = RetrofitInstance.setupRetrofitCall(
                "https://www.cinemablend.com/",
                "topic/reviews/movies"
            )

            call.enqueue(object : Callback<RssFeed> {
                override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {

                    Log.d("RssFeed", "Success")

                    val listItems : List<RssItem>? = response.body()?.items

                    listItems?.let {
                        for (item in listItems){

                            arrayListContentRecyclerView.add(
                                RecyclerViewContentItemModel(
                                    description = item.description,
                                    image = item.image,
                                    link = item.link,
                                    publishDate = item.publishDate,
                                    title = item.title
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                    Log.d("RssFeed", t.message)
                }
            })

            return arrayListContentRecyclerView
        }
    }
}