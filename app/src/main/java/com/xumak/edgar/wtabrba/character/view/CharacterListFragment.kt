package com.xumak.edgar.wtabrba.character.view

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xumak.edgar.wtabrba.R

import com.xumak.edgar.wtabrba.character.CharacterMVP
import com.xumak.edgar.wtabrba.character.model.service.ObjectResponse
import com.xumak.edgar.wtabrba.character.view.callback.OnItemSelectedListener
import kotlinx.android.synthetic.main.character_list_fragment.*


class CharacterListFragment(var listener: OnItemSelectedListener, val presenter: CharacterMVP.Presenter) : Fragment(){

    private lateinit var adapter : CharacterAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.character_list_fragment, container, false)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        presenter.getCharacters()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvResponseList.layoutManager = LinearLayoutManager(activity)
        rvResponseList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL))

        val list : List<ObjectResponse.Character> = listOf()
        adapter = CharacterAdapter(list, listener)
        rvResponseList.adapter = adapter
    }

    fun updateCharactersList(characterList : List<ObjectResponse.Character>){
        adapter.updateCharacterList(characterList)
    }
}