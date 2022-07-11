package com.taehee.wordcard.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.FragmentGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {

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

        viewModel.gameComplete.observe(viewLifecycleOwner) {
            if (it) {
                binding.restartButton.visibility = View.VISIBLE
            } else {
                binding.restartButton.visibility = View.GONE
            }
        }

        binding.restartButton.setOnClickListener {
            binding.restartButton.visibility = View.GONE
            viewModel.loadGames()
        }
    }

}