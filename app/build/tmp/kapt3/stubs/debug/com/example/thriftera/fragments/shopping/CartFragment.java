package com.example.thriftera.fragments.shopping;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010 \u001a\u00020\u0014H\u0002J\b\u0010!\u001a\u00020\u0014H\u0002J\b\u0010\"\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006#"}, d2 = {"Lcom/example/thriftera/fragments/shopping/CartFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/example/thriftera/databinding/FragmentCartBinding;", "cartAdapter", "Lcom/example/thriftera/adapters/CartProductAdapter;", "getCartAdapter", "()Lcom/example/thriftera/adapters/CartProductAdapter;", "cartAdapter$delegate", "Lkotlin/Lazy;", "productsInCart", "Ljava/util/ArrayList;", "Lcom/example/thriftera/data/CartProduct;", "viewModel", "Lcom/example/thriftera/viewmodel/CartViewModel;", "getViewModel", "()Lcom/example/thriftera/viewmodel/CartViewModel;", "viewModel$delegate", "hideEmptyCart", "", "hideOtherViews", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "setupCartRv", "showEmptyCart", "showOtherViews", "app_debug"})
public final class CartFragment extends androidx.fragment.app.Fragment {
    private com.example.thriftera.databinding.FragmentCartBinding binding;
    private final kotlin.Lazy cartAdapter$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private java.util.ArrayList<com.example.thriftera.data.CartProduct> productsInCart;
    
    public CartFragment() {
        super();
    }
    
    private final com.example.thriftera.adapters.CartProductAdapter getCartAdapter() {
        return null;
    }
    
    private final com.example.thriftera.viewmodel.CartViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showOtherViews() {
    }
    
    private final void hideOtherViews() {
    }
    
    private final void hideEmptyCart() {
    }
    
    private final void showEmptyCart() {
    }
    
    private final void setupCartRv() {
    }
}