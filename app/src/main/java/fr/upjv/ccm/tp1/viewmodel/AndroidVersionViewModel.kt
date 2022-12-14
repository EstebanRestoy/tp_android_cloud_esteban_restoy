package fr.upjv.ccm.tp1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import fr.upjv.ccm.tp1.model.MyObjectForRecyclerView
import fr.upjv.ccm.tp1.model.Category
import fr.upjv.ccm.tp1.model.Boardgame
import fr.upjv.ccm.tp1.repository.AndroidVersionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AndroidVersionViewModel : ViewModel() {

    private val androidVersionRepository: AndroidVersionRepository by lazy { AndroidVersionRepository() }
    val androidVersionList: LiveData<List<MyObjectForRecyclerView>> = androidVersionRepository.selectAllBoardgames().map { list ->
        list.toMyObjectForRecyclerView()
    }


    fun insertBoardgame(name: String, price: Double, desc: String, category: String, image: String) {
        viewModelScope.launch(Dispatchers.IO) {
            androidVersionRepository.insertBoardgame(
                Boardgame(name, price, desc,category,image)
            )
        }
    }

    fun deleteAllBoardgame() {
        viewModelScope.launch(Dispatchers.IO) {
            androidVersionRepository.deleteAllBoardgame()
        }
    }
}

private fun List<Boardgame>.toMyObjectForRecyclerView(): List<MyObjectForRecyclerView> {
    val result = mutableListOf<MyObjectForRecyclerView>()

    groupBy {
        it.category
    }.forEach { (categoryName, boargames) ->
        result.add(Category(name=categoryName))
        result.addAll(boargames)
        //result.add(Footer("Total Cost : " + boargames.sumOf { it.price }.toString() ))
    }
    return result
}
