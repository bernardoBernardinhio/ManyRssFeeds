package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import bernardo.bernardinhio.retrofit2rssconverterfactory.service.RetrofitInstance
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.R
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.`data-navigation`.NavigationDataProvider
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.model.RecyclerViewContentItemModel
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//

class MainActivity : AppCompatActivity() {

    private var drawerLayout: DrawerLayout? = null
    private var recyclerViewNavigation: RecyclerView? = null
    private var adapterRecyclerViewNavigation: AdapterRecyclerViewNavigation? = null
    private val arrayListNavigation = NavigationDataProvider.Companion.cloneDataProvider()
    private lateinit var toolbar : Toolbar
    private lateinit var drawerToggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        recyclerViewNavigation = findViewById<RecyclerView>(R.id.navigation_recyclerview)


        val layoutManager = LinearLayoutManager(this)
        recyclerViewNavigation!!.setLayoutManager(layoutManager)

        adapterRecyclerViewNavigation =
            AdapterRecyclerViewNavigation(arrayListNavigation)
        recyclerViewNavigation!!.adapter = adapterRecyclerViewNavigation
        adapterRecyclerViewNavigation?.notifyDataSetChanged()


        drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )

        drawerLayout!!.addDrawerListener(drawerToggle)

        drawerToggle.syncState()


    }



    fun getDataFromRssResponse(baseUrl : String, endPoint : String) : ArrayList<RecyclerViewContentItemModel>{

        var arrayListContentRecyclerView : ArrayList<RecyclerViewContentItemModel> = ArrayList<RecyclerViewContentItemModel>()

        // ex   https://www.cinemablend.com/rss/topic/reviews/movies
        val call : Call<RssFeed> = RetrofitInstance.setupRetrofitCall(
            "https://www.cinemablend.com/",
            "topic/reviews/movies"
        )


        call.enqueue(object : Callback<RssFeed> {
            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {

                val listItems : List<RssItem>? = response.body()?.items

                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()

                listItems?.let {
                    for (item in listItems){

                        arrayListContentRecyclerView.add(RecyclerViewContentItemModel(
                            description = item.description,
                            image = item.image,
                            link = item.link,
                            publishDate = item.publishDate,
                            title = item.title
                        ))
                    }
                }

            }

            override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                Toast.makeText(this@MainActivity, "API call failed!", Toast.LENGTH_SHORT).show()
            }
        })

        return arrayListContentRecyclerView
    }
}
