package com.android.leivacourse.artapp.data.local

import androidx.room.TypeConverter
import com.android.leivacourse.artapp.data.local.model.Urls
import com.android.leivacourse.artapp.data.local.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): Urls? {
        val listType = object : TypeToken<Urls>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromUrl(url: Urls?): String? {
        val gson = Gson()
        return gson.toJson(url)
    }

    @TypeConverter
    fun fromStringUser(value: String?): User? {
        val listType = object : TypeToken<User>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromUser(user: User?): String? {
        val gson = Gson()
        return gson.toJson(user)
    }
}