package com.taehee.wordcard.ui.card

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
import androidx.fragment.app.viewModels
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.FragmentCardBinding
import com.taehee.wordcard.ui.main.MainViewModel
import com.taehee.wordcard.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

@AndroidEntryPoint
class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: CardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
//
//        }
    }

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
        binding.viewModel = viewModel

        binding.root.setOnTouchListener { _, motionEvent -> onTouchView(motionEvent) }
        binding.cardView.setOnTouchListener { _, motionEvent -> onTouchView(motionEvent) }
        binding.cardView.setOnClickListener {
            if (viewModel.completeLoading.value == true) {
                viewModel.speak(binding.wordText.text.toString())
                mainViewModel.wordChange()
            }
        }
        mainViewModel.wordChanged.observe(viewLifecycleOwner, EventObserver {
            viewModel.getCard(binding.wordText.text.toString())
        })

//        if (savedInstanceState == null) {
//            activity?.onBackPressedDispatcher?.addCallback(object : OnBackPressedCallback(true){
//                override fun handleOnBackPressed() {
//                    activity?.finish()
//                }
//            })
//        }
    }

    private fun onTouchView(motionEvent: MotionEvent): Boolean {
        if (motionEvent.action == MotionEvent.ACTION_DOWN || motionEvent.action == MotionEvent.ACTION_MOVE) {
            binding.particle.build()
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
        return false
    }

}