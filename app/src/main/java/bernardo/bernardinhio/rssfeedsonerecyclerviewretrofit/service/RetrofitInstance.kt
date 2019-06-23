package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.service

import me.toptas.rssconverter.RssConverterFactory
import me.toptas.rssconverter.RssFeed
import retrofit2.Call
import retrofit2.Retrofit

object RetrofitInstance {

    // singleton
    private var retrofitInstance: Retrofit? = null

    fun setupRetrofitCall(feedsBaseUrl : String, feedsUrlEndPoint : String) : Call<RssFeed> {

        val retrofit = Retrofit.Builder()
            .baseUrl(feedsBaseUrl)
            .addConverterFactory(RssConverterFactory.create())
            .build()

        val service = retrofit.create(RssService::class.java)

        return service.getRss(feedsUrlEndPoint)
    }
}