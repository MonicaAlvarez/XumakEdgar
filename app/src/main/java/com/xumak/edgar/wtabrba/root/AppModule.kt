package com.xumak.edgar.wtabrba.root

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app :App) {
    @Provides
    @Singleton
    fun provideApp(): App {
        return app
    }
}