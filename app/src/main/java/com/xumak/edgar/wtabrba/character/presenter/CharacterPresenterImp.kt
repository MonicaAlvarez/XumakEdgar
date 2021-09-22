package com.xumak.edgar.wtabrba.character.presenter

import com.xumak.edgar.wtabrba.character.CharacterMVP
import com.xumak.edgar.wtabrba.character.model.service.ObjectResponse
import com.xumak.edgar.wtabrba.character.model.service.OnCharacterServiceResponse

class CharacterPresenterImp(val model: CharacterMVP.Model) : CharacterMVP.Presenter,
    OnCharacterServiceResponse {
    private lateinit var view: CharacterMVP.View

    override fun setView(view: CharacterMVP.View) {
       this.view = view
        model.setOnGetCharacterResponse(this)
    }

    override fun getCharacters() {
        model.requestCharacters(15)
    }

    override fun onResponse(charactersList: List<ObjectResponse.Character>) {
        view.updateCharactersList(charactersList)
    }

    override fun onError(error: String) {
       view.showGetCharactersError(error)
    }
}