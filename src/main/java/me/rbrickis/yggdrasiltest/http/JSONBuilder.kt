package me.rbrickis.yggdrasiltest.http

import org.json.simple.JSONArray
import org.json.simple.JSONObject

/**
Created by Ryan on 6/16/2015

Project: YggdrasilTest
 */
public class JSONBuilder(private val parent : JSONObject) {

    companion object {
        public fun create() : JSONBuilder {
            return JSONBuilder(JSONObject())
        }
    }


    fun add(k : String, v : Any?) : JSONBuilder {
        parent.put(k, v)
        return this
    }

    fun addJSONArray(k : String, v : JSONArray) : JSONBuilder {
        parent.put(k, v)
        return this
    }


    fun addJSONObject(k : String, v : JSONObject) : JSONBuilder {
        parent.put(k, v)
        return this
    }


    fun build() : JSONObject {
        return JSONObject(parent)
    }




}


