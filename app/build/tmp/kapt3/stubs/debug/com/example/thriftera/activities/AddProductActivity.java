package com.example.thriftera.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\u0018\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001a2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0012\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\u0012\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\u0010\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020*H\u0016J\u0012\u0010+\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010,\u001a\u00020%H\u0016J\u001c\u0010-\u001a\u00020 2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020 0/H\u0002J\b\u00100\u001a\u00020 H\u0002J\b\u00101\u001a\u00020 H\u0002J\b\u00102\u001a\u00020 H\u0002J\b\u00103\u001a\u00020%H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00064"}, d2 = {"Lcom/example/thriftera/activities/AddProductActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/thriftera/databinding/ActivityAddProductBinding;", "getBinding", "()Lcom/example/thriftera/databinding/ActivityAddProductBinding;", "binding$delegate", "Lkotlin/Lazy;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "getFirestore", "()Lcom/google/firebase/firestore/FirebaseFirestore;", "selectedColors", "", "", "getSelectedColors", "()Ljava/util/List;", "selectedImages", "Landroid/net/Uri;", "getSelectedImages", "setSelectedImages", "(Ljava/util/List;)V", "storage", "Lcom/google/firebase/storage/FirebaseStorage;", "getImagesByteArrays", "", "", "getSizesList", "", "sizes", "hideLoading", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "onSupportNavigateUp", "saveProducts", "state", "Lkotlin/Function1;", "showLoading", "updateColors", "updateImages", "validateInformation", "app_debug"})
public final class AddProductActivity extends androidx.appcompat.app.AppCompatActivity {
    private final kotlin.Lazy binding$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.Integer> selectedColors = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<android.net.Uri> selectedImages;
    @org.jetbrains.annotations.NotNull
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    private final com.google.firebase.storage.FirebaseStorage storage = null;
    
    public AddProductActivity() {
        super();
    }
    
    private final com.example.thriftera.databinding.ActivityAddProductBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.Integer> getSelectedColors() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<android.net.Uri> getSelectedImages() {
        return null;
    }
    
    public final void setSelectedImages(@org.jetbrains.annotations.NotNull
    java.util.List<android.net.Uri> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.google.firebase.firestore.FirebaseFirestore getFirestore() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    @java.lang.Override
    public boolean onPrepareOptionsMenu(@org.jetbrains.annotations.Nullable
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.Nullable
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull
    android.view.MenuItem item) {
        return false;
    }
    
    private final boolean validateInformation() {
        return false;
    }
    
    private final void saveProducts(kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> state) {
    }
    
    private final void hideLoading() {
    }
    
    private final void showLoading() {
    }
    
    private final java.util.List<byte[]> getImagesByteArrays() {
        return null;
    }
    
    private final java.util.List<java.lang.String> getSizesList(java.lang.String sizes) {
        return null;
    }
    
    private final void updateColors() {
    }
    
    private final void updateImages() {
    }
}