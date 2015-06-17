package me.rbrickis.yggdrasiltest.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
Created by Ryan on 6/16/2015

Project: YggdrasilTest
 */

object UUIDUtils {

    val UUID_PATTERN = Pattern.compile("^([A-Fa-f0-9]{8})([A-Fa-f0-9]{4})([A-Fa-f0-9]{4})([A-Fa-f0-9]{4})([A-Fa-f0-9]{12})$");


    fun addDashes(uuid : String) : String {
        val uuidl = uuid.replace("-", ""); // Remove dashes
        val matcher = UUID_PATTERN.matcher(uuidl);
        if (!matcher.matches()) {
            throw IllegalArgumentException("Invalid UUID format");
        }
        return matcher.replaceAll("$1-$2-$3-$4-$5");
    }


}