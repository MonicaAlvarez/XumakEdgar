package com.xumak.edgar.wtabrba.character.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FavoriteCharacterDB : RealmObject() {
    @PrimaryKey
    var id: Int? = null
    var name: String? = null
}