package com.example.starwars.ui.favourites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.DataManager
import com.example.starwars.INTENT_EXTRA_PERSON_INDEX
import com.example.starwars.PersonActivity
import com.example.starwars.R
import com.example.starwars.model.Person

class FavouritesFragment : Fragment() {

    private lateinit var favouritesViewModel: FavouritesViewModel
    private var root: View? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favouritesViewModel =
                ViewModelProvider(this).get(FavouritesViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_favourites, container, false)

        val textList: ListView? = root?.findViewById(R.id.listFavourites)

        textList?.adapter = this.context?.let { ArrayAdapter<Person>(it, android.R.layout.simple_list_item_1, DataManager.favourites.map { DataManager.persons.get(it) }) }

        textList?.setOnItemClickListener { adapterView, view, position, id ->
            System.out.println(position)
            val intent = Intent(this.context, PersonActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PERSON_INDEX, DataManager.favourites.get(position))
            startActivity(intent)
        }

        return root
    }
}