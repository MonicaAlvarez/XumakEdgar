package com.xumak.edgar.wtabrba.character

import com.xumak.edgar.wtabrba.character.model.CharacterModelImp
import com.xumak.edgar.wtabrba.character.presenter.CharacterPresenterImp
import dagger.Module
import dagger.Provides

@Module
class CharacterModule {
    @Provides
    fun providesPresenter(model : CharacterMVP.Model) : CharacterMVP.Presenter{
        return CharacterPresenterImp(model)
    }
    @Provides
    fun providesModel() : CharacterMVP.Model{
        return CharacterModelImp()
    }
}