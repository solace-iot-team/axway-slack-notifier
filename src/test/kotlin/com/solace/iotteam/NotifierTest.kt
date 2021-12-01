package com.solace.iotteam

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import jakarta.inject.Inject

@MicronautTest
class NotifierTest {

    @Inject
    lateinit var application: EmbeddedApplication<*>

    @Inject
    lateinit var clientNotification:SlackNotificationClient

    @Inject
    lateinit var clientMonitor:SlackMonitorClient

    @Test
    fun testItWorks() {
        Assertions.assertTrue(application.isRunning)
    }

    @Test
    fun testPublishSubscribe(){
        clientNotification.publishSubscribeNotification(SlackMessage(text="Testmessage Subscribe, please ignore"))
    }

    @Test
    fun testPublishUnsubscribe(){
        clientNotification.publisUnsubscribeNotification(SlackMessage(text="Testmessage Unsubscribe, please ignore"))
    }

    @Test
    fun testPublishMonitorSuccess(){
        clientMonitor.publishMonitorSuccess(SlackMonitorMessage(text="Testmessage Monitor Success, please ignore"))
    }

    @Test
    fun testPublishMonitorFailure(){
        clientMonitor.publishMonitorFault(SlackMonitorMessage(text="Testmessage Monitor Failure, please ignore"))
    }
}
