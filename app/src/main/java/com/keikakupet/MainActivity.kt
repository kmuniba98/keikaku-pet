package com.keikakupet

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ClipDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.text.DecimalFormat

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

        // register notification channel
        val channelId = createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")

        // display status bubble on click
        val bubbleImageView = findViewById<ImageView>(R.id.bubbleImageView)
        var bubbleVisible = false
        petImageView.setOnClickListener{
            createNotification(channelId, "this be a title", "this be content")
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

    private fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String): String {

        val channelId = "${context.packageName}-$name" // create unique name for the notification channel

        // safety check the OS version for API 26 and greater
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // display name and description in application's notification settings
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            // create channel using NotificationManager
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        return channelId
    }

    private fun createNotification(channelId:String, titleText:String, contentText:String){

        // create an Intent to launch MainActivity
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        // wrap the Intent in a PendingIntent, created through getActivity() method which returns description of an Activity to be launched
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        // build notification
        val notificationBuilder = NotificationCompat.Builder(this, channelId).apply {
            setSmallIcon(R.drawable.notification_icon)
            setContentTitle(titleText)
            setContentText(contentText)
            priority = NotificationCompat.PRIORITY_DEFAULT
            setAutoCancel(true)
            setContentIntent(pendingIntent)
        }

        // use the app's context to get a reference to NotificationManagerCompat
        val notificationManager = NotificationManagerCompat.from(this)

        // call notify() on the NotificationManager passing in an identifier and the notification
        notificationManager.notify(1001, notificationBuilder.build())
    }
}
