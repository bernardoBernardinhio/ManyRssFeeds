In this App I show the power or Retrofit in easily parsing responses of Data from Apis such as those of Rss feeds, also I show how beautiful and powerful RecyclerViews are that just respond to the change of data provider binded to their adapters.

I created a DrawerLayout that allows us to reset one container located in one activity and so to populate the same RecyclerView with the different sources of the Rss, each Rss sources has its Items and all of them have in common some properties that I matched in a model class used also in the fragment (CardView)

I made a nice architecture by regrouping the Http requests in one Interface and providing a retrofit instance used across the project. 
DrawerLayout navigation different Rss sources

I made good reactive UI with titles and progress bars during the Api call or with restart project and navigating to other Rss feed sources


DrawerLayout navigation different Rss sources

![DrawerLayout navigation different Rss sources](https://user-images.githubusercontent.com/34580921/59990429-64d27480-9643-11e9-8de6-9056c54e2135.png)


Loading Rss with Retrofit Library

![Loading Rss with Retrofit Library](https://user-images.githubusercontent.com/34580921/59990456-87fd2400-9643-11e9-974d-cf6dc3109bee.png)


RecyclerView CardView on response from Retrofit

![RecyclerView CardView on response from Retrofit](https://user-images.githubusercontent.com/34580921/59990509-af53f100-9643-11e9-8d01-0a0e746997b9.png)

WebView to open each Rss Item

![WebView to open each Rss Item](https://user-images.githubusercontent.com/34580921/59990536-c85ca200-9643-11e9-8fc2-f355fa712b6b.png)
