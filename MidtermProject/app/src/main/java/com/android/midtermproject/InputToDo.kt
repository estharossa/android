package com.android.midtermproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.activity_input_to_do.*

class InputToDo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_to_do)
        addNewItemButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id", idEditText.text)
            intent.putExtra("title", titleEditText.text)
            intent.putExtra("description", descriptionEditText.text)
            intent.putExtra("status", statusEditText.text)
            intent.putExtra("category", categoryEditText.text)
            intent.putExtra("durations", durationsEditText.text)
            startActivity(intent)
        }
    }
}
