package com.example.program.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "MessagingServiceLog"

    private lateinit var remoteMessage: RemoteMessage

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        this.remoteMessage = remoteMessage

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Body: ${it.body}, Title: ${it.title}, icon: ${it.icon}")
        }
    }


}