package com.example.mindbox_sample_flutter

import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import cloud.mindbox.mindbox_android.MindboxAndroidPlugin
import cloud.mindbox.mobile_sdk.Mindbox

class MainActivity: FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processMindboxIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        processMindboxIntent(intent)
    }

    private fun processMindboxIntent(intent: Intent?) {
        intent?.let {
            // Проверяем, что интент - это пуш Mindbox
            val uniqueKey = intent.getStringExtra("uniq_push_key")
            if (uniqueKey != null) {

                Mindbox.onP

                Mindbox.onPushClicked(this, it)
                Mindbox.onNewIntent(it)
                // Передача ссылки из пуша во Flutter
                val link = Mindbox.getUrlFromPushIntent(intent) ?: ""
                MindboxAndroidPlugin.pushClicked(link)

            }
        }
    }

}
