package com.example.syllabusreader

import android.content.Context
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar

class ProgressAnimation(
    private val activity: SplashScreenActivity,
    private val context: Context,
    private val progressBar: ProgressBar,
    private val from: Float,
    private val to: Float
) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)

        val value = from + (to - from) * interpolatedTime
        progressBar.progress = value.toInt()
//        if(value == to) {
//
//            val intent = Intent(context, MainActivity::class.java)
//            context.startActivity(intent)
////            activity.finish()
//        }
    }
}