package com.example.gamestores.ui.view.fragment

import android.R.attr.fragment
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.gamestores.R
import com.example.gamestores.databinding.FragmentDetailGameBinding
import com.example.gamestores.ui.adapter.CarouselAdapter
import com.example.gamestores.ui.adapter.TabAdapter
import com.example.gamestores.ui.view.fragment.TabDetailGame.AllFragment
import com.example.gamestores.ui.viewmodel.GameViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Timer
import java.util.TimerTask


class DetailGameFragment : Fragment() {
    private lateinit var carouselAdapter: CarouselAdapter
    private var timer: Timer? = null
    private lateinit var binding: FragmentDetailGameBinding
    private val gameViewModel: GameViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailGameBinding.inflate(inflater, container, false)
        setUpUi()
        loadData()
        return binding.root
    }

    private fun setUpUi() {
        toolBar()
        binding.buttonModel = gameViewModel
        gameViewModel.btnWantViewModel.iconSrc = drawableFromSrc(R.drawable.icon_star_24)
        gameViewModel.btnPlayedViewModel.iconSrc = drawableFromSrc(R.drawable.icon_esports_24)
        gameViewModel.btnAddGameListViewModel.iconSrc = drawableFromSrc(R.drawable.icon_add_24)
        gameViewModel.btnShareViewModel.iconSrc = drawableFromSrc(R.drawable.icon_share_24)

        // set tab item for tab layout
        val adapter = TabAdapter(this)
        val tabLayout = binding.root.findViewById<TabLayout>(R.id.tab_layout)
        val tabPager = binding.root.findViewById<ViewPager2>(R.id.tab_pager)
        tabPager.adapter = adapter

        TabLayoutMediator(tabLayout, tabPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.description)
                1 -> getString(R.string.reviews)
                2 -> getString(R.string.gameplay)
                3 -> getString(R.string.news)
                4 -> getString(R.string.guide)
                5 -> getString(R.string.discussion)
                else -> {
                    getString(R.string.description)
                }
            }
        }.attach()
    }

    private fun toolBar(){
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun loadData() {
        gameViewModel.detail.observe(viewLifecycleOwner, Observer { result ->
            binding.model = result
            val listImage = result.screenshots.map { it.image }
            carouselAdapter = CarouselAdapter(listImage)
            binding.viewPager.adapter = carouselAdapter
            autoPayCarousel(listImage)
        })

    }

    private fun drawableFromSrc(src: Int): Drawable? {
        return ResourcesCompat.getDrawable(resources, src, null)
    }

    private fun autoPayCarousel(listImage: List<String>) {
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                binding.viewPager.post {
                    val nextItem = (binding.viewPager.currentItem + 1) % listImage.size
                    binding.viewPager.setCurrentItem(nextItem, true)
                }
            }

        }, DELAY_MS, PERIOD_MS)
    }

    companion object {
        private const val DELAY_MS: Long =
            4000 // Delay before first auto scroll (2 seconds in this example)
        private const val PERIOD_MS: Long =
            6000 // Time between auto scrolls (4 seconds in this example)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        stopAutoScroll()
    }

    private fun stopAutoScroll() {
        // Check if the Timer is running and cancel it
        timer?.cancel()
        timer = null
    }
}