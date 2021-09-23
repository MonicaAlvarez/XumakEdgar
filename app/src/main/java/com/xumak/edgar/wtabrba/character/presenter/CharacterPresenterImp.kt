package com.xumak.edgar.wtabrba.character.presenter

import android.content.Context
import android.net.ConnectivityManager
import com.xumak.edgar.wtabrba.character.CharacterMVP
import com.xumak.edgar.wtabrba.character.model.service.ObjectResponse
import com.xumak.edgar.wtabrba.character.model.service.OnCharacterServiceResponse

class CharacterPresenterImp(val model: CharacterMVP.Model) : CharacterMVP.Presenter,
    OnCharacterServiceResponse {
    private lateinit var view: CharacterMVP.View
    private lateinit var storeCharacter : CharacterRealmManager
    private val limit = 15
    private lateinit var context : Context

    override fun setView(view: CharacterMVP.View, context: Context) {
        this.view = view
        this.context = context
        model.setOnGetCharacterResponse(this)
        storeCharacter = CharacterRealmManager()
    }

    override fun getCharacters() {
        if(!inNetworkAvailable()){
            view.noInternetConnection()
            return
        }
        model.requestCharacters(limit)
    }

    override fun storeFavoriteCharacter(id: Int, name: String) {
        storeCharacter.storeFavoriteCharacter(id, name)
    }

    override fun deleteFavoriteCharacter(id: Int) {
        storeCharacter.deleteFavoriteCharacter(id)
    }

    override fun getAllFavoritesCharacters(charactersList: List<ObjectResponse.Character>) {
      storeCharacter.returnAllFavoritesCharacters()?.forEach { favorite ->
          charactersList.find {
              it.char_id == favorite.id
          }?.isSelected = true
      }
        view.updateCharactersList(charactersList)
    }

    override fun searchCharacter(valueToSearch: String) {
        if(!inNetworkAvailable()){
            view.noInternetConnection()
            return
        }
        model.requestCharacters(limit, valueToSearch)
    }

    override fun closeStore() {
        storeCharacter.closeRealm()
    }

    override fun onResponse(charactersList: List<ObjectResponse.Character>) {
        getAllFavoritesCharacters(charactersList)
    }

    override fun onError(error: String) {
       view.showGetCharactersError(error)
    }


    fun inNetworkAvailable() : Boolean{
        val manager  = (this.context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val networkInfo = manager.getActiveNetworkInfo()
        if(networkInfo != null) {
            when (networkInfo.type){
                ConnectivityManager.TYPE_MOBILE ->
                    return true
                ConnectivityManager.TYPE_WIFI ->
                    return true
                else -> return false
            }
        }
        return false
    }
}