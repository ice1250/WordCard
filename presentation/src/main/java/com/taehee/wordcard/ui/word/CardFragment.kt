package com.taehee.wordcard.ui.word

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.FragmentCardBinding
import com.taehee.wordcard.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

@AndroidEntryPoint
class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding

    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = sharedViewModel

        binding.root.setOnTouchListener { _, motionEvent -> onTouchView(motionEvent) }
        binding.cardView.setOnTouchListener { _, motionEvent -> onTouchView(motionEvent) }
        binding.cardView.setOnClickListener { sharedViewModel.speakTts(binding.wordText.text.toString(), true) }
        subscribeCard()
    }

    private fun subscribeCard() {
        sharedViewModel.card.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.main.setBackgroundColor(Color.parseColor(it.color))
            } else {
                binding.main.setBackgroundColor(Color.parseColor("#ffffff"))
            }
        }
    }

    private fun onTouchView(motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                with(binding) {
                    particle.build()
                        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(100L)
                        .addShapes(Shape.Square, Shape.Circle)
                        .addSizes(Size(12))
                        .setPosition(motionEvent.rawX, motionEvent.rawY)
                        .streamFor(12, 300L)
                }
            }
        }
        return false
    }

}