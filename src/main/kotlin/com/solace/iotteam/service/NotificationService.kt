package com.solace.iotteam.service

import com.fasterxml.jackson.databind.node.TextNode
import com.solace.iotteam.*
import com.solace.iotteam.model.NotificationMonitor
import com.solace.iotteam.model.NotificationSubscribe
import com.solace.iotteam.model.NotificationUnsubscribe
import io.micronaut.context.annotation.Bean
import io.micronaut.mqtt.annotation.Qos
import io.micronaut.mqtt.annotation.Topic
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class NotificationService {

    @Inject
    lateinit var clientNotification: SlackNotificationClient
    @Inject
    lateinit var clientMonitor: SlackMonitorClient

    fun handleSubscribe(ne: NotificationSubscribe){
        val key = ne.data?.applicationData?.at("/credentials/secret/consumerKey") as TextNode
        val secret = ne.data?.applicationData?.at("/credentials/secret/consumerSecret") as TextNode
        val internalName = ne.data?.applicationData?.at("/internalName") as TextNode
        val message = "*Subscribe*\nSubscription: `${ne.data?.subscription}`\nSubscriber: `${ne.data?.subscriber}`\nEmail: `${ne.data?.subscriberEmail}`\nEnvironment: `${ne.data?.environment}`\nTeam: `${ne.data?.team}`\nAPI: `${ne.data?.api}`\nProduct: `${ne.data?.product}`\nApplication: `${ne.data?.application}`\nInternalName: `${internalName}`\n\nConsumerKey:`${key.asText()}`\nConsumerSecret:`${secret.asText()}` "
        L.info("Received Subscribe Message (Environment: ${ne.data?.environment} Subscription: ${ne.data?.subscription}, Subscriber: ${ne.data?.subscriber})")
        clientNotification.publishSubscribeNotification(SlackMessage(text = message))
    }

    fun handleUnsubscribe(ne: NotificationUnsubscribe){
        val message = "*Unsubscribe*\nSubscription: `${ne.data?.subscription}`\nSubscriber: `${ne.data?.subscriber}`\nEmail: `${ne.data?.subscriberEmail}`\nEnvironment: `${ne.data?.environment}`\nTeam: `${ne.data?.team}`\nAPI: `${ne.data?.api}`\nProduct: `${ne.data?.product}`\nApplication: `${ne.data?.application}`"
        L.info("Received Unsubscribe Message (Environment: ${ne.data?.environment} Subscription: ${ne.data?.subscription}, Subscriber: ${ne.data?.subscriber})")
        clientNotification.publisUnsubscribeNotification(SlackMessage(text = message))
    }

  fun handleSuccessMonitor(ne: NotificationMonitor){
        val message = "*MONITOR SUCCESS*\nTrigger: `${ne.data?.trigger}`\nMessage: `${ne.data?.message}`\nSubscription: `${ne.data?.subscription}`\nEnvironment: `${ne.data?.environment}`"
        L.info("Received Monitor Success Message (Environment: ${ne.data?.environment} Subscription: ${ne.data?.subscription}, Subscriber: ${ne.data?.subscriber})")
        clientMonitor.publishMonitorSuccess(SlackMonitorMessage(text = message))

    }

    fun handleFailureMonitor(ne: NotificationMonitor){
        val message = "*MONITOR FAILURE*\n\nTrigger: `${ne.data?.trigger}`\nMessage: `${ne.data?.message}`\nSubscription: `${ne.data?.subscription}`\nEnvironment: `${ne.data?.environment}`"
        L.info("Received Monitor Failure Message (Environment: ${ne.data?.environment} Subscription: ${ne.data?.subscription}, Subscriber: ${ne.data?.subscriber})")
        clientMonitor.publishMonitorFault(SlackMonitorMessage(text = message))
    }

    companion object {
        val L = logger()
    }
}