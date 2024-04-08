package com.groupproject.thriftera.adapters

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.groupproject.thriftera.data.Product
import com.groupproject.thriftera.databinding.ProductRvItemBinding
import com.groupproject.thriftera.helper.getProductPrice
import com.groupproject.thriftera.helper.getProductPriceAfterDiscount

class BestProductsAdapter : RecyclerView.Adapter<BestProductsAdapter.BestProductsViewHolder>() {
// Adapter to list the best-products scroll element using the recycler view

    inner class BestProductsViewHolder(private val binding: ProductRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                //if there is an offer
                Glide.with(imgProduct).load(product.images[0]).into(imgProduct)
                tvName.text = product.name
                if (product.offerPercentage != null){
                    val priceAfterOffer = getProductPriceAfterDiscount(
                        product.price,
                        product.offerPercentage)
                    tvPrice.text = "$${product.price}"
                    tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                    tvNewPrice.text = "$${String.format("%.2f", priceAfterOffer)}"
                    tvNewPrice.visibility = View.VISIBLE
                }else {
                    tvPrice.text = "$${product.price}"
                }
            }

        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductsViewHolder {
        return BestProductsViewHolder(
            ProductRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: BestProductsViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onClick?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onClick: ((Product) -> Unit)? = null

}