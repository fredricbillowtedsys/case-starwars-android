package com.example.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.starwars.model.Person

class PersonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        val personIndex = intent.extras?.getInt(INTENT_EXTRA_PERSON_INDEX)
        if (personIndex != null) {
            val person = DataManager.persons[personIndex]
            findViewById<TextView>(R.id.lblName).text = person.name
            findViewById<TextView>(R.id.lblHeight).text = person.height
            findViewById<TextView>(R.id.lblMass).text = person.mass
            findViewById<TextView>(R.id.lblHairColor).text = person.hairColor
            findViewById<TextView>(R.id.lblSkinColor).text = person.skinColor
            findViewById<TextView>(R.id.lblEyeColor).text = person.eyeColor
            findViewById<TextView>(R.id.lblBirthYear).text = person.birthYear
            findViewById<TextView>(R.id.lblGender).text = person.gender

            val button = findViewById<Button>(R.id.btnAddRemoveFavourite)
            if (DataManager.favourites.contains(personIndex)) {
                button.text = "Remove from favourites"
            } else {
                button.text = "Add to favourites"
            }

            button.setOnClickListener { view ->
                if (DataManager.favourites.contains(personIndex)) {
                    DataManager.favourites.remove(personIndex)
                } else {
                    DataManager.favourites.add(personIndex)
                }
            }
        }
    }
}