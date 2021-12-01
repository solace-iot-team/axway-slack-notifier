package com.solace.iotteam.model

data class NotificationSubscribe(
    var specversion: String?=null,
    var type: String?=null,
    var source: String?=null,
    var id: String?=null,
    var time:String?=null,
    var datacontenttype:String?=null,
    var data: NotificationSubscribeData?=null
)


