package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.dataprovider

import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.model.RecyclerViewNavigationModel
import java.util.ArrayList

// no logic but fixed names of rss providers
class NavigationDataProvider{
    companion object {
        fun getArrayListNavigationItems() : ArrayList<RecyclerViewNavigationModel>{
            val arrayListRecyclerViewNavigationItems : ArrayList<RecyclerViewNavigationModel> = ArrayList<RecyclerViewNavigationModel>()

            arrayListRecyclerViewNavigationItems.add(
                RecyclerViewNavigationModel(
                    "image1",
                    "Daily Mail",
                    "Latest stories",
                    "5.00"
                )
            )
            arrayListRecyclerViewNavigationItems.add(
                RecyclerViewNavigationModel(
                    "image2",
                    "BBC Sports",
                    "World edition",
                    "1.07"
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