package com.bhdrburak.vbtproject.data.repo

import androidx.lifecycle.LiveData
import com.bhdrburak.vbtproject.common.Resource
import com.bhdrburak.vbtproject.data.model.UserModel


interface UserRepositoryInterface {

    suspend fun insertRepo(repo : UserModel)

    suspend fun deleteRepo(repo: UserModel)

    fun getUsers() : LiveData<List<UserModel>>

    suspend fun fetchUser(username : String) : Resource<UserModel>

    suspend fun fetchUserRepo(username : String) : Resource<List<UserModel>>
}