package com.martin.myapplicationrecyclercarddrawer.listeners

import com.martin.myapplicationrecyclercarddrawer.models.Flight

interface RecyclerFlightListener {
    fun onClick (flight: Flight, position: Int)
    fun onDelete (flight: Flight, position: Int)
}