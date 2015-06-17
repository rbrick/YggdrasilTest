package me.rbrickis.yggdrasiltest

import me.rbrickis.yggdrasiltest.PlayerProfile
import me.rbrickis.yggdrasiltest.UserProfile

/**
Created by Ryan on 6/15/2015

Project: YggdrasilTest
 */
public interface Authenticator {

    fun authenticate(profile : UserProfile) : PlayerProfile

}