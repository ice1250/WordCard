package com.taehee.wordcard.ui.info.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.taehee.wordcard.R
import com.taehee.wordcard.databinding.FragmentInfoDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoDetailFragment : Fragment() {

    private lateinit var binding: FragmentInfoDetailBinding
    val args: InfoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = args.url
        binding.webView.setBackgroundColor(Color.TRANSPARENT)
        binding.webView.loadUrl(url)
    }
}