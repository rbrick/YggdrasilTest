package me.rbrickis.yggdrasiltest.http

import org.json.simple.JSONObject

/**
Created by Ryan on 6/15/2015

Project: YggdrasilTest
 */
public interface HttpClient {

    fun post() : JSONObject

    fun get() : JSONObject
}