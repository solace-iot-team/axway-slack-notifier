package com.solace.iotteam.messaging

import com.fasterxml.jackson.databind.node.TextNode
import com.solace.iotteam.*
import com.solace.iotteam.model.NotificationUnsubscribe
import com.solace.iotteam.model.NotificationMonitor
import com.solace.iotteam.model.NotificationSubscribe
import com.solace.iotteam.service.NotificationService
import io.micronaut.context.annotation.Requires
import io.micronaut.mqtt.annotation.MqttSubscriber
import io.micronaut.mqtt.annotation.Qos
import io.micronaut.mqtt.annotation.Topic
import jakarta.inject.Inject

@Requires(property = "mqtt-enabled")
@MqttSubscriber
class NotificationSubscriber {

    @Inject
    lateinit var notificationService: NotificationService


    @Topic("\${topic.echo}")
    @Qos(1)
    fun receiveEcho(message:ByteArray){
        L.info("Received ECHO Message (${String(message)})")
    }

    @Topic("\${topic.subscribe}")
    @Qos(1)
    fun receiveSubscribe(ne: NotificationSubscribe){
        notificationService.handleSubscribe(ne)
    }

    @Topic("\${topic.unsubscribe}")
    @Qos(1)
    fun receiveUnsubscribe(ne: NotificationUnsubscribe){
        notificationService.handleUnsubscribe(ne)
    }



    @Topic("\${topic.success}")
    @Qos(1)
    fun receiveSuccessMonitor(ne: NotificationMonitor){
        notificationService.handleSuccessMonitor(ne)

    }

    @Topic("\${topic.fault}")
    @Qos(1)
    fun receiveFailureMonitor(ne: NotificationMonitor){
        notificationService.handleFailureMonitor(ne)
    }

    companion object {
        val L = logger()
    }
}