package com.solace.iotteam

import io.micronaut.runtime.Micronaut.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("com.solace.iotteam")
        .start()
}

inline fun <reified R:Any> R.logger(): Logger = LoggerFactory.getLogger(this::class.java.name.substringBefore("\$Companion"))
