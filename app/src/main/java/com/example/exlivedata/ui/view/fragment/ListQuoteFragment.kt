package com.example.exlivedata.ui.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.exlivedata.R
import com.example.exlivedata.data.models.Result
import com.example.exlivedata.databinding.FragmentListQuoteBinding
import com.example.exlivedata.ui.adapter.QuoteAdapter
import com.example.exlivedata.ui.viewmodel.QuoteViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListQuoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListQuoteFragment : Fragment() {
    private lateinit var quoteAdapter: QuoteAdapter
    private lateinit var quoteViewModel: QuoteViewModel
    private lateinit var binding: FragmentListQuoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//
//        }
        quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        quoteAdapter = QuoteAdapter(emptyList())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListQuoteBinding.inflate(inflater, container, false)
        val view = binding.root
        quoteAdapter.setClickListener(object : QuoteAdapter.ClickListener {
            override fun onClick(quote: Result) {
                removeItem(view)
            }
        })
        binding.recyclerView.adapter = quoteAdapter
        binding.model = quoteViewModel

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
            ListQuoteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData() {
        quoteViewModel.quoteList.observe(viewLifecycleOwner) { quoteList ->
            quoteAdapter.quoteList = quoteList
            quoteAdapter.notifyDataSetChanged()
        }
        quoteViewModel.fetchQuotes()

    }


    private fun removeItem(view:View) {
//        quoteViewModel.removeQuote(quote)
//        quoteViewModel.detailQuote(quote)
//        val fragment = ListQuoteFragment()
//         FragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment).commit()
view.findNavController().navigate(R.id.detailQuoteFragment)
    }
}