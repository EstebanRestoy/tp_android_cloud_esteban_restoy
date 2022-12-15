package fr.upjv.ccm.tp1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fr.upjv.ccm.tp1.architecture.CustomApplication
import fr.upjv.ccm.tp1.model.LocalDataSourceSample
import fr.upjv.ccm.tp1.model.Boardgame

class AndroidVersionRepository {

    private val mBoardGameDatabase =
        CustomApplication.instance.mBoardGameDatabase.BoardgameDao()

    fun selectAllBoardgames(): LiveData<List<Boardgame>> {
        return mBoardGameDatabase.selectAll().map { list ->
            list.toObjectDataSample()
        }
    }

    fun insertBoardgame(objectDataSample: Boardgame) {
        mBoardGameDatabase.insert(objectDataSample.toRoomObject())
    }

    fun deleteOneBoardgame(name: String) {
        mBoardGameDatabase.deleteOne(name)
    }

    fun deleteAllBoardgame() {
        mBoardGameDatabase.deleteAll()
    }
}

private fun Boardgame.toRoomObject(): LocalDataSourceSample {
    return LocalDataSourceSample(
        name=name,
        price=price,
        desc=desc,
        category=category,
        image=image
    )
}

private fun List<LocalDataSourceSample>.toObjectDataSample(): List<Boardgame> {
    return map { eachItem ->
        Boardgame(
            name=eachItem.name,
            price=eachItem.price,
            desc=eachItem.desc,
            category=eachItem.category,
            image=eachItem.image
        )
    }
}