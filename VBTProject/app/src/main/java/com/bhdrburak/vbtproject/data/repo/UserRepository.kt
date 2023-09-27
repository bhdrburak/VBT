package com.bhdrburak.vbtproject.data.repo

import androidx.lifecycle.LiveData
import com.bhdrburak.vbtproject.common.Resource
import com.bhdrburak.vbtproject.data.RetrofitAPI
import com.bhdrburak.vbtproject.data.UserDao
import com.bhdrburak.vbtproject.data.model.UserModel
import java.lang.Exception
import javax.inject.Inject


class UserRepository  @Inject constructor (
    private val userDao : UserDao,
    private val retrofitApi : RetrofitAPI
) : UserRepositoryInterface {


    override suspend fun insertRepo(user: UserModel) {
        return userDao.insertUser(user)
    }

    override suspend fun deleteRepo(user: UserModel) {
        return userDao.deleteUser(user)
    }

    override fun getUsers(): LiveData<List<UserModel>> {
        return userDao.getAllUser()
    }


    override suspend fun fetchUser(username : String): Resource<UserModel> {
        return try {
            val response = retrofitApi.fetchUser(username)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        } catch (e: Exception) {
            Resource.error("No data!",null)
        }
    }

    override suspend fun fetchUserRepo(username: String): Resource<List<UserModel>> {
        return try {
            val response = retrofitApi.fetchAllUser(username)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        } catch (e: Exception) {
            Resource.error("No data!",null)
        }
    }

}