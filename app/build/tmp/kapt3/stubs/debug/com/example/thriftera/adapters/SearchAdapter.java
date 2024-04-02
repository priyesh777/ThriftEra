package com.example.thriftera.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001)B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001dH\u0016J\u001c\u0010!\u001a\u00020\u00142\n\u0010\"\u001a\u00060\u0002R\u00020\u00002\u0006\u0010 \u001a\u00020\u001dH\u0016J\u001c\u0010#\u001a\u00060\u0002R\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001dH\u0016J\u0014\u0010\'\u001a\u00020\u00142\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\t\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\u00060\u00060\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR*\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005@BX\u0082\u000e\u00a2\u0006\b\n\u0000\"\u0004\b\u0010\u0010\u0011R(\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u0006*"}, d2 = {"Lcom/example/thriftera/adapters/SearchAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/thriftera/adapters/SearchAdapter$SearchProductsViewHolder;", "()V", "allProducts", "", "Lcom/example/thriftera/data/Product;", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "differ", "Landroidx/recyclerview/widget/AsyncListDiffer;", "kotlin.jvm.PlatformType", "getDiffer", "()Landroidx/recyclerview/widget/AsyncListDiffer;", "value", "filteredProducts", "setFilteredProducts", "(Ljava/util/List;)V", "onProductClick", "Lkotlin/Function1;", "", "getOnProductClick", "()Lkotlin/jvm/functions/Function1;", "setOnProductClick", "(Lkotlin/jvm/functions/Function1;)V", "filter", "query", "", "getItemCount", "", "getItemId", "", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitList", "products", "SearchProductsViewHolder", "app_debug"})
public final class SearchAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.thriftera.adapters.SearchAdapter.SearchProductsViewHolder> {
    private java.util.List<com.example.thriftera.data.Product> allProducts;
    private final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.thriftera.data.Product> diffCallback = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.recyclerview.widget.AsyncListDiffer<com.example.thriftera.data.Product> differ = null;
    private java.util.List<com.example.thriftera.data.Product> filteredProducts;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super com.example.thriftera.data.Product, kotlin.Unit> onProductClick;
    
    public SearchAdapter() {
        super();
    }
    
    @java.lang.Override
    public long getItemId(int position) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.recyclerview.widget.AsyncListDiffer<com.example.thriftera.data.Product> getDiffer() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.example.thriftera.adapters.SearchAdapter.SearchProductsViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    public final void submitList(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.thriftera.data.Product> products) {
    }
    
    private final void setFilteredProducts(java.util.List<com.example.thriftera.data.Product> value) {
    }
    
    public final void filter(@org.jetbrains.annotations.Nullable
    java.lang.String query) {
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.thriftera.adapters.SearchAdapter.SearchProductsViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function1<com.example.thriftera.data.Product, kotlin.Unit> getOnProductClick() {
        return null;
    }
    
    public final void setOnProductClick(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super com.example.thriftera.data.Product, kotlin.Unit> p0) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/example/thriftera/adapters/SearchAdapter$SearchProductsViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/thriftera/databinding/ItemSearchBinding;", "(Lcom/example/thriftera/adapters/SearchAdapter;Lcom/example/thriftera/databinding/ItemSearchBinding;)V", "getBinding", "()Lcom/example/thriftera/databinding/ItemSearchBinding;", "bind", "", "product", "Lcom/example/thriftera/data/Product;", "app_debug"})
    public final class SearchProductsViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.thriftera.databinding.ItemSearchBinding binding = null;
        
        public SearchProductsViewHolder(@org.jetbrains.annotations.NotNull
        com.example.thriftera.databinding.ItemSearchBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.thriftera.databinding.ItemSearchBinding getBinding() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.example.thriftera.data.Product product) {
        }
    }
}