package emperorfin.android.dummyjsonusers.di

import android.content.Context
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import emperorfin.android.dummyjsonusers.BuildConfig
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.AppRoomDatabase
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entitysource.UserLocalDataSourceRoom
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dtosource.UserRemoteDataSourceRetrofit
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.api.UserApi
import emperorfin.android.dummyjsonusers.data.repository.UserRepository
import emperorfin.android.dummyjsonusers.domain.datalayer.dao.IUserDao
import emperorfin.android.dummyjsonusers.domain.datalayer.datasource.UserDataSource
import emperorfin.android.dummyjsonusers.domain.datalayer.repository.IUserRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Sunday 21st July, 2024.
 */



@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class UserLocalDataSource

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class UserRemoteDataSource

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class LocalUserDao

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class RemoteUserDao

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    @UserLocalDataSource
    abstract fun bindUserLocalDataSourceRoom(dataSource: UserLocalDataSourceRoom): UserDataSource

    @Singleton
    @Binds
    @UserRemoteDataSource
    abstract fun bindUserRemoteDataSourceRetrofit(dataSource: UserRemoteDataSourceRetrofit): UserDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(repository: UserRepository): IUserRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ): AppRoomDatabase {
        return AppRoomDatabase.getInstance(context)
    }

    //    @Provides
//    fun provideUserDao(database: AppRoomDatabase): UserDao = database.mUserDao
    @Provides
    @LocalUserDao
    fun provideUserDao(database: AppRoomDatabase): IUserDao = database.mUserDao
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(RequestInterceptor())
//            .build()
//    }

    @Provides
    @Singleton
//    fun provideRetrofit(okhHttpClient: OkHttpClient): Retrofit {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
//            .client(okhHttpClient)
            .baseUrl(BuildConfig.DUMMY_JSON_BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @RemoteUserDao
    fun provideUserApi(retrofit: Retrofit): IUserDao { //UserApi
        return retrofit.create(UserApi::class.java)
    }
}