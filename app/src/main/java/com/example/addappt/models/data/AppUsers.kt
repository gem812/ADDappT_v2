package com.example.addappt.models.data

import java.lang.Exception

data class AppUsers(
    val id : String?,
    val userId : String,
    val displayName : String,
    val age : Int
) {
    fun toMap() : MutableMap<String, Any>{
        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName,
            "age" to this.age
        )
    }
}
