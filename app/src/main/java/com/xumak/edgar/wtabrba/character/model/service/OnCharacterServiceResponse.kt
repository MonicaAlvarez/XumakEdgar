package com.xumak.edgar.wtabrba.character.model.service

interface OnCharacterServiceResponse {
    fun onResponse(charactersList: List<ObjectResponse.Character>)
    fun onError(error : String)
}