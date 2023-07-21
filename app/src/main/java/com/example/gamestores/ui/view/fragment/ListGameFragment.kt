package com.example.gamestores.ui.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.gamestores.R
import com.example.gamestores.data.models.Game
import com.example.gamestores.databinding.FragmentListGameBinding
import com.example.gamestores.ui.adapter.GameAdapter
import com.example.gamestores.ui.viewmodel.GameViewModel
import kotlinx.coroutines.launch

class ListGameFragment : Fragment() {
    private lateinit var gameAdapter: GameAdapter
    private val gameViewModel: GameViewModel by activityViewModels()
    private lateinit var binding: FragmentListGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameAdapter = GameAdapter(emptyList())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListGameBinding.inflate(inflater, container, false)
        val view = binding.root
        gameAdapter.setClickListener(object : GameAdapter.ClickListener {
            override fun onClick(quote: Game) {
                detailItem(quote)
            }
        })
        binding.recyclerView.adapter = gameAdapter
        binding.model = gameViewModel

        loadData()
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData() {
        gameViewModel.gameList.observe(viewLifecycleOwner) { quoteList ->
            gameAdapter.games = quoteList
            gameAdapter.notifyDataSetChanged()
        }
    }


    private fun detailItem(quote: Game) {
        gameViewModel.viewModelScope.launch {
            gameViewModel.detailGame(quote.id)
            findNavController().navigate(R.id.detailQuoteFragment)
        }
    }
}