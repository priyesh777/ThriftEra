package com.groupproject.thriftera.data

data class User(
    //Data types for user profile
    val firstName: String,
    val lastName: String,
    val email: String,
    var imagePath: String = ""
){
    constructor(): this("","","","")
}
