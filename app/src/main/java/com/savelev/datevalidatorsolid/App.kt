package com.savelev.datevalidatorsolid

import android.app.Application
import com.savelev.datevalidatorsolid.data.DateSharedPreferenceStorage
import pro.azhidkov.solid.date.domain.DateStorage
import timber.log.Timber

class App : Application() {



// TODO: все в активити
//    val dateView = AndroidDateView() //???? Создать dateView в активити
//
//    private val saveDateInteractor = SaveDateInteractor(dateStorage)
//
//    private val saveResultPresenter = SaveResultPresenter(dateView)
//
//    val saveDateController = SaveDateController(saveDateInteractor, saveResultPresenter)

    companion object {
        lateinit var dateStorage: DateStorage
            private set
    }

    override fun onCreate() {
        super.onCreate()

        dateStorage = DateSharedPreferenceStorage(MainActivity())

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}