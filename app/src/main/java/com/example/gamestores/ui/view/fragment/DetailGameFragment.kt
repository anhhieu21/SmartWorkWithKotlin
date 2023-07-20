package com.example.gamestores.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.gamestores.databinding.FragmentDetailGameBinding
import com.example.gamestores.ui.viewmodel.GameViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailGameFragment : Fragment() {

    private lateinit var binding: FragmentDetailGameBinding
    private lateinit var gameViewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailGameBinding.inflate(inflater, container, false)
        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        loadData()
        return binding.root
    }

    private fun  loadData(){
        gameViewModel.detail.observe(viewLifecycleOwner) { result ->
            Toast.makeText(activity,result.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}