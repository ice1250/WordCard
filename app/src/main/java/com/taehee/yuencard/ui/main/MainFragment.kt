package com.taehee.yuencard.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.taehee.yuencard.R
import com.taehee.yuencard.databinding.MainFragmentBinding
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import java.util.*

class MainFragment : Fragment(), MainHandler, TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        tts = TextToSpeech(requireContext(), this)
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.handler = this

        viewModel.getColor().observe(viewLifecycleOwner,
            { binding.main.setBackgroundColor(Color.parseColor(it)) })

        binding.root.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.particle.build()
                        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(100L)
                        .addShapes(Shape.Square, Shape.Circle)
                        .addSizes(Size(12))
                        .setPosition(motionEvent.x,
                            motionEvent.x,
                            motionEvent.y,
                            motionEvent.y)
                        .streamFor(100, 300L)
                }
                MotionEvent.ACTION_MOVE -> {
                    binding.particle.build()
                        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(100L)
                        .addShapes(Shape.Square, Shape.Circle)
                        .addSizes(Size(12))
                        .setPosition(motionEvent.x,
                            motionEvent.x,
                            motionEvent.y,
                            motionEvent.y)
                        .streamFor(10, 300L)
                }
            }
            false
        }
        binding.cardView.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.particle.build()
                        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(100L)
                        .addShapes(Shape.Square, Shape.Circle)
                        .addSizes(Size(14))
                        .setPosition(motionEvent.x + binding.cardView.x,
                            motionEvent.x + binding.cardView.x,
                            motionEvent.y + binding.cardView.y,
                            motionEvent.y + binding.cardView.y)
                        .streamFor(100, 300L)
                }
                MotionEvent.ACTION_MOVE -> {
                    binding.particle.build()
                        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(100L)
                        .addShapes(Shape.Square, Shape.Circle)
                        .addSizes(Size(14))
                        .setPosition(motionEvent.x + binding.cardView.x,
                            motionEvent.x + binding.cardView.x,
                            motionEvent.y + binding.cardView.y,
                            motionEvent.y + binding.cardView.y)
                        .streamFor(10, 300L)
                }
            }
            false
        }
    }

    override fun onClickCard(wordText: String) {

        tts.speak(wordText, TextToSpeech.QUEUE_FLUSH, null, "")
        viewModel.refresh()
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.KOREAN)
            tts.setSpeechRate(.5f)
            Log.i("taehee", "tts is $result")
        }
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }


}