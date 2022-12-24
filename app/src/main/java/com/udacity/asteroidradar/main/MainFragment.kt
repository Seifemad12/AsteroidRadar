package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.asteroidradar.datamodels.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment() {


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    lateinit var recyclerAdapter: MainRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel


        recyclerAdapter = MainRecyclerAdapter()
        binding.asteroidRecycler.adapter = recyclerAdapter
        binding.asteroidRecycler.layoutManager = LinearLayoutManager(requireContext())

        allWeekAsteroidsDisplay()

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun allAsteroidsDisplay() {
        viewModel.asteroidDatabase.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.setAsteroidDataFromApi(it as ArrayList<Asteroid>)
        })
    }

    private fun allWeekAsteroidsDisplay() {
        viewModel.allWeekAsteroid.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.setAsteroidDataFromApi(it as ArrayList<Asteroid>)
        })
    }

    private fun allTodayAsteroidsDisplay() {
        viewModel.todayAsteroid.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.setAsteroidDataFromApi(it as ArrayList<Asteroid>)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_rent_menu -> allTodayAsteroidsDisplay()
            R.id.show_buy_menu -> allAsteroidsDisplay()
            R.id.show_all_menu -> allWeekAsteroidsDisplay()
        }
        return true
    }
}
