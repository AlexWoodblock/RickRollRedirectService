// This project is libre, and licenced under the terms of the
// DO WHAT THE FUCK YOU WANT TO PUBLIC LICENCE, version 3.1,
// as published by dtf on July 2019. See the COPYING file or
// https://ph.dtf.wtf/w/wtfpl/#version-3-1 for more details.

package com.woodblockwithoutco.rickroller.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

/**
 * Description of SQL table containing data of Rick Rolled people.
 */
object RickRolledTable: IntIdTable("rick_rolled") {

    /**
     * When the person was Rick Rolled.
     */
    val at = datetime("at").defaultExpression(CurrentDateTime)
}