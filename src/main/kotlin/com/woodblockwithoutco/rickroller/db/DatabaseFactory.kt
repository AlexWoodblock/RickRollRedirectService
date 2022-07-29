package com.woodblockwithoutco.rickroller.db

import org.jetbrains.exposed.sql.Database
import java.net.URI

/**
 * Create database from raw passed URL.
 */
fun createDatabase(
    rawUrl: String
): Database {
    val uri = URI(rawUrl)

    val userInfo = uri.userInfo.split(":")
    val userName = userInfo[0]
    val userPassword = userInfo[1]

    val url = "jdbc:postgresql://" + uri.host + ':' + uri.port + uri.path

    return Database.connect(
        url = url,
        user = userName,
        password = userPassword
    )
}