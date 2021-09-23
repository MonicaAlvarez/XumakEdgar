package com.xumak.edgar.wtabrba.character.view.callback

import com.xumak.edgar.wtabrba.character.model.service.ObjectResponse

interface OnItemSelectedListener {
    fun onItemSelected(character : ObjectResponse.Character)
    fun onFavoriteSelected(id : Int, name : String)
    fun onFavoriteUnselected(id: Int)
}