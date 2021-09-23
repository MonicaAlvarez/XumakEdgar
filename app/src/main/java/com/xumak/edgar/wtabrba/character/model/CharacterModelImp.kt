package com.xumak.edgar.wtabrba.character.model

import com.xumak.edgar.wtabrba.character.CharacterMVP
import com.xumak.edgar.wtabrba.character.model.service.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterModelImp : CharacterMVP.Model {
    init {
        DaggerServiceComponent.builder()
            .serviceModule(ServiceModule())
            .build()
            .inject(this)
    }
    @Inject
    lateinit var service : ApiService
    private lateinit var callback : OnCharacterServiceResponse

    override fun requestCharacters(limit: Int, toSearch : String) {
        val options: MutableMap<String, String> = HashMap()
        options["limit"] = limit.toString()
        if (!toSearch.isEmpty()){
            options["name"] = toSearch
        }
        service.requestChacter(options).enqueue(object : Callback<List<ObjectResponse.Character>> {
            override fun onResponse(call: Call<List<ObjectResponse.Character>>, response: Response<List<ObjectResponse.Character>>) {
                if (response.errorBody() == null){
                    response.body()?.let {
                        callback.onResponse(it)
                    }?:{
                        callback.onError("No items to show")
                    }
                }else{
                    callback.onError(response.message())
                }
            }

            override fun onFailure(call: Call<List<ObjectResponse.Character>>, t: Throwable) {
                callback.onError(t.localizedMessage)
            }
        })
    }

    override fun setOnGetCharacterResponse(callback : OnCharacterServiceResponse) {
        this.callback = callback
    }
}