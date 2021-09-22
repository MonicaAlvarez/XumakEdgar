package com.xumak.edgar.wtabrba.character.model.service

import com.xumak.edgar.wtabrba.character.model.CharacterModelImp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ServiceModule::class))
interface ServiceComponent {
    fun inject(modelImp : CharacterModelImp)
}