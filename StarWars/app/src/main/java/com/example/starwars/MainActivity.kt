package com.example.starwars

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.starwars.ApiUtil.PEOPLE_ENDPOINT
import com.example.starwars.model.Person
import com.example.starwars.model.PersonsResponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        Thread {
            var count: Int?
            var nextUrl: String? = PEOPLE_ENDPOINT
            do {
                val personsResponse = nextUrl?.let { ApiUtil.fetchPersons<PersonsResponse>(it) }
                count = personsResponse?.count
                nextUrl = personsResponse?.nextUrl
                if (personsResponse != null) {
                    DataManager.persons.addAll(personsResponse.persons)
                }
            } while (count != null && nextUrl != null && DataManager.persons.count() < count)

            val textList: ListView = findViewById(R.id.listPersons)
            runOnUiThread {
                (textList.adapter as ArrayAdapter<Person>?)?.notifyDataSetChanged()
            }
            val pos = textList.lastVisiblePosition
        }.start()
    }
}