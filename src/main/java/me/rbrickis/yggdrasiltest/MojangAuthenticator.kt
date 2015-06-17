package me.rbrickis.yggdrasiltest

import me.rbrickis.yggdrasiltest.http.HttpClientImpl
import me.rbrickis.yggdrasiltest.http.JSONBuilder
import me.rbrickis.yggdrasiltest.http.RequestType
import me.rbrickis.yggdrasiltest.utils.UUIDUtils
import org.json.simple.JSONObject
import me.rbrickis.yggdrasiltest.PlayerProfile
import me.rbrickis.yggdrasiltest.UserProfile
import java.net.URL
import java.util.UUID

/**
Created by Ryan on 6/15/2015

Project: YggdrasilTest
 */
public class MojangAuthenticator : Authenticator {

    override fun authenticate(profile: UserProfile): PlayerProfile {

        val body = JSONBuilder.create()
                .addJSONObject("agent", JSONBuilder.create()
                        .add("name", "Minecraft")
                        .add("version", 1).build())
                .add("username", profile.username)
                .add("password", profile.password)
                .build()

        val client = me.rbrickis.yggdrasiltest.http.HttpClientImpl(URL("https://authserver.mojang.com/authenticate"))
                .withAgent("Mozilla/5.0")
                .addHeader("Content-Type", "application/json")
                .withBody(body)
                .withRequestType(RequestType.POST)
        val response = client.post()
        println(response.toJSONString())

        val selectedProfile = response.get("selectedProfile") as JSONObject

        val name = selectedProfile.get("name") as String
        val uuid = UUID.fromString(UUIDUtils.addDashes(selectedProfile.get("id") as String))

        return PlayerProfile(name, uuid)
    }
}