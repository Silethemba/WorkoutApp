package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private var restTimer: CountDownTimer? = null
    private var exerciseTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel> = Constants.defaultExerciseList()
    private var exerciseAdapter: ExerciseAdapter = ExerciseAdapter(exerciseList)
    var currentPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityExerciseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarExercise)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarExercise.setNavigationOnClickListener {
            onBackPressed()
        }
        setupRestView()
        setupExerciseStatus()

    }

    private fun setupRestView() {

        binding.txTitle.visibility = View.VISIBLE
        binding.tvExercise.visibility = View.INVISIBLE
        binding.flRestBar.visibility = View.VISIBLE
        binding.flExerciseBar.visibility = View.INVISIBLE
        binding.ivImage.visibility = View.INVISIBLE
        binding.upComingExercise.visibility = View.VISIBLE
        binding.upComingExerciseLabel.visibility = View.VISIBLE

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        binding.upComingExercise.text = exerciseList[currentPosition + 1].getName()
        setRestProgressBar()
    }

    private fun setupExerciseView() {

        binding.txTitle.visibility = View.INVISIBLE
        binding.tvExercise.visibility = View.VISIBLE
        binding.flRestBar.visibility = View.INVISIBLE
        binding.flExerciseBar.visibility = View.VISIBLE
        binding.ivImage.visibility = View.VISIBLE
        binding.upComingExercise.visibility = View.INVISIBLE
        binding.upComingExerciseLabel.visibility = View.INVISIBLE

        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        binding.ivImage.setImageResource(exerciseList[currentPosition].getImage())
        binding.tvExercise.text = exerciseList[currentPosition].getName()

        setExerciseProgressBar()

    }

    private fun setupExerciseStatus() {
        binding.exerciseRv.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.exerciseRv.adapter = exerciseAdapter
    }

    private fun setRestProgressBar() {
        binding.restProgressBar.progress = restProgress
        restTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                binding.restProgressBar.progress = 5 - restProgress
                binding.tvRestTimer.text = (5 - restProgress).toString()
            }

            override fun onFinish() {
                currentPosition++
                exerciseList[currentPosition].setIsSelected(true)
                exerciseAdapter.notifyDataSetChanged()
                setupExerciseView()
            }

        }.start()
    }

    private fun setExerciseProgressBar() {
        binding.exerciseProgressBar.progress = restProgress
        exerciseTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding.exerciseProgressBar.progress = 15 - exerciseProgress
                binding.tvExerciseTimer.text = (15 - exerciseProgress).toString()
            }

            override fun onFinish() {

                exerciseList[currentPosition].setIsSelected(false)
                exerciseList[currentPosition].setIsCompleted(true)
                exerciseAdapter.notifyDataSetChanged()

                if (currentPosition < exerciseList.size - 1) {
                    setupRestView()
                } else {
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
    }
}