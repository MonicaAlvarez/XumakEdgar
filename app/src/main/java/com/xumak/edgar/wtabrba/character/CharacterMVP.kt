package com.xumak.edgar.wtabrba.character

import com.xumak.edgar.wtabrba.character.model.service.ObjectResponse
import com.xumak.edgar.wtabrba.character.model.service.OnCharacterServiceResponse

interface CharacterMVP {
    interface View{
        fun updateCharactersList(charactersList: List<ObjectResponse.Character>)
        fun showGetCharactersError(error : String)
    }

    interface Presenter{
        fun setView(view: View)
        fun getCharacters()
    }

    interface Model{
        fun requestCharacters(limit : Int)
        fun setOnGetCharacterResponse(callback : OnCharacterServiceResponse)
    }
}