package com.martin.myapplicationrecyclercarddrawer.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.myapplicationrecyclercarddrawer.R
import com.martin.myapplicationrecyclercarddrawer.adapters.FlightAdapter
import com.martin.myapplicationrecyclercarddrawer.extensionFunctions.toast
import com.martin.myapplicationrecyclercarddrawer.listeners.RecyclerFlightListener
import com.martin.myapplicationrecyclercarddrawer.models.Flight
import kotlinx.android.synthetic.main.fragment_departures.view.*

class DeparturesFragment : Fragment() {

    private val list: ArrayList<Flight> by lazy { //by lazy se ejecuta UNA VEZ, y prepara con el codigo a la variable
        getFlights()
    }

    private lateinit var recycler: RecyclerView
    private lateinit var adapter:FlightAdapter
    private val layoutManager by lazy { LinearLayoutManager (context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.departures_fragment_title)
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_departures, container, false)
        recycler = rootView.recyclerView as RecyclerView
        setRecyclerView()
        return rootView
    }

    private fun setRecyclerView() {
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = layoutManager
        adapter = (FlightAdapter(list, object : RecyclerFlightListener{
            override fun onClick(flight: Flight, position: Int) {
                activity?. toast("Let's go to ${flight.city}")
            }

            override fun onDelete(flight: Flight, position: Int) {
                list.remove(flight)
                adapter.notifyItemRemoved(position)
                activity?.toast("${flight.city} has been removed")
            }
        }))
        recycler.adapter = adapter
    }

    private fun getFlights(): ArrayList<Flight> {
        return object : ArrayList<Flight>() {
            init {
                add(Flight(1, "Seattle", R.drawable.seattle))
                add(Flight(2, "New York", R.drawable.new_york))
                add(Flight(3, "London", R.drawable.london))
                add(Flight(4, "Seville", R.drawable.seville))
                add(Flight(5, "Venice", R.drawable.venice))
                add(Flight(6, "Buenos Aires", R.drawable.buenosaires))
                add(Flight(7, "Athens", R.drawable.athens))
                add(Flight(8, "Toronto", R.drawable.toronto))
                add(Flight(9, "Dublin", R.drawable.dublin))
                add(Flight(10, "Caribbean", R.drawable.caribbean_sea))
            }
        }
    }

}
