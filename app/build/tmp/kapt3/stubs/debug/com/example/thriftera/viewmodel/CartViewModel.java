package com.example.thriftera.viewmodel;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0014\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0016\u0010&\u001a\u00020\u001e2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0016\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020+J\u0010\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020$H\u0002J\u000e\u00100\u001a\u00020$2\u0006\u0010)\u001a\u00020\rJ\b\u0010\u0016\u001a\u00020$H\u0002J\u0010\u00101\u001a\u00020$2\u0006\u0010-\u001a\u00020.H\u0002J\u000e\u00102\u001a\u00020$2\u0006\u00103\u001a\u00020\u0011R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R#\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017\u00a8\u00064"}, d2 = {"Lcom/example/thriftera/viewmodel/CartViewModel;", "Landroidx/lifecycle/ViewModel;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "firebaseCommon", "Lcom/example/thriftera/firebase/FirebaseCommon;", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/auth/FirebaseAuth;Lcom/example/thriftera/firebase/FirebaseCommon;)V", "_cartProducts", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/thriftera/util/Resource;", "", "Lcom/example/thriftera/data/CartProduct;", "_deleteDialog", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "_successDialog", "", "cartProductDocuments", "Lcom/google/firebase/firestore/DocumentSnapshot;", "cartProducts", "Lkotlinx/coroutines/flow/StateFlow;", "getCartProducts", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteDialog", "Lkotlinx/coroutines/flow/SharedFlow;", "getDeleteDialog", "()Lkotlinx/coroutines/flow/SharedFlow;", "productsPrice", "Lkotlinx/coroutines/flow/Flow;", "", "getProductsPrice", "()Lkotlinx/coroutines/flow/Flow;", "successDialog", "getSuccessDialog", "bookItems", "", "products", "calculatePrice", "data", "changeQuantity", "cartProduct", "quantityChanging", "Lcom/example/thriftera/firebase/FirebaseCommon$QuantityChanging;", "decreaseQuantity", "documentId", "", "deleteAllProductsInCart", "deleteCartProduct", "increaseQuantity", "updateSuccessDialog", "status", "app_debug"})
public final class CartViewModel extends androidx.lifecycle.ViewModel {
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    private final com.example.thriftera.firebase.FirebaseCommon firebaseCommon = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.thriftera.util.Resource<java.util.List<com.example.thriftera.data.CartProduct>>> _cartProducts = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.thriftera.util.Resource<java.util.List<com.example.thriftera.data.CartProduct>>> cartProducts = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.lang.Float> productsPrice = null;
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.example.thriftera.data.CartProduct> _deleteDialog = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.SharedFlow<com.example.thriftera.data.CartProduct> deleteDialog = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _successDialog = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> successDialog = null;
    private java.util.List<? extends com.google.firebase.firestore.DocumentSnapshot> cartProductDocuments;
    
    @javax.inject.Inject
    public CartViewModel(@org.jetbrains.annotations.NotNull
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull
    com.google.firebase.auth.FirebaseAuth auth, @org.jetbrains.annotations.NotNull
    com.example.thriftera.firebase.FirebaseCommon firebaseCommon) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.thriftera.util.Resource<java.util.List<com.example.thriftera.data.CartProduct>>> getCartProducts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.lang.Float> getProductsPrice() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.SharedFlow<com.example.thriftera.data.CartProduct> getDeleteDialog() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getSuccessDialog() {
        return null;
    }
    
    public final void updateSuccessDialog(boolean status) {
    }
    
    public final void deleteCartProduct(@org.jetbrains.annotations.NotNull
    com.example.thriftera.data.CartProduct cartProduct) {
    }
    
    private final float calculatePrice(java.util.List<com.example.thriftera.data.CartProduct> data) {
        return 0.0F;
    }
    
    private final void getCartProducts() {
    }
    
    public final void bookItems(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.thriftera.data.CartProduct> products) {
    }
    
    private final void deleteAllProductsInCart() {
    }
    
    public final void changeQuantity(@org.jetbrains.annotations.NotNull
    com.example.thriftera.data.CartProduct cartProduct, @org.jetbrains.annotations.NotNull
    com.example.thriftera.firebase.FirebaseCommon.QuantityChanging quantityChanging) {
    }
    
    private final void decreaseQuantity(java.lang.String documentId) {
    }
    
    private final void increaseQuantity(java.lang.String documentId) {
    }
}