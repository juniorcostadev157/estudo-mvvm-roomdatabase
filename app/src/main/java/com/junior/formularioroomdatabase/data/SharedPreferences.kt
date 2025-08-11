package com.junior.formularioroomdatabase.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("localdata", Context.MODE_PRIVATE)


    fun savePreference(key:String, value:String){
        preferences.edit().putString(key, value).apply()
    }

    fun saveID(key: String, value:Long){
        preferences.edit().putLong(key, value).apply()
    }

    fun deletePreference(key:String){
        preferences.edit().remove(key).apply()
    }

    fun getPreference(key: String): String {
        return preferences.getString(key, "") ?: ""
    }

    fun getByIdPreference(key: String): Long {
        return preferences.getLong(key,0L)
    }
}