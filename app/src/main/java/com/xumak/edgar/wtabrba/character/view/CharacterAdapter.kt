package com.xumak.edgar.wtabrba.character.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.xumak.edgar.wtabrba.R
import com.xumak.edgar.wtabrba.character.model.service.ObjectResponse
import com.xumak.edgar.wtabrba.character.view.callback.OnItemSelectedListener

class CharacterAdapter(var responseList :List<ObjectResponse.Character>, val listener: OnItemSelectedListener) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        return CharacterViewHolder(v)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.txtName.text = responseList.get(position).name
        holder.txtNickname.text = responseList.get(position).nickname
        val path = responseList.get(position).img

        Picasso.get().load(path)
            .into(holder.imgThumbnail)

        holder.crdContainer.setOnClickListener {
            listener.onItemSelected(responseList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    fun updateCharacterList(characterList :List<ObjectResponse.Character>){
        responseList = characterList
        notifyDataSetChanged()
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val crdContainer = itemView.findViewById<CardView>(R.id.crdContainer)
        val txtName = itemView.findViewById<TextView>(R.id.txtName)
        val txtNickname = itemView.findViewById<TextView>(R.id.txtNickname)
        val imgThumbnail = itemView.findViewById<ImageView>(R.id.imgThumbnail)
    }
}