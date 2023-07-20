package com.example.gamestores.ui.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gamestores.R
import com.example.gamestores.data.models.Game
import com.example.gamestores.databinding.FragmentListGameBinding
import com.example.gamestores.ui.adapter.GameAdapter
import com.example.gamestores.ui.viewmodel.GameViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListGameFragment : Fragment() {
    private lateinit var gameAdapter: GameAdapter
    private lateinit var gameViewModel: GameViewModel
    private lateinit var binding: FragmentListGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//
//        }
        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailQuoteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListGameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData() {
        gameViewModel.gameList.observe(viewLifecycleOwner) { quoteList ->
            gameAdapter.games = quoteList
            gameAdapter.notifyDataSetChanged()
        }
        gameViewModel.fetchQuotes()

    }


    private fun detailItem(quote: Game) {
//        quoteViewModel.removeQuote(quote)
        gameViewModel.detailQuote(quote)
        findNavController().navigate(R.id.detailQuoteFragment)
    }
}