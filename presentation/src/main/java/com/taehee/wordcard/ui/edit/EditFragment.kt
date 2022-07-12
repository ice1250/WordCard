package com.taehee.wordcard.ui.edit

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
import com.taehee.wordcard.databinding.FragmentEditBinding
import com.taehee.wordcard.ui.main.MainViewModel
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
        binding.button.setOnClickListener {
            if (binding.editText.text.isNotEmpty()) {
                viewModel.addWord(binding.editText.text.toString())
                binding.editText.text.clear()
                mainViewModel.wordChange()
                binding.recyclerView.postDelayed({
                    binding.recyclerView.smoothScrollToPosition(binding.recyclerView.adapter!!.itemCount)
                }, 100)

            }
        }
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = EditRecyclerViewAdapter(
                { viewModel.wordClicked(it.name) },
                {
                    viewModel.deleteWord(it)
                    mainViewModel.wordChange()
                }
            )
            addItemDecoration(EditItemDecoration(4))
            TedKeyboardObserver(requireActivity()).listen {
                if (it) {
                    smoothScrollToPosition(adapter!!.itemCount - 1)
                }
            }
        }
    }

}