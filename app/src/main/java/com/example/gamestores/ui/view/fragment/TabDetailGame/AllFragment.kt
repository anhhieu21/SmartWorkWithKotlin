package com.example.gamestores.ui.view.fragment.TabDetailGame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.gamestores.R
import com.example.gamestores.data.models.GameDetail
import com.example.gamestores.databinding.FragmentAllBinding
import com.example.gamestores.ui.adapter.CarouselAdapter
import com.example.gamestores.ui.viewmodel.GameViewModel


class AllFragment : Fragment() {
    private val gameViewModel: GameViewModel by activityViewModels()
    private lateinit var binding: FragmentAllBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAllBinding.inflate(inflater, container, false)
        gameViewModel.detail.observe(viewLifecycleOwner, Observer { result ->
            binding.model = result

        })
        return binding.root
    }

}