package com.yigit.catchthekenny

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.yigit.catchthekenny.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler()

    var score = 0
    var imageArray = ArrayList<ImageView>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent
        val isStarted = intent.getBooleanExtra("isStarted",true)

        if(isStarted){
            //Create Image Array
            createImageArray()
            //Start Timer
            startTimer()
        }



    }

    private fun createImageArray(){
        imageArray.add(binding.kennyImageView1)
        imageArray.add(binding.kennyImageView2)
        imageArray.add(binding.kennyImageView3)
        imageArray.add(binding.kennyImageView4)
        imageArray.add(binding.kennyImageView5)
        imageArray.add(binding.kennyImageView6)
        imageArray.add(binding.kennyImageView7)
        imageArray.add(binding.kennyImageView8)
        imageArray.add(binding.kennyImageView9)
        hideImages()
    }

    private fun hideImages(){
        runnable = Runnable {
            for(image in imageArray){
                image.visibility = View.INVISIBLE
            }
            val random = Random()
            val randomIndex = random.nextInt(9)
            imageArray[randomIndex].visibility = View.VISIBLE
            handler.postDelayed(runnable,300)
        }
        handler.post(runnable)
    }



    private fun startTimer(){
        object : CountDownTimer(21000,1000){
            override fun onTick(p0: Long) {
                binding.timeTextView.text = "Time: ${p0/1000}"
            }

            override fun onFinish() {
                binding.timeTextView.text = "Time: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                showAlertDialog()
            }

        }.start()

    }


    fun increaseScore(view: View) {
        score += 1
        binding.scoreTextView.text = "Score: $score"
    }

    fun showAlertDialog(){
        var alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("Game Over")
        alert.setMessage("Restart Game?")
        alert.setCancelable(false)
        alert.setPositiveButton("Yes"){dialog,which->
            //Restart
            val intent = intent
            finish() //Call onDestroyed() Method
            startActivity(intent) //Call onCreateMethod
        }
        alert.setNegativeButton("No"){diaog,which ->
            Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
        }
        alert.show()
    }
}