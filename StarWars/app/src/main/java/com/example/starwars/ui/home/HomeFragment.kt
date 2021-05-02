package com.example.starwars.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.DataManager
import com.example.starwars.INTENT_EXTRA_PERSON_INDEX
import com.example.starwars.PersonActivity
import com.example.starwars.R
import com.example.starwars.model.Person

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
/*
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
*/
        val textList: ListView = root.findViewById(R.id.listPersons)
        textList.adapter = this.context?.let { ArrayAdapter<Person>(it, android.R.layout.simple_list_item_1, DataManager.persons) }

        textList.setOnItemClickListener { adapterView, view, position, id ->
            System.out.println(position)
            val intent = Intent(this.context, PersonActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PERSON_INDEX, position)
            startActivity(intent)
        }

        return root
    }
}

