// This project is libre, and licenced under the terms of the
// DO WHAT THE FUCK YOU WANT TO PUBLIC LICENCE, version 3.1,
// as published by dtf on July 2019. See the COPYING file or
// https://ph.dtf.wtf/w/wtfpl/#version-3-1 for more details.

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