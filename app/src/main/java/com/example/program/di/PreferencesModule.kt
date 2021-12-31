package com.example.program.di

import android.content.Context
import com.securepreferences.SecurePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

//val preferencesModule = module {
//    single {
//        SecurePreferences(get(), "", "my_prefs.xml")
//    }
//}

@Module
@InstallIn(ApplicationComponent::class)
object PreferencesModule {

    @Singleton
    @Provides
    fun provideSecurePreference(
        @ApplicationContext context: Context
    ) : SecurePreferences {
        return SecurePreferences(context,"", "my_prefs.xml")
    }
}