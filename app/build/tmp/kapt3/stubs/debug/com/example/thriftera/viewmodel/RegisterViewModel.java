package com.example.thriftera.viewmodel;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aJ!\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\nH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006 "}, d2 = {"Lcom/example/thriftera/viewmodel/RegisterViewModel;", "Landroidx/lifecycle/ViewModel;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "_register", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/thriftera/util/Resource;", "Lcom/example/thriftera/data/User;", "_validation", "Lkotlinx/coroutines/channels/Channel;", "Lcom/example/thriftera/util/RegisterFieldsState;", "register", "Lkotlinx/coroutines/flow/StateFlow;", "getRegister", "()Lkotlinx/coroutines/flow/StateFlow;", "validation", "Lkotlinx/coroutines/flow/Flow;", "getValidation", "()Lkotlinx/coroutines/flow/Flow;", "checkValidation", "", "user", "password", "", "createAccountWithEmailAndPassword", "", "saveUserInfo", "userUid", "(Ljava/lang/String;Lcom/example/thriftera/data/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class RegisterViewModel extends androidx.lifecycle.ViewModel {
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.thriftera.util.Resource<com.example.thriftera.data.User>> _register = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.thriftera.util.Resource<com.example.thriftera.data.User>> register = null;
    private final kotlinx.coroutines.channels.Channel<com.example.thriftera.util.RegisterFieldsState> _validation = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<com.example.thriftera.util.RegisterFieldsState> validation = null;
    
    @javax.inject.Inject
    public RegisterViewModel(@org.jetbrains.annotations.NotNull
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull
    com.google.firebase.firestore.FirebaseFirestore db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.thriftera.util.Resource<com.example.thriftera.data.User>> getRegister() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<com.example.thriftera.util.RegisterFieldsState> getValidation() {
        return null;
    }
    
    public final void createAccountWithEmailAndPassword(@org.jetbrains.annotations.NotNull
    com.example.thriftera.data.User user, @org.jetbrains.annotations.NotNull
    java.lang.String password) {
    }
    
    private final java.lang.Object saveUserInfo(java.lang.String userUid, com.example.thriftera.data.User user, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final boolean checkValidation(com.example.thriftera.data.User user, java.lang.String password) {
        return false;
    }
}