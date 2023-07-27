package com.example.gamestores.ui.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import com.example.gamestores.R
import com.example.gamestores.data.models.Game
import com.example.gamestores.databinding.FragmentListGameBinding
import com.example.gamestores.ui.adapter.GameAdapter
import com.example.gamestores.ui.adapter.TopChartAdapter
import com.example.gamestores.ui.viewmodel.GameViewModel
import kotlinx.coroutines.launch
import java.lang.Math.abs


class ListGameFragment : Fragment() {
    private lateinit var gameAdapter: GameAdapter
    private lateinit var topChartAdapter: TopChartAdapter

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
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.setItemViewCacheSize(15)
        loadData()
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData() {
        val viewpager = binding.topChart.viewpager
        gameViewModel.gameList.observe(viewLifecycleOwner) { quoteList ->
            gameAdapter.games = quoteList
            gameAdapter.notifyDataSetChanged()
            topChartAdapter = TopChartAdapter(quoteList)
            viewpager.adapter = topChartAdapter
            viewpager.offscreenPageLimit = 1
            // Add a PageTransformer that translates the next and previous items horizontally
// towards the center of the screen, which makes them visible
            val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
            val currentItemHorizontalMarginPx =
                resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                val postionPage = viewpager.currentItem
                if (postionPage != 0) {
                    page.translationX = -pageTranslationX * position

                }
            }
            viewpager.setPageTransformer(pageTransformer)
            // The ItemDecoration gives the current (centered) item horizontal margin so that
// it doesn't occupy the whole screen width. Without it the items overlap
            val itemDecoration = HorizontalMarginItemDecoration(
                requireActivity().applicationContext,
                R.dimen.viewpager_current_item_horizontal_margin
            )
            viewpager.addItemDecoration(itemDecoration)
        }
    }


    private fun detailItem(quote: Game) {
        gameViewModel.viewModelScope.launch {
            gameViewModel.detailGame(quote.id)
            findNavController().navigate(R.id.detailQuoteFragment)
        }
    }
}

/**
 * Adds margin to the left and right sides of the RecyclerView item.
 * Adapted from https://stackoverflow.com/a/27664023/4034572
 * @param horizontalMarginInDp the margin resource, in dp.
 */
class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int) :
    RecyclerView.ItemDecoration() {

    private val horizontalMarginInPx: Int =
        context.resources.getDimension(horizontalMarginInDp).toInt()

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.right = horizontalMarginInPx
        outRect.left = horizontalMarginInPx
    }

}