package com.example.mindbox_sample_flutter

import cloud.mindbox.mobile_sdk.Mindbox
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MindboxMessagingService : FirebaseMessagingService()  {
    override fun onNewToken(token: String) {
        Mindbox.updateFmsToken(applicationContext, token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Метод возвращает boolean, чтобы можно было сделать фолбек для обработки пуш уведомлений
        val messageWasHandled = Mindbox.handleRemoteMessage(
            context = applicationContext,
            activities = mapOf(),
            message = remoteMessage,
            channelId = "notification_channel_id", // Идентификатор канала для уведомлений, отправленных из Mindbox
            channelName = "notification_channel_name", // Название канала
            pushSmallIcon = android.R.drawable.ic_dialog_info, // Маленька иконка для уведомлений
            defaultActivity = MainActivity::class.java,
            channelDescription = "notification_channel_description" // Описание канала
        )

        if (!messageWasHandled) {
            // Если пуш был не от Mindbox или в нем некорректные данные, то можно написать фолбе для его обработки
        }
    }


}