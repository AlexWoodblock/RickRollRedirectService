// This project is libre, and licenced under the terms of the
// DO WHAT THE FUCK YOU WANT TO PUBLIC LICENCE, version 3.1,
// as published by dtf on July 2019. See the COPYING file or
// https://ph.dtf.wtf/w/wtfpl/#version-3-1 for more details.

package com.woodblockwithoutco.rickroller.db

import org.jetbrains.exposed.sql.Database
import org.slf4j.LoggerFactory
import java.net.URI

/**
 * Create database from raw passed URL.
 */
fun createDatabase(
    url: String
): Database {
    return Database.connect(
        url = url
    )
}