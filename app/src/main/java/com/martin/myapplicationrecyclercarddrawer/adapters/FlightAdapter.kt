package com.martin.myapplicationrecyclercarddrawer.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.martin.myapplicationrecyclercarddrawer.R
import com.martin.myapplicationrecyclercarddrawer.extensionFunctions.inflate
import com.martin.myapplicationrecyclercarddrawer.extensionFunctions.loadByResource
import com.martin.myapplicationrecyclercarddrawer.listeners.RecyclerFlightListener
import com.martin.myapplicationrecyclercarddrawer.models.Flight
import kotlinx.android.synthetic.main.recycler_flight.view.*

class FlightAdapter (private val flights: List<Flight>, private val listener: RecyclerFlightListener): RecyclerView.Adapter<FlightAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder((parent.inflate(R.layout.recycler_flight)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(flights[position], listener)

    override fun getItemCount() = flights.size

    class ViewHolder (itemView: View): RecyclerView.ViewHolder (itemView){

        fun bind (flight: Flight, listener: RecyclerFlightListener) = with(itemView) {
            //al poner with(itemview) me ahorro de poner itemView. y lo que sigue (textViewCity...)
            textViewCityName.text = flight.city
            imageViewBackground.loadByResource(flight.imgResource)

            //click events
            setOnClickListener {listener.onClick(flight, adapterPosition)}
            buttonDelete.setOnClickListener {listener.onDelete(flight, adapterPosition)}
        }
    }
}