package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.R
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.dataprovider.NavigationDataProvider
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.model.RecyclerViewContentItemModel
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.service.RetrofitInstance.setupRetrofitCall
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var drawerLayout: DrawerLayout? = null
    private lateinit var toolbar : Toolbar
    private lateinit var drawerToggle : ActionBarDrawerToggle

    private lateinit var recyclerViewNavigation: RecyclerView
    private lateinit var adapterRecyclerViewNavigation: AdapterRecyclerViewNavigation
    private val arrayListNavigation = NavigationDataProvider.Companion.cloneDataProvider()

    private lateinit var recyclerViewRssContent: RecyclerView
    private lateinit var adapterRecyclerViewRssContent: AdapterRecyclerViewRssContent
    private val arrayListContent = ArrayList<RecyclerViewContentItemModel>()

    private lateinit var headerDrawer : TextView
    private lateinit var headerPage : TextView
    private lateinit var recyclerProgressBar: ProgressBar
    private lateinit var menuItemRestart: MenuItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Many Rss"

        initializeLayoutComponents()

        setDrawerLayoutComponents()

        setupNavigationRecyclerView()

        setupContentRecyclerView()
    }

    private fun initializeLayoutComponents() {
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        recyclerViewNavigation = findViewById<RecyclerView>(R.id.navigation_recyclerview)
        recyclerViewRssContent = findViewById<RecyclerView>(R.id.page_recyclerview)
        headerDrawer = findViewById(R.id.header_drawer)
        headerPage = findViewById(bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.R.id.header_page)
        recyclerProgressBar = findViewById<ProgressBar>(R.id.progress_bar_loading_feeds)
    }

    private fun setDrawerLayoutComponents() {
        setSupportActionBar(toolbar)

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

    private fun setupNavigationRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        recyclerViewNavigation.setLayoutManager(layoutManager)

        adapterRecyclerViewNavigation = AdapterRecyclerViewNavigation(arrayListNavigation)

        recyclerViewNavigation.adapter = adapterRecyclerViewNavigation
        adapterRecyclerViewNavigation.notifyDataSetChanged()

        headerDrawer.text = "All feeds (${arrayListNavigation.size})"
    }

    private fun setupContentRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        recyclerViewRssContent.setLayoutManager(layoutManager)

        adapterRecyclerViewRssContent = AdapterRecyclerViewRssContent(arrayListContent)

        recyclerViewRssContent.adapter = adapterRecyclerViewRssContent
        adapterRecyclerViewNavigation.notifyDataSetChanged()
    }

    fun navigationItemClicked(view : View){

        arrayListContent.clear()
        adapterRecyclerViewRssContent.notifyDataSetChanged()
        recyclerProgressBar.visibility = View.VISIBLE
        headerPage.text = ""

        val positionItemClicked : Int?
                = recyclerViewNavigation.findContainingViewHolder(view)?.adapterPosition

        positionItemClicked.let {
            val feedsBaseUrl : String = arrayListNavigation.get(positionItemClicked!!).feedsBaseUrl
            val feedsUrlEndPoint : String = arrayListNavigation.get(positionItemClicked!!).feedsUrlEndPoint
            val feedTitle : String = "${arrayListNavigation.get(positionItemClicked).title} \n ${arrayListNavigation.get(positionItemClicked).subTitle}"

            drawerLayout?.closeDrawer(Gravity.START)
            fillArrayListRecyclerViewContent(feedsBaseUrl, feedsUrlEndPoint, feedTitle)
        }
    }

    private fun fillArrayListRecyclerViewContent (feedsBaseUrl : String, feedsUrlEndPoint : String, feedTitle : String){

        val call : Call<RssFeed> = setupRetrofitCall(
            feedsBaseUrl,
            feedsUrlEndPoint
        )

        call.enqueue(object : Callback<RssFeed> {

            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {

                Log.d("RssFeed", "Success")

                val listItems : List<RssItem>? = response.body()?.items

                listItems?.let {
                    for (item in listItems){

                        arrayListContent.add(
                            RecyclerViewContentItemModel(
                                description = item.description,
                                image = item.image,
                                link = item.link,
                                publishDate = item.publishDate,
                                title = item.title
                            )
                        )

                        adapterRecyclerViewRssContent.notifyDataSetChanged()

                        headerPage.text = "$feedTitle (${arrayListContent.size})"

                        recyclerProgressBar.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<RssFeed>, t: Throwable) {
                Log.d("RssFeed", t.message)
                Toast.makeText(this@MainActivity, "No Feeds!", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun openWebView(view : View){

        val positionItemClicked : Int?
                = recyclerViewRssContent.findContainingViewHolder(view)?.adapterPosition

        positionItemClicked.let {
            val feedItemUrl : String? = arrayListContent.get(positionItemClicked!!).link

            val intent : Intent  = Intent(view.context, ItemWebViewActivity::class.java)

            intent.putExtra("feedItemUrl", feedItemUrl)
            view.context.startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(m: Menu): Boolean {
        super.onCreateOptionsMenu(m)
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, m)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val itemId = item.itemId

        when (itemId) {
            R.id.restart -> {
                menuItemRestart = item
                recyclerProgressBar.visibility = View.GONE
                headerPage.text = ""
                resetActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun resetActivity() {
        val intent = intent
        finish()
        startActivity(intent)
    }
}
