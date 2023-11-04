package gsbkomar.gsbdelivery.screens.tape.adapters.product

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gsbkomar.data.dtos.ResultsDto
import gsbkomar.gsbdelivery.databinding.CardProductItemBinding
import gsbkomar.gsbdelivery.screens.tape.MainTapeFragment
import javax.inject.Inject
import kotlin.random.Random

class CardProductItemListAdapter @Inject constructor(
    private val mainTapeFragment: MainTapeFragment,
    private val onClick: (item: String) -> Unit
) : ListAdapter<ResultsDto, CardProductListViewHolder>(DifUtilCallBack()) {

    override fun onBindViewHolder(holder: CardProductListViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            Glide.with(mainTapeFragment)
                .load(item.image)
                .into(ivProductPhoto)
            tvProductName.text = item.name
            // Ингредиенты пришлось заменить на описание блюда,
            tvProductIngredients.text = item.content
            // Захардкордил потому что апишка не предоставляет цены на еду.
            btnPrice.text = "от " + Random.nextInt(100, 1000) + " р"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardProductListViewHolder {
        return CardProductListViewHolder(
            CardProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

class DifUtilCallBack : DiffUtil.ItemCallback<ResultsDto>() {
    override fun areItemsTheSame(
        oldItem: ResultsDto,
        newItem: ResultsDto
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: ResultsDto,
        newItem: ResultsDto
    ): Boolean {
        return oldItem == newItem
    }
}

class CardProductListViewHolder(val binding: CardProductItemBinding) : RecyclerView.ViewHolder(binding.root)
