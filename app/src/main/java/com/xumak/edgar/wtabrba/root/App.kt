package com.xumak.edgar.wtabrba.root

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {
    lateinit var component : ApplicationComponet

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponet.builder()
            .build()
        component.inject(this)

        //inicializando el realm
        Realm.init(this)

        val realmConfig = RealmConfiguration.Builder()
            .name("Characters.realm") //nombre del archivo
            .schemaVersion(0)
            .build()


        Realm.setDefaultConfiguration(realmConfig)
    }
}