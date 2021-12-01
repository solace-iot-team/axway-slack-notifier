package com.solace.iotteam

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import jdk.jfr.ContentType

data class SlackMonitorMessage(
    val text:String
)

interface SlackMonitorPublisher {

    @Post("\${monitor.slack.success}")
    fun publishMonitorSuccess(@Body message: SlackMonitorMessage)

    @Post("\${monitor.slack.fault}")
    fun publishMonitorFault(@Body message: SlackMonitorMessage)
}


@Client("\${monitor.slack.url}")
interface SlackMonitorClient: SlackMonitorPublisher {
}