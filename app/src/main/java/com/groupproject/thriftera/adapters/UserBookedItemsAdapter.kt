package com.groupproject.thriftera.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.groupproject.thriftera.data.CartProduct
import com.groupproject.thriftera.databinding.BookedProductItemBinding
import com.groupproject.thriftera.helper.getProductPriceAfterDiscount

class UserBookedItemsAdapter: RecyclerView.Adapter<UserBookedItemsAdapter.CartProductsViewHolder>() {
    //Adapter to list the booked items in the profile section

    inner class CartProductsViewHolder( val binding: BookedProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartProduct: CartProduct) {
            binding.apply {
                val product = cartProduct.product
                Glide.with(itemView).load(product.images[0]).into(imageCartProduct)
                tvProductCartName.text = product.name

                imageCartProductColor.setImageDrawable(ColorDrawable(cartProduct.selectedColor?: Color.TRANSPARENT))
                tvCartProductSize.text = cartProduct.selectedSize?:"".also { imageCartProductSize.setImageDrawable(
                    ColorDrawable(Color.TRANSPARENT)
                ) }
                if (product.offerPercentage != null){
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

    private val diffCallback = object : DiffUtil.ItemCallback<CartProduct>() {
        override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductsViewHolder {
        return CartProductsViewHolder(
            BookedProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CartProductsViewHolder, position: Int) {
        val cartProduct = differ.currentList[position]
        holder.bind(cartProduct)

        holder.itemView.setOnClickListener {
            onProductClick?.invoke(cartProduct)
        }
//
//        holder.binding.imagePlus.setOnClickListener {
//            onPlusClick?.invoke(cartProduct)
//        }
//
//        holder.binding.imageMinus.setOnClickListener {
//            onMinusClick?.invoke(cartProduct)
//        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onProductClick: ((CartProduct) -> Unit)? = null
    var onPlusClick: ((CartProduct) -> Unit)? = null
    var onMinusClick: ((CartProduct) -> Unit)? = null



}