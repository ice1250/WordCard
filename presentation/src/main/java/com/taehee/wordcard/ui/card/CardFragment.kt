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
import androidx.lifecycle.Observer
import com.taehee.domain.model.Card
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.FragmentCardBinding
import com.taehee.wordcard.ui.main.MainViewModel
import com.taehee.wordcard.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding

    private val mainViewModel: MainViewModel by activityViewModels()
    private val cardViewModel: CardViewModel by viewModels()

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
        binding.viewModel = cardViewModel

        binding.root.setOnTouchListener { _, motionEvent -> onTouchView(motionEvent) }
        binding.cardView.setOnTouchListener { _, motionEvent -> onTouchView(motionEvent) }
        binding.cardView.setOnClickListener {
            if (cardViewModel.completeLoading.value == true) {
                cardViewModel.speak(binding.wordText.text.toString())
                cardViewModel.getCard(binding.wordText.text.toString(), true)
            }
        }
        mainViewModel.wordChanged.observe(viewLifecycleOwner, EventObserver {
            cardViewModel.getCard(binding.wordText.text.toString(), false)
        })
        cardViewModel.card.observe(viewLifecycleOwner, object : Observer<Card> {
            override fun onChanged(t: Card?) {
                mainViewModel.initComplete()
                cardViewModel.card.removeObserver(this)
            }
        })
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