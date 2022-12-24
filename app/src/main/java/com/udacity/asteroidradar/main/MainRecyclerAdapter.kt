package com.udacity.asteroidradar.main;

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.udacity.asteroidradar.datamodels.Asteroid
import com.udacity.asteroidradar.R


class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.MainHolder>() {

    private var astroidsList = mutableListOf<Asteroid>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.asteroid_item, parent, false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int {
        return astroidsList.size
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val astroids = astroidsList[position]
        holder.name.text = astroids.codename
        holder.closeapproachdate.text = astroids.closeApproachDate
        if(astroids.isPotentiallyHazardous){
            holder.dangerous.setImageResource(R.drawable.ic_status_potentially_hazardous)
        }
        else{
            holder.dangerous.setImageResource(R.drawable.ic_status_normal)
        }

        holder.selected_asteroid.setOnClickListener {
            val action = MainFragmentDirections.actionShowDetail(astroids)
            holder.itemView.findNavController().navigate(action)
        }

    }

    class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val closeapproachdate = itemView.findViewById<TextView>(R.id.closeapproachdate)
        val dangerous = itemView.findViewById<ImageView>(R.id.dangerous)
        val selected_asteroid = itemView.findViewById<CardView>(R.id.selected_asteroid)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setAsteroidDataFromApi(data:ArrayList<Asteroid>){
        this.astroidsList = data
        notifyDataSetChanged()
    }
}