package bobby.irawan.moviecatalogue

import android.app.Application
import android.content.Context
import bobby.irawan.moviecatalogue.di.dataModule
import bobby.irawan.moviecatalogue.di.domainModule
import bobby.irawan.moviecatalogue.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Bobby Irawan on 25/10/20.
 */
class AppController : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            androidLogger(Level.NONE)
            modules(dataModule, domainModule, presentationModule)
        }
    }

    companion object {
        private lateinit var instance: AppController

        fun getInstance(): Context = instance.applicationContext
    }
}