package com.solace.iotteam

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import jdk.jfr.ContentType

data class SlackMessage(
    val text:String
)

interface SlackPublisher {

    @Post("\${notifier.slack.subscribe}")
    fun publishSubscribeNotification(@Body message: SlackMessage)

    @Post("\${notifier.slack.unsubscribe}")
    fun publisUnsubscribeNotification(@Body message: SlackMessage)
}

@Client("\${notifier.slack.url}")
interface SlackNotificationClient: SlackPublisher {
}