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
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.dataprovider.NavigationDataProvider
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.model.RecyclerViewContentItemModel
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    private var drawerLayout: DrawerLayout? = null
    private lateinit var toolbar : Toolbar
    private lateinit var drawerToggle : ActionBarDrawerToggle

    private var recyclerViewNavigation: RecyclerView? = null
    private var adapterRecyclerViewNavigation: AdapterRecyclerViewNavigation? = null
    private val arrayListNavigation = NavigationDataProvider.Companion.cloneDataProvider()

    private var recyclerViewRssContent: RecyclerView? = null
    private var adapterRecyclerViewRssContent: AdapterRecyclerViewRssContent? = null
    private lateinit var arrayListRssContent : ArrayList<RecyclerViewContentItemModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeLayoutComponents()

        setDrawerLayoutComponents()

        setupNavigationRecyclerView()

    }





    private fun initializeLayoutComponents() {
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        recyclerViewNavigation = findViewById<RecyclerView>(R.id.navigation_recyclerview)
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
        recyclerViewNavigation!!.setLayoutManager(layoutManager)

        adapterRecyclerViewNavigation =
            AdapterRecyclerViewNavigation(arrayListNavigation)
        recyclerViewNavigation!!.adapter = adapterRecyclerViewNavigation
        adapterRecyclerViewNavigation?.notifyDataSetChanged()


    }



}
