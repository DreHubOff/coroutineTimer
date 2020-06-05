package com.studying.simpletimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener {
            val text = edit_text.text.toString()
            if (text.isNotEmpty()) GlobalScope.launch { count(text.toInt()) }
        }
    }

    private suspend fun count(secNum: Int) {
        changeStatus(false)
        var i = secNum
        do {
            withContext(Dispatchers.Main) { text_view.text = i.toString() }
            i--
            delay(1000)
        } while (i > 0)
        changeStatus(true)
    }

    private suspend fun changeStatus(flag: Boolean) {
        withContext(Dispatchers.Main) {
            start.isEnabled = flag
            edit_text.isEnabled = flag
        }
    }
}
