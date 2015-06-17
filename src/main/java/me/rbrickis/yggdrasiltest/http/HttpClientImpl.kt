package me.rbrickis.yggdrasiltest.http

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import me.rbrickis.yggdrasiltest.http.HttpClient
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.HashMap

/**
Created by Ryan on 6/15/2015

Project: YggdrasilTest
 */
public class HttpClientImpl(private var url: URL) : HttpClient {

    private val headers = HashMap<String, String>()

    private var requestType = RequestType.GET

    private var body = "{}"


    fun addHeader(k: String, v: String) : HttpClientImpl {
        headers.put(k, v);
        return this
    }

    fun withRequestType(type: RequestType) : HttpClientImpl  {
        requestType = type
        return this
    }

    fun withAgent(agent: String) : HttpClientImpl {
        headers.put("User-Agent", agent)
        return this
    }

    fun withBody(body: JSONObject) : HttpClientImpl {
        this.body = body.toJSONString()
        return this
    }

    override fun get(): JSONObject {
        if (requestType != RequestType.GET) {
            throw IllegalArgumentException("Request type does not equal GET")
        }
        val conn : HttpURLConnection = url.openConnection() as HttpURLConnection
        conn.setRequestMethod(requestType.name())
        headers.forEach { k, v ->  conn.addRequestProperty(k, v) }
        val parser = JSONParser()
        return parser.parse(InputStreamReader(conn.getInputStream())) as JSONObject
    }

    override fun post(): JSONObject {
        if (requestType != RequestType.POST) {
            throw IllegalArgumentException("Request type does not equal POST")
        }

        val conn : HttpURLConnection = url.openConnection() as HttpURLConnection
        conn.setDoOutput(true)
        conn.setRequestMethod(requestType.name())

        headers.forEach { k, v ->  conn.addRequestProperty(k, v) }

        val writer = OutputStreamWriter(conn.getOutputStream())
        writer.write(body)
        writer.flush()
        writer.close()

        val parser = JSONParser()

        return parser.parse(InputStreamReader(conn.getInputStream())) as JSONObject
    }
}