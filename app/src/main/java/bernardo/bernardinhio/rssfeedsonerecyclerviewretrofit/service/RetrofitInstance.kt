package bernardo.bernardinhio.retrofit2rssconverterfactory.service

import me.toptas.rssconverter.RssConverterFactory
import me.toptas.rssconverter.RssFeed
import retrofit2.Call
import retrofit2.Retrofit

object RetrofitInstance {

    // singleton
    private var retrofitInstance: Retrofit? = null

    // the baseUrl SHOULD always end with /
    private val BASE_URL = "https://www.cinemablend.com/"

    fun setupRetrofitCall(baseUrl : String, endPoint : String) : Call<RssFeed> {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.cinemablend.com/")
            .addConverterFactory(RssConverterFactory.create())
            .build()

        val service = retrofit.create(RssService::class.java)

        return service.getRss("rss/topic/reviews/movies")
    }
}