package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.dataprovider

import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.model.RecyclerViewNavigationModel
import java.util.ArrayList

// no logic but fixed names of rss providers
class NavigationDataProvider{
    companion object {
        fun getArrayListNavigationItems() : ArrayList<RecyclerViewNavigationModel>{
            val arrayListRecyclerViewNavigationItems : ArrayList<RecyclerViewNavigationModel> = ArrayList<RecyclerViewNavigationModel>()

            // https://www.cinemablend.com/rss/topic/reviews/movies
            arrayListRecyclerViewNavigationItems.add(
                RecyclerViewNavigationModel(
                    "cinema",
                    "Cinema",
                    "reviews/movies",
                    "5.00",
                    "https://www.cinemablend.com/",
                    "rss/topic/reviews/movies"
                )
            )

            // https://www.kfz-betrieb.vogel.de/rss/themechannel5452.xml
            arrayListRecyclerViewNavigationItems.add(
                RecyclerViewNavigationModel(
                    "cars",
                    "Car News",
                    "Commercial and sport",
                    "4.5",
                    "https://www.kfz-betrieb.vogel.de/",
                    "rss/themechannel5452.xml"
                )
            )

            // https://www.kfz-betrieb.vogel.de/rss/themechannel5447.xml
            arrayListRecyclerViewNavigationItems.add(
                RecyclerViewNavigationModel(
                    "safety",
                    "Best practices",
                    "Laws insight",
                    "3.7",
                    "https://www.kfz-betrieb.vogel.de/",
                    "rss/themechannel5447.xml"
                )
            )

            // http://rss.sueddeutsche.de/rss/Topthemen
            arrayListRecyclerViewNavigationItems.add(
                RecyclerViewNavigationModel(
                    "students",
                    "Students matters",
                    "studies finance",
                    "5.0",
                    "http://rss.sueddeutsche.de/",
                    "rss/Topthemen"
                )
            )

            return arrayListRecyclerViewNavigationItems
        }

        fun cloneDataProvider() : ArrayList<RecyclerViewNavigationModel>{
            var clonedArrayList : ArrayList<RecyclerViewNavigationModel> = ArrayList<RecyclerViewNavigationModel>()
            for(item in getArrayListNavigationItems()){
                clonedArrayList.add(item)
            }
            return clonedArrayList
        }
    }
}