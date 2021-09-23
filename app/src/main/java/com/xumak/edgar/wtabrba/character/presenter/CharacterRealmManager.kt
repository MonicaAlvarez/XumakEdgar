package com.xumak.edgar.wtabrba.character.presenter

import com.xumak.edgar.wtabrba.character.model.FavoriteCharacterDB
import io.realm.Realm

class CharacterRealmManager {
    private var realm: Realm

    init {
        realm = Realm.getDefaultInstance()
    }

    fun closeRealm(){
        realm.close()
    }

    fun storeFavoriteCharacter(id : Int , characterName: String) {
        realm.executeTransactionAsync { realm ->
            val place: FavoriteCharacterDB = realm.createObject(FavoriteCharacterDB::class.java, id)
            place.name = characterName
        }
    }

    fun returnAllFavoritesCharacters(): List<FavoriteCharacterDB>? {
        realm.where(FavoriteCharacterDB::class.java).findAll()
        return realm.copyFromRealm(realm.where(FavoriteCharacterDB::class.java).findAll())
    }

    fun deleteFavoriteCharacter(id: Int) {
        realm.executeTransactionAsync { realm -> //obteniendo el place que coincida con el id
            val placeToDelete: FavoriteCharacterDB? =
                realm.where(FavoriteCharacterDB::class.java).equalTo("id", id)
                    .findFirst()
            placeToDelete?.deleteFromRealm()
        }
    }
}