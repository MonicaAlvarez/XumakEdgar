package com.xumak.edgar.wtabrba.root

import com.xumak.edgar.wtabrba.character.CharacterModule
import com.xumak.edgar.wtabrba.character.view.CharactersActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, CharacterModule::class))
interface ApplicationComponet {
    fun inject(app : App)
    fun inject(characterActivity : CharactersActivity)
}