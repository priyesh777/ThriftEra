package com.groupproject.thriftera.util

import androidx.fragment.app.Fragment
import com.groupproject.thriftera.activities.ShoppingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hideBottomNavigationView(){
    val bottomNavigationView =
        (activity as ShoppingActivity).findViewById<BottomNavigationView>(
            com.groupproject.thriftera.R.id.bottomNavigation
        )
    bottomNavigationView.visibility = android.view.View.GONE
}

fun Fragment.showBottomNavigationView(){
    val bottomNavigationView =
        (activity as ShoppingActivity).findViewById<BottomNavigationView>(
            com.groupproject.thriftera.R.id.bottomNavigation
        )
    bottomNavigationView.visibility = android.view.View.VISIBLE
}