package com.example.thriftera.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thriftera.data.Product
import com.example.thriftera.databinding.ItemSearchBinding
import com.example.thriftera.helper.getProductPrice

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchProductsViewHolder>() {

    private var allProducts: List<Product> = listOf()

    inner class SearchProductsViewHolder(val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(ivProduct)
                tvProductCartName.text = product.name
                val priceAfterPercentage = product.offerPercentage.getProductPrice(product.price)
                tvProductCartPrice.text = "$${String.format("%.2f", priceAfterPercentage)}"
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return filteredProducts[position].id.hashCode().toLong()
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchProductsViewHolder {
        return SearchProductsViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun submitList(products: List<Product>) {
        allProducts = products
        filteredProducts = allProducts
    }


    private var filteredProducts: List<Product> = listOf()
        set(value) {
            field = value
            differ.submitList(value)
        }

    fun filter(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) allProducts else allProducts.filter {
            it.name.lowercase().contains(
                query,
                ignoreCase = true
            )
        }
        differ.submitList(filteredList.toList())
    }

    override fun onBindViewHolder(holder: SearchProductsViewHolder, position: Int) {
        val product = filteredProducts[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onProductClick?.invoke(product)
        }
    }

    override fun getItemCount() = filteredProducts.size

    var onProductClick: ((Product) -> Unit)? = null
}
