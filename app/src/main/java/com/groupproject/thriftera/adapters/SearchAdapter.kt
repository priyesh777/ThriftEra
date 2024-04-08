package com.groupproject.thriftera.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.groupproject.thriftera.data.Product
import com.groupproject.thriftera.databinding.ItemSearchBinding
import com.groupproject.thriftera.helper.getProductPriceAfterDiscount

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchProductsViewHolder>() {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    inner class SearchProductsViewHolder(val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(ivProduct)
                tvProductCartName.text = product.name
                if (product.offerPercentage != null) {
                    val priceAfterOffer = getProductPriceAfterDiscount(
                        product.price,
                        product.offerPercentage)
                    tvProductCartPrice.text = "$${String.format("%.2f", priceAfterOffer)}"
                }else {
                    tvProductCartPrice.text = "$${String.format("%.2f", product.price)}"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchProductsViewHolder {
        return SearchProductsViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    fun submitList(list: List<Product>) {
        differ.submitList(list)
    }


    override fun onBindViewHolder(holder: SearchProductsViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onProductClick?.invoke(product)
        }
    }


    var onProductClick: ((Product) -> Unit)? = null
}
