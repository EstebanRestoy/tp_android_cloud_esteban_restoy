package fr.upjv.ccm.tp1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.upjv.ccm.tp1.architecture.RetrofitBuilder
import fr.upjv.ccm.tp1.model.Boardgame
import fr.upjv.ccm.tp1.model.MyObjectForRecyclerView
import fr.upjv.ccm.tp1.repository.AndroidVersionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URI

class SearchBoardgameViewModel : ViewModel() {

    private val androidVersionRepository: AndroidVersionRepository by lazy { AndroidVersionRepository() }
    private val _searchBoardgameList = MutableLiveData<List<MyObjectForRecyclerView>>()
    val searchBoardgameList: LiveData<List<MyObjectForRecyclerView>> get() = _searchBoardgameList


    fun fetchData(text: String) {
        viewModelScope.launch(Dispatchers.IO){
            //appel API

            val emptyBoardGameList: MutableList<Boardgame> = arrayListOf()


            RetrofitBuilder.getBoardgameQuote().getBoardgamesByName(name=text).forEach {
                emptyBoardGameList.add(it)
            }

            _searchBoardgameList.postValue(emptyBoardGameList)

        }
    }


    fun insertBoardGame(boardgame: Boardgame) {
        viewModelScope.launch(Dispatchers.IO) {
            androidVersionRepository.insertBoardgame(
                boardgame
            )
        }
    }


}