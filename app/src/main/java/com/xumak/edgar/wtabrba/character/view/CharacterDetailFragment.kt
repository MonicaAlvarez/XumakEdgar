package com.xumak.edgar.wtabrba.character.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.xumak.edgar.wtabrba.R
import com.xumak.edgar.wtabrba.character.model.service.ObjectResponse
import com.xumak.edgar.wtabrba.character.view.callback.OnItemSelectedListener
import kotlinx.android.synthetic.main.character_detail_fragment.*

class CharacterDetailFragment(val character : ObjectResponse.Character, val listener : OnItemSelectedListener) : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = character.name
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.character_detail_fragment, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtNickname.text = character.nickname
        val path = character.img?: "" +R.mipmap.ic_launcher
        Picasso.get().load(path)
            .into(ivCharacterThumbnail)

        txtOccupation.text = character.occupation.joinToString(",")
        txtStatus.text = character.status
        txtPortrayed.text = character.portrayed

        ivLikeDetail.setImageResource(if (character.isSelected) R.drawable.ic_favorite else R.drawable.ic_favorite_empty)

        ivLikeDetail.setOnClickListener {

            if (character.isSelected){
                character.isSelected = false
                listener.onFavoriteUnselected(character.char_id)
            }else{
                character.isSelected = true
                listener.onFavoriteSelected(character.char_id, character.name)
            }

            ivLikeDetail.setImageResource(if (character.isSelected) R.drawable.ic_favorite else R.drawable.ic_favorite_empty)
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home ->{
                activity?.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}