package com.taehee.yuencard.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.taehee.domain.model.Word
import com.taehee.yuencard.R
import com.taehee.yuencard.databinding.FragmentEditBinding
import com.taehee.yuencard.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditFragment : Fragment(), OnEditClickListener {

    private val viewModel: EditViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
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
        binding.callback = this
        binding.viewModel = viewModel
    }

    override fun onInsertClick(text: String) {
        if (text.isNotEmpty()) {
            viewModel.addWord(text)
            binding.editText.text.clear()
            sharedViewModel.dataChanged()
        }
    }

    override fun onDeleteClick(word: Word) {
        viewModel.deleteWord(word)
        sharedViewModel.dataChanged()
    }

    override fun onClick(word: Word) {
        sharedViewModel.speakTts(word.name)
    }
}