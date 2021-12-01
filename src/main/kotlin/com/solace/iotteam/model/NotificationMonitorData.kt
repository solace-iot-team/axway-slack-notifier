package com.solace.iotteam.model

data class NotificationMonitorData(
    var trigger: String?=null,
    var success: Boolean?=null,
    var message: String? = null,
    var correlationId: String? = null,
    var environment: String?=null,
    var team: String?=null,
    var api: String?=null,
    var product: String?=null,
    var application: String?=null,
    var subscription: String?=null,
    var subscriber: String?=null,
    var subscriberEmail: String?=null,
)

