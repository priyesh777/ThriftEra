package com.groupproject.thriftera.data

sealed class Category(val category: String) {

    object Chair: Category("Chair")
    object Cupboard: Category("Cupboard")
    object Table: Category("Table")
    object Accessory: Category("Accessories")
    object Furniture: Category("Furniture")
}