package bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.navigationdata

import bernardo.bernardinhio.rssfeedsonerecyclerviewretrofit.model.NavigationModel
import java.util.ArrayList


class NavigationDataProvider{
    companion object {
        fun getArrayListNavigationItems() : ArrayList<NavigationModel>{
            val arrayListNavigationItems : ArrayList<NavigationModel> = ArrayList<NavigationModel>()

            arrayListNavigationItems.add(
                NavigationModel(
                    "image1",
                    "Daily Mail",
                    "Latest stories",
                    "5.00"
                )
            )
            arrayListNavigationItems.add(
                NavigationModel(
                    "image2",
                    "BBC Sports",
                    "World edition",
                    "1.07"
                )
            )

            return arrayListNavigationItems
        }

        fun cloneDataProvider() : ArrayList<NavigationModel>{
            var clonedArrayList : ArrayList<NavigationModel> = ArrayList<NavigationModel>()
            for(item in getArrayListNavigationItems()){
                clonedArrayList.add(item)
            }
            return clonedArrayList
        }
    }
}