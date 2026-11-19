
package com.example.pashto_keyboard

import android.content.Intent
import android.provider.Settings
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {

    override fun configureFlutterEngine(
        flutterEngine: FlutterEngine
    ) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "pashto_keyboard"
        ).setMethodCallHandler { call, result ->

            if (call.method == "openKeyboardSettings") {
                val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
                startActivity(intent)
                result.success(true)
            } else {
                result.notImplemented()
            }
        }
    }
}
