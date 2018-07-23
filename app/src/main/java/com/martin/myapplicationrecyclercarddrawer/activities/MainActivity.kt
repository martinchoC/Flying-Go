package com.martin.myapplicationrecyclercarddrawer.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.TextView
import com.martin.myapplicationrecyclercarddrawer.R
import com.martin.myapplicationrecyclercarddrawer.extensionFunctions.toast
import com.martin.myapplicationrecyclercarddrawer.fragments.ArrivalsFragment
import com.martin.myapplicationrecyclercarddrawer.fragments.DeparturesFragment
import com.martin.myapplicationrecyclercarddrawer.fragments.HomeFragment
import com.martin.mylibrary.ToolbarActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarToLoad(toolbar as Toolbar)

        setNavDrawer()
        setUserHeaderInformation()

        //save state
        if(savedInstanceState == null){
            fragmentTransaction(HomeFragment())
            navView.menu.getItem(0).isChecked = true
        }

    }

    //button for hamburguer menu
    private fun setNavDrawer() {
        var toggle = ActionBarDrawerToggle(this, drawerLayout, _toolbar, R.string.open_drawer, R.string.close_drawer)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener (this)
    }

    private fun fragmentTransaction (fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }

    private fun loadFragmentById (id: Int) {
        when (id) {
            R.id.nav_home -> fragmentTransaction(HomeFragment())
            R.id.nav_departures -> fragmentTransaction(DeparturesFragment())
            R.id.nav_arrivals -> fragmentTransaction(ArrivalsFragment())
        }
    }

    private fun showMessageNavItemSelectedById (id: Int){
        when (id) {
            R.id.nav_profile -> toast("Profile")
            R.id.nav_settings -> toast("Settings")
        }
    }

    private fun setUserHeaderInformation() {
        val name = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewName)
        val email = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewEmail)

        //check name is not null
        name?.let { name.text = getString(R.string.user_name) }
        //check email is not null
        email?.let { email.text = getString(R.string.user_email) }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        showMessageNavItemSelectedById(item.itemId)
        loadFragmentById(item.itemId)
        //close hamburguer
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }

}
