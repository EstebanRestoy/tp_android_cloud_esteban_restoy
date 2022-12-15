package fr.upjv.ccm.tp1.view

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.upjv.ccm.tp1.R
import fr.upjv.ccm.tp1.databinding.ItemCustomRecyclerBinding
import fr.upjv.ccm.tp1.databinding.ItemCustomRecyclerHeaderBinding
import fr.upjv.ccm.tp1.model.MyObjectForRecyclerView
import fr.upjv.ccm.tp1.model.Category
import fr.upjv.ccm.tp1.model.Boardgame
import fr.upjv.ccm.tp1.model.Footer
import java.lang.RuntimeException

private val diffItemUtils = object : DiffUtil.ItemCallback<MyObjectForRecyclerView>() {

    override fun areItemsTheSame(oldItem: MyObjectForRecyclerView, newItem: MyObjectForRecyclerView): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MyObjectForRecyclerView, newItem: MyObjectForRecyclerView): Boolean {
        return oldItem == newItem
    }
}

class AndroidVersionAdapter(
    private val onItemClick: (quoteUi: Boardgame, view: View) -> Unit,
) : ListAdapter<MyObjectForRecyclerView, RecyclerView.ViewHolder>(diffItemUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            MyItemType.ROW.type -> {
                AndroidVersionViewHolder(
                    ItemCustomRecyclerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onItemClick
                )
            }
            MyItemType.HEADER.type -> {
                AndroidVersionHeaderViewHolder(
                    ItemCustomRecyclerHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            MyItemType.FOOTER.type -> {
                AndroidVersionFooterViewHolder(
                    ItemCustomRecyclerHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw RuntimeException("Wrong view type received $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            MyItemType.ROW.type -> (holder as AndroidVersionViewHolder).bind(getItem(position) as Boardgame)
            MyItemType.HEADER.type -> (holder as AndroidVersionHeaderViewHolder).bind(getItem(position) as Category)
            MyItemType.FOOTER.type -> (holder as AndroidVersionFooterViewHolder).bind(getItem(position) as Footer)

            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Boardgame -> MyItemType.ROW.type
            is Category -> MyItemType.HEADER.type
            is Footer -> MyItemType.FOOTER.type
        }
    }
}

class AndroidVersionViewHolder(
    private val binding: ItemCustomRecyclerBinding,
    onItemClick: (objectDataSample: Boardgame, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ui: Boardgame


    init {
        binding.root.setOnLongClickListener() {
            onItemClick(ui, itemView)
            true
        }
    }

    fun bind(boardgame: Boardgame) {
        ui = boardgame
        binding.itemRecyclerViewName.text = boardgame.name
        binding.itemRecyclerViewPrice.text = "${boardgame.price}" + "€"
        binding.itemRecyclerViewDescription.text = Html.fromHtml(boardgame.desc)
        Glide.with(itemView.context)
            .load(boardgame.image)
            .placeholder(R.drawable.sea)
            .into(binding.imageView)
    }
}

class AndroidVersionHeaderViewHolder(
    private val binding: ItemCustomRecyclerHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category) {
        binding.itemRecyclerViewHeader.text = category.name
    }
}

class AndroidVersionFooterViewHolder(
    private val binding: ItemCustomRecyclerHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(footer: Footer) {
        binding.itemRecyclerViewHeader.text = "${footer.numberOfBoargames} Boardgames"
    }
}

enum class MyItemType(val type: Int) {
    ROW(0),
    HEADER(1),
    FOOTER(2)
}

