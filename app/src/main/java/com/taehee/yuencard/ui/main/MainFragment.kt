package com.taehee.yuencard.ui.main

import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.taehee.yuencard.R
import com.taehee.yuencard.databinding.MainFragmentBinding
import java.util.*

class MainFragment : Fragment(), MainHandler, TextToSpeech.OnInitListener {

    lateinit var tts: TextToSpeech;
    lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        tts = TextToSpeech(requireContext(), this)
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.handler = this

        viewModel.getColor().observe(viewLifecycleOwner,
            { binding.main.setBackgroundColor(Color.parseColor(it)) })
    }

    override fun onClickCard(wordText: String) {
        tts.speak(wordText, TextToSpeech.QUEUE_FLUSH, null, "")
        viewModel.refresh()
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.KOREAN)
            tts.setSpeechRate(.5f)
            Log.i("taehee", "tts is $result")
        }
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}