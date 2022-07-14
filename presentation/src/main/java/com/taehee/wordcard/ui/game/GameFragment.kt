package com.taehee.wordcard.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.FragmentGameBinding
import com.taehee.wordcard.ui.main.MainViewModel
import com.taehee.wordcard.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import nl.dionsegijn.konfetti.core.Angle
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.Spread
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class GameFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: GameViewModel by viewModels()
    private lateinit var binding: FragmentGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) { requireActivity().finish() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        with(binding.recyclerView) {
            setHasFixedSize(true)
            adapter = GameRecyclerViewAdapter {
                if (viewModel.completeLoading.value == true) viewModel.select(it)
            }
            addItemDecoration(GameItemDecoration(4, 10))
        }

        binding.restartButton.setOnClickListener {
            binding.restartButton.visibility = View.GONE
            viewModel.reGame()
        }

        mainViewModel.wordChanged.observe(viewLifecycleOwner,
            EventObserver { viewModel.reGame() })

        viewModel.gameComplete.observe(viewLifecycleOwner) { if (it) binding.particle.start(parade()) }
    }

    private fun parade(): List<Party> {
        val party = Party(
            speed = 10f,
            maxSpeed = 30f,
            damping = 0.9f,
            angle = Angle.RIGHT - 45,
            spread = Spread.SMALL,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 5, TimeUnit.SECONDS).perSecond(100),
            position = Position.Relative(0.0, 0.5)
        )

        return listOf(
            party,
            party.copy(
                angle = party.angle - 90, // flip angle from right to left
                position = Position.Relative(1.0, 0.5)
            ),
        )
    }


}