package com.example.thriftera.viewmodel;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/thriftera/viewmodel/SearchViewModel;", "Landroidx/lifecycle/ViewModel;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/google/firebase/firestore/FirebaseFirestore;)V", "TAG", "", "_allProducts", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/thriftera/util/Resource;", "", "Lcom/example/thriftera/data/Product;", "allProducts", "Lkotlinx/coroutines/flow/StateFlow;", "getAllProducts", "()Lkotlinx/coroutines/flow/StateFlow;", "fetchAllProducts", "", "app_debug"})
public final class SearchViewModel extends androidx.lifecycle.ViewModel {
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.thriftera.util.Resource<java.util.List<com.example.thriftera.data.Product>>> _allProducts = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.thriftera.util.Resource<java.util.List<com.example.thriftera.data.Product>>> allProducts = null;
    private final java.lang.String TAG = "SearchVM";
    
    @javax.inject.Inject
    public SearchViewModel(@org.jetbrains.annotations.NotNull
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.thriftera.util.Resource<java.util.List<com.example.thriftera.data.Product>>> getAllProducts() {
        return null;
    }
    
    private final void fetchAllProducts() {
    }
}