package gsbkomar.gsbdelivery.screens.tape.adapters.category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gsbkomar.gsbdelivery.R
import gsbkomar.gsbdelivery.databinding.CategoryItemBinding
import gsbkomar.gsbdelivery.screens.tape.models.category.Category
import javax.inject.Inject

class CategoryItemListAdapter @Inject constructor(
    private val context: Context,
    private val onClick: (item: Int) -> Unit
) : ListAdapter<Category, CategoryListViewHolder>(DifUtilCallBack()) {


    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            tvCategory.text = item.name
            if (item.isSelected) {
                tvCategory.setTextColor(context.resources.getColor(R.color.red))
                tvCategory.textSize = 18.0f
                cvContainerCategory.setCardBackgroundColor(context.resources.getColor(R.color.red_background))
            } else {
                tvCategory.setTextColor(context.resources.getColor(R.color.gray))
                tvCategory.textSize = 15.0f
                cvContainerCategory.setCardBackgroundColor(context.resources.getColor(R.color.gray_background))
            }
            root.setOnClickListener {
                onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        return CategoryListViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

class DifUtilCallBack : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(
        oldItem: Category,
        newItem: Category
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: Category,
        newItem: Category
    ): Boolean {
        return oldItem == newItem
    }
}

class CategoryListViewHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)