package fr.upjv.ccm.tp1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

sealed class MyObjectForRecyclerView()

data class Boardgame(
    @Expose
    @SerializedName("name")
    val name : String,

    @Expose
    @SerializedName("price")
    val price : Double,

    @Expose
    @SerializedName("description")
    val desc: String,

    @Expose
    @SerializedName("type")
    val category: String,

    @Expose
    @SerializedName("thumb_url")
    val image: String
):MyObjectForRecyclerView()

@Entity(tableName = "boardgame_object_table")
data class LocalDataSourceSample(
    val name : String,
    val price : Double,
    val desc: String,
    val category: String,
    val image: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

data class Category(
    val name: String,
) : MyObjectForRecyclerView()

data class Footer(
    val numberOfBoargames: String,
) : MyObjectForRecyclerView()