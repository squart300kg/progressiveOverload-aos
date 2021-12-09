package com.example.program

import android.app.Application
import com.example.program.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * 4B:88:4F:EB:BA:9E:2E:9C:4E:A4:57:4E:BD:29:E3:78:36:89:6B:07
 */
open class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        koinInit()

        contextInit()

    }

    private fun contextInit() {
        instance = this
    }

    private fun koinInit() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(listOf(preferencesModule, networkModule ,roomModule, repositoryModule ,viewModelModule))
        }
    }


//    open fun configureDi() = startKoin {
//        androidLogger(Level.ERROR)
//        androidContext(this@Application)
//        modules(listOf(preferencesModule, networkModule ,repositoryModule ,viewModelModule))
//    }

    companion object {
        private const val Tag = "Sauce Application"
        var instance: com.example.program.Application? = null

    }

}

