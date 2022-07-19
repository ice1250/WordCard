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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.FragmentGameBinding
import com.taehee.wordcard.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.core.Angle
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.Spread
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class GameFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()
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
            adapter = GameRecyclerViewAdapter(onClick = { game, view ->
                viewModel.select(game)
                val location = IntArray(2)
                view.getLocationOnScreen(location)

                binding.particle.start(Party(
                    size = listOf(Size.MEDIUM),
                    position = Position.Absolute(location[0].toFloat() + view.width / 2,
                        location[1].toFloat()),
                    emitter = Emitter(100, TimeUnit.MILLISECONDS).perSecond(200)
                ))
            })
            addItemDecoration(GameItemDecoration(4, 10))
        }

        binding.restartButton.setOnClickListener {
            binding.restartButton.visibility = View.GONE
            viewModel.reGame()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.uiState.collect {
                    if (it.needRefreshCard) {
                        viewModel.reGame()
                        sharedViewModel.gameRefreshFinished()
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    if (it.isGameWin) {
                        binding.particle.start(parade())
                    }
                }
            }
        }
    }

    private fun parade(): List<Party> {
        val party = Party(
            speed = 10f,
            maxSpeed = 30f,
            damping = 0.9f,
            angle = Angle.RIGHT - 45,
            spread = Spread.SMALL,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 1, TimeUnit.SECONDS).perSecond(100),
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