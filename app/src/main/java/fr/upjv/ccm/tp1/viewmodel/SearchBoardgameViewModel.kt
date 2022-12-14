package fr.upjv.ccm.tp1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.upjv.ccm.tp1.architecture.RetrofitBuilder
import fr.upjv.ccm.tp1.model.Boardgame
import fr.upjv.ccm.tp1.model.MyObjectForRecyclerView
import fr.upjv.ccm.tp1.repository.AndroidVersionRepository
import fr.upjv.ccm.tp1.repository.SearchBoardgameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchBoardgameViewModel : ViewModel() {

    private val androidVersionRepository: AndroidVersionRepository by lazy { AndroidVersionRepository() }

    fun fetchData(text: String): List<Boardgame> {
        return mutableListOf<Boardgame>(
            Boardgame(
                name = "Ankh",
                price = 79.99,
                desc = "Un bon jeu",
                category = "advanced",
                image = "https://www.myludo.fr/img/jeux/1658807383/300/by/50933.png"
            )
        )
    }

    fun insertBoardGame(boardgame: Boardgame) {
        viewModelScope.launch(Dispatchers.IO) {
            androidVersionRepository.insertBoardgame(
                boardgame
            )
        }
    }

}