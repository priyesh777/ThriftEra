package com.example.thriftera.viewmodel;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0006J\b\u0010\u0017\u001a\u00020\u0013H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/thriftera/viewmodel/SearchViewModel;", "Landroidx/lifecycle/ViewModel;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/google/firebase/firestore/FirebaseFirestore;)V", "TAG", "", "_allProducts", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/thriftera/util/Resource;", "", "Lcom/example/thriftera/data/Product;", "_filteredProducts", "_searchQuery", "filteredProducts", "Lkotlinx/coroutines/flow/StateFlow;", "getFilteredProducts", "()Lkotlinx/coroutines/flow/StateFlow;", "fetchAllProducts", "", "filterProducts", "query", "setSearchQuery", "setupSearch", "app_debug"})
public final class SearchViewModel extends androidx.lifecycle.ViewModel {
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.thriftera.util.Resource<java.util.List<com.example.thriftera.data.Product>>> _allProducts = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.thriftera.data.Product>> _filteredProducts = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.thriftera.data.Product>> filteredProducts = null;
    private final java.lang.String TAG = "SearchVM";
    
    @javax.inject.Inject
    public SearchViewModel(@org.jetbrains.annotations.NotNull
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.thriftera.data.Product>> getFilteredProducts() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class, kotlinx.coroutines.FlowPreview.class})
    private final void setupSearch() {
    }
    
    public final void setSearchQuery(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    public final void filterProducts(@org.jetbrains.annotations.Nullable
    java.lang.String query) {
    }
    
    private final void fetchAllProducts() {
    }
}