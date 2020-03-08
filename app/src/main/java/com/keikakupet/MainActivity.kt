package com.keikakupet

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ClipDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.TextView
import java.text.DecimalFormat
import kotlin.text.Typography.tm


class MainActivity : AppCompatActivity() {

    private lateinit var petAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // display pet animation
        val petImageView = findViewById<ImageView>(R.id.petImageView).apply {
            setBackgroundResource(R.drawable.sample_pet_animation_list)
            petAnimation = background as AnimationDrawable
        }

        // display status bubble on click
        val bubbleImageView = findViewById<ImageView>(R.id.bubbleImageView)
        var bubbleVisible = false
        petImageView.setOnClickListener{
            if(!bubbleVisible) {
                bubbleImageView.setImageResource(R.drawable.speech_bubble)
                bubbleVisible = true
            }
            else{
                bubbleImageView.setImageResource(android.R.color.transparent)
                bubbleVisible = false
            }
        }

        // launch ToDoListActivity
        val toDoListBtn = findViewById<Button>(R.id.toDoListBtn)
        toDoListBtn.setOnClickListener {
            val startIntent = Intent(this, ToDoListActivity::class.java)
            startActivity(startIntent)
        }

        // health bar
        val barImageView = findViewById<ImageView>(R.id.barImageView)
        val barImageDrawable = barImageView.drawable as ClipDrawable
        var healthPercent = 0.65
        barImageDrawable.level = (10000 * healthPercent).toInt()

        // health text
        val df = DecimalFormat("##.##% HEALTH")
        val formattedPercent = df.format(healthPercent)
        val healthEditText = findViewById<TextView>(R.id.healthEditText)
        healthEditText.setText(formattedPercent)
    }

    override fun onStart() {
        super.onStart()
        petAnimation.start() // start pet animation
    }

}
