package com.arseniomuanda.person_percistence_api.utils

import java.util.*
import org.mindrot.jbcrypt.BCrypt

fun String.toUUID(): UUID = UUID.fromString(this)

fun String.toSlug(): String = lowercase(Locale.getDefault())
    .replace("\n", " ")
    .replace("[^a-z\\d\\s]".toRegex(), "")
    .split(" ")
    .joinToString("-")
    .replace("-+".toRegex(), "-")


fun String.byCrypt(): String {
   return BCrypt.hashpw(this, BCrypt.gensalt())
}

fun String.getFistLowercase(): String {
    return this.split(" ").first().lowercase()
}