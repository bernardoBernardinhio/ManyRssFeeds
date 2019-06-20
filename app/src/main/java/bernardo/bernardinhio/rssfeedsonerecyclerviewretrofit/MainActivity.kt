package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.navigationdata.NavigationDataProvider


// https://feeder.co/knowledge-base/rss-content/rss-lists-popular-and-useful-rss-feeds/

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

        adapterRecyclerViewNavigation = AdapterRecyclerViewNavigation(arrayListNavigation)
        recyclerViewNavigation!!.adapter = adapterRecyclerViewNavigation
        adapterRecyclerViewNavigation?.notifyDataSetChanged()


        drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name)

        drawerLayout!!.addDrawerListener(drawerToggle)

        drawerToggle.syncState()

    }



}
