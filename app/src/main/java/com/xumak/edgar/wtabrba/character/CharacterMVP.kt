package com.xumak.edgar.wtabrba.character

import android.content.Context
import com.xumak.edgar.wtabrba.character.model.service.ObjectResponse
import com.xumak.edgar.wtabrba.character.model.service.OnCharacterServiceResponse

interface CharacterMVP {
    interface View{
        fun updateCharactersList(charactersList: List<ObjectResponse.Character>)
        fun showGetCharactersError(error : String)
        fun noInternetConnection()
    }

    interface Presenter{
        fun setView(view: View, context: Context)
        fun getCharacters()

        fun storeFavoriteCharacter(id : Int, name : String)
        fun deleteFavoriteCharacter(id : Int)
        fun getAllFavoritesCharacters(charactersList: List<ObjectResponse.Character>)
        fun closeStore()

        fun searchCharacter(valueToSearch : String)
    }

    interface Model{
        fun requestCharacters(limit : Int = 0, toSearch : String = "")
        fun setOnGetCharacterResponse(callback : OnCharacterServiceResponse)
    }
}