package com.example.applibre.data.model.db


/**
 * modelo de usuario con un id, email y nombre
 * @param userId id del usuario
 * @param email email del usuario
 * @param username nombre del usuario
 */
data class UserModel(
    val userId: String,
    val email: String,
    val username: String,
)
