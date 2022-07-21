package com.taehee.wordcard.ui.card

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.FragmentCardBinding
import com.taehee.wordcard.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding

    private val sharedViewModel: MainViewModel by activityViewModels()
    private val viewModel: CardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) { requireActivity().finish() }
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
            sharedViewModel.speak(binding.wordText.text.toString())
            viewModel.fetchCard(binding.wordText.text.toString(), true)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.uiState.collect {
                    if (it.needRefreshCard) {
                        viewModel.fetchCard(isNeedDelay = false)
                        sharedViewModel.refreshFinished(card = true)
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    binding.cardView.isClickable = !it.isFetchingCard
                }
            }
        }
    }

    private fun onTouchView(motionEvent: MotionEvent): Boolean {
        if (motionEvent.action == MotionEvent.ACTION_DOWN || motionEvent.action == MotionEvent.ACTION_MOVE) {
            binding.particle.start(Party(
                angle = 10,
                size = listOf(Size.LARGE),
                position = Position.Absolute(motionEvent.rawX, motionEvent.rawY),
                emitter = Emitter(300, TimeUnit.MILLISECONDS).perSecond(100)
            ))
        }
        return false
    }

}