package com.solace.iotteam.model

import com.fasterxml.jackson.databind.JsonNode

data class NotificationSubscribeData(
    var environment: String?=null,
    var team: String?=null,
    var api: String?=null,
    var product: String?=null,
    var application: String?=null,
    var subscription: String?=null,
    var subscriber: String?=null,
    var subscriberEmail: String?=null,
    var applicationData: JsonNode?=null,
    var asyncApis: JsonNode?=null
)

