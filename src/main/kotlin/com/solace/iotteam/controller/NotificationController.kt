/*
 * MIT License
 *
 * Copyright (c) 2021., Solace Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.solace.iotteam.controller

import com.solace.iotteam.logger
import com.solace.iotteam.model.NotificationMonitor
import com.solace.iotteam.model.NotificationSubscribe
import com.solace.iotteam.model.NotificationUnsubscribe
import com.solace.iotteam.service.NotificationService
import io.micronaut.context.annotation.Requires
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject

@Requires(property = "rest-enabled")
@Controller("/notification")
class NotificationController {

    @Inject
    lateinit var notificationService: NotificationService


   @Post("/health",produces = [MediaType.APPLICATION_JSON])
   fun postHealth(@Body payload: String?):String {
       L.info("Received ECHO / HEALTH message (${payload})")
        return """ { "notifier":"ok"} """
    }

    @Post("/subscribe")
    fun postSubscribe(@Body payload: NotificationSubscribe) {
        L.trace("/subscribe $payload")
        notificationService.handleSubscribe(payload)
    }

    @Post("/unsubscribe")
    fun postUnsubscribe(@Body payload: NotificationUnsubscribe) {
        L.trace("/unsubscribe $payload")
        notificationService.handleUnsubscribe(payload)
    }

    @Post("/monitor/success")
    fun postMontiorSuccess(@Body payload: NotificationMonitor) {
        L.trace("/monitor/success $payload")
        notificationService.handleSuccessMonitor(payload)
    }

    @Post("/monitor/failure")
    fun postMonitorFailure(@Body payload: NotificationMonitor) {
        L.trace("/monitor/failure $payload")
        notificationService.handleFailureMonitor(payload)
    }

    companion object {
        val L = logger()
    }
}