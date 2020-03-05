package com.keikakupet

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

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
    }

    override fun onStart() {
        super.onStart()
        petAnimation.start() // start pet animation
    }

}
