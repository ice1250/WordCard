package com.taehee.wordcard.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.FragmentEditBinding
import com.taehee.wordcard.ui.custom.isScrollable
import com.taehee.wordcard.ui.custom.setStackFromEnd
import com.taehee.wordcard.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditFragment : Fragment() {

    private val viewModel: EditViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentEditBinding

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
            }
        }

        binding.recyclerView.apply {
            setHasFixedSize(true)

            adapter = EditRecyclerViewAdapter(
                onClick = {
                    viewModel.wordClicked(it.name)
                },
                onDeleteClick = {
                    viewModel.deleteWord(it)
                    mainViewModel.wordChange()
                }
            )
            viewTreeObserver.addOnScrollChangedListener { setStackFromEnd(isScrollable()) }
        }
    }

}