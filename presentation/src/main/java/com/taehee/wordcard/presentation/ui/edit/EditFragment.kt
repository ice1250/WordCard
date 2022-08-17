package com.taehee.wordcard.presentation.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.taehee.presentation.R
import com.taehee.presentation.databinding.FragmentEditBinding
import com.taehee.wordcard.presentation.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedkeyboardobserver.TedKeyboardObserver

@AndroidEntryPoint
class EditFragment : Fragment() {

    private val viewModel: EditViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) { requireActivity().finish() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.sharedViewModel = mainViewModel

        binding.recyclerView.apply {
            TedKeyboardObserver(requireActivity()).listen {
                if (it) {
                    adapter?.itemCount.also { count ->
                        if (count != null && count > 0) {
                            smoothScrollToPosition(count - 1)
                        }
                    }
                }
            }
        }

    }

}