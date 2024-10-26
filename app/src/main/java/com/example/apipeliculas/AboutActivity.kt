package com.example.apipeliculas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AboutActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val backToMainButton: MaterialButton = findViewById(R.id.backToMainButton)
        backToMainButton.setOnClickListener {
            finish()
        }
    }
}
