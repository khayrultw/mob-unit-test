package com.kht.mob23location.data.repo

class UserRepo: IUserRepo {
    override fun getUser(): String {
        //Call actual api or database query to get the info
        return "Hello User"
    }
}