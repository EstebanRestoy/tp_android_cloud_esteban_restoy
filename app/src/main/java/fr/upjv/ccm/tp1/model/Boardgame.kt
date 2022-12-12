package fr.upjv.ccm.tp1.model
sealed class MyObjectForRecyclerView()

data class Boardgame(
    val name : String,
    val price : Double,
    val desc: String,
    val category: String
):MyObjectForRecyclerView()

data class Category(
    val name: String,
) : MyObjectForRecyclerView()

data class Footer(
    val numberOfBoargames: String,
) : MyObjectForRecyclerView()