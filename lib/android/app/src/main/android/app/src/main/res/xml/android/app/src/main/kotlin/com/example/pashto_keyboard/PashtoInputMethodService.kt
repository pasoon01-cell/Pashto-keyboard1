
package com.example.pashto_keyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.Gravity
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout

class PashtoInputMethodService : InputMethodService() {

    private val rows = listOf(
        listOf("ق","و","ع","ر","ت","ې","ي","ږ","ګ","غ"),
        listOf("ا","س","د","پ","ټ","ب","ل","م","ن"),
        listOf("ځ","چ","ښ","ش","خ","ح","ج","ډ","ړ"),
        listOf("؟","،","ئ","ۀ","Space","⌫","↵")
    )

    override fun onCreateInputView(): View {
        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(4, 8, 4, 8)
        root.setBackgroundColor(Color.rgb(35, 38, 45))

        rows.forEach { row ->
            val line = LinearLayout(this)
            line.orientation = LinearLayout.HORIZONTAL
            line.gravity = Gravity.CENTER
            row.forEach { key ->
                val button = Button(this)
                button.text = if (key == "Space") "فاصله" else key
                button.textSize = if (key == "Space") 13f else 20f
                button.setTextColor(Color.WHITE)
                button.typeface = Typeface.DEFAULT_BOLD
                button.setBackgroundColor(Color.rgb(65, 70, 80))

                val params = LinearLayout.LayoutParams(
                    0,
                    52,
                    if (key == "Space") 2.5f else 1f
                )
                params.setMargins(2, 3, 2, 3)
                line.addView(button, params)

                button.setOnClickListener {
                    val ic: InputConnection? = currentInputConnection
                    when (key) {
                        "Space" -> ic?.commitText(" ", 1)
                        "⌫" -> ic?.deleteSurroundingText(1, 0)
                        "↵" -> ic?.commitText("\n", 1)
                        else -> ic?.commitText(key, 1)
                    }
                }
            }
            root.addView(line)
        }
        return root
    }
}
