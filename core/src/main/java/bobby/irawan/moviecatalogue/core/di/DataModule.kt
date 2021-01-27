package bobby.irawan.moviecatalogue.di

import androidx.room.Room
import bobby.irawan.moviecatalogue.core.BuildConfig.BASE_URL
import bobby.irawan.moviecatalogue.core.data.RepositoryImpl
import bobby.irawan.moviecatalogue.core.data.interceptor.HeaderInterceptor
import bobby.irawan.moviecatalogue.core.data.local.LocalDataSource
import bobby.irawan.moviecatalogue.core.data.local.room.FavoriteDatabase
import bobby.irawan.moviecatalogue.core.data.remote.RemoteDataSource
import bobby.irawan.moviecatalogue.core.data.remote.movie.MovieApi
import bobby.irawan.moviecatalogue.core.data.remote.tvshow.TvShowApi
import bobby.irawan.moviecatalogue.core.domain.repository.Repository
import bobby.irawan.moviecatalogue.core.utils.Constants.DATABASE_NAME
import bobby.irawan.moviecatalogue.core.utils.Constants.HEADER_INTERCEPTOR
import bobby.irawan.moviecatalogue.core.utils.Constants.LOGGING_INTERCEPTOR
import com.chuckerteam.chucker.api.ChuckerInterceptor
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Bobby Irawan on 25/10/20.
 */

val dataModule = module {

    single {
        val phasePhrase: ByteArray = SQLiteDatabase.getBytes("movie_catalogue".toCharArray())
        val factory = SupportFactory(phasePhrase)
        Room.databaseBuilder(
            androidApplication(),
            FavoriteDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    single {
        get<FavoriteDatabase>().favoriteDao()
    }

    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(qualifier = named(LOGGING_INTERCEPTOR)))
            .addInterceptor(get<Interceptor>(qualifier = named(HEADER_INTERCEPTOR)))
            .addInterceptor(ChuckerInterceptor(androidContext()))
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    single<Interceptor>(named(HEADER_INTERCEPTOR)) {
        HeaderInterceptor()
    }

    single {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(MovieApi::class.java) }
    single { get<Retrofit>().create(TvShowApi::class.java) }

    single { LocalDataSource(get()) }
    single { RemoteDataSource(get(), get()) }

    single<Repository> {
        RepositoryImpl(get(), get())
    }
}