package com.example.pashto_keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout

class PashtoInputMethodService : InputMethodService() {

    override fun onCreateInputView(): View {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val rows = listOf(
            listOf("ق","و","ع","ر","ت","ي","ې","ء","پ"),
            listOf("ا","س","د","ف","ګ","ه","ڼ","ک","ل"),
            listOf("ز","خ","څ","چ","ش","م","ن","ب",""),
            listOf("غ","ښ","ږ","ډ","ټ","ۍ","ی"," space ","⌫")
        )

        for (row in rows) {
            val line = LinearLayout(this)
            for (key in row) {
                if (key.isEmpty()) continue
                val button = Button(this)
                button.text = if (key == "space") "فاصله" else key
                button.setOnClickListener {
                    val ic: InputConnection? = currentInputConnection
                    when (key) {
                        "⌫" -> ic?.deleteSurroundingText(1, 0)
                        " space " -> ic?.commitText(" ", 1)
                        else -> ic?.commitText(key, 1)
                    }
                }
                line.addView(button, LinearLayout.LayoutParams(0, 55, 1f))
            }
            layout.addView(line)
        }
        return layout
    }
}
