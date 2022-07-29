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