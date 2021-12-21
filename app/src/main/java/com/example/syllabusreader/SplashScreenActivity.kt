package com.example.syllabusreader

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import androidx.lifecycle.lifecycleScope
import com.example.syllabusreader.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var animator: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar1.visibility = View.GONE

        //Init
//        animator = ObjectAnimator()
//        animator.duration = 1500L
//
//        binding.progressBar1.max = 100
//        binding.progressBar1.scaleX = 2F


//        animator.addListener {
//            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

//        progressAnimation(2000L)

        lifecycleScope.launch {
            delay(1000L)
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun progressAnimation(duration: Long){
        val animation = ProgressAnimation(this,this,binding.progressBar1,0F,100F)
        animation.duration = duration
        binding.progressBar1.animation = animation
//        finish()
    }
}