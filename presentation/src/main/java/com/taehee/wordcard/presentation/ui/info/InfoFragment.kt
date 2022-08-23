package com.taehee.wordcard.presentation.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.taehee.wordcard.presentation.R
import com.taehee.wordcard.presentation.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {

    private val viewModel: InfoViewModel by viewModels()
    private lateinit var binding: FragmentInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) { requireActivity().finish() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        with(binding.recyclerView) {
            setHasFixedSize(true)
            adapter = InfoRecyclerViewAdapter {
                val action =
                    InfoFragmentDirections.actionInfoFragmentToInfoDetailFragment(it.html_url)
                findNavController().navigate(action)
            }
            addItemDecoration(InfoItemDecoration(4))
        }
        viewModel.getGithubRepositories("ice1250")
    }
}