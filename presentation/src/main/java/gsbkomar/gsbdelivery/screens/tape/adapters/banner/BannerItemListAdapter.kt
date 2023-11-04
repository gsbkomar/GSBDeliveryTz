package gsbkomar.gsbdelivery.screens.tape.adapters.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gsbkomar.gsbdelivery.databinding.BannerSalesItemBinding
import gsbkomar.gsbdelivery.screens.tape.models.banner.Banner
import javax.inject.Inject

class BannerItemListAdapter @Inject constructor(
    private val onClick: (item: String) -> Unit
) : ListAdapter<Banner, BannerListViewHolder>(DifUtilCallBack()) {

    override fun onBindViewHolder(holder: BannerListViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.ivBanner.setImageDrawable(item.src)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerListViewHolder {
        return BannerListViewHolder(
            BannerSalesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

class DifUtilCallBack : DiffUtil.ItemCallback<Banner>() {
    override fun areItemsTheSame(
        oldItem: Banner,
        newItem: Banner
    ): Boolean {
        return oldItem.src == newItem.src
    }

    override fun areContentsTheSame(
        oldItem: Banner,
        newItem: Banner
    ): Boolean {
        return oldItem == newItem
    }
}

class BannerListViewHolder(val binding: BannerSalesItemBinding) : RecyclerView.ViewHolder(binding.root)