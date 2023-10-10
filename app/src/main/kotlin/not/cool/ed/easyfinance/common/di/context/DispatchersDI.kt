package not.cool.ed.easyfinance.common.di.context

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import kotlin.coroutines.CoroutineContext

@Qualifier
annotation class DispatcherMain

@Qualifier
annotation class DispatcherDefault

@Qualifier
annotation class DispatcherUnconfined

@Qualifier
annotation class DispatcherIO

@Qualifier
annotation class DispatcherLog

@Module
@InstallIn(SingletonComponent::class)
class CoroutineContextModule {

    @Provides
    @DispatcherMain
    fun provideDispatcherMain(dispatchers: Dispatchers): CoroutineContext = dispatchers.main

    @Provides
    @DispatcherDefault
    fun provideDispatcherDefault(dispatchers: Dispatchers): CoroutineContext = dispatchers.default

    @Provides
    @DispatcherUnconfined
    fun provideDispatcherUnconfined(dispatchers: Dispatchers): CoroutineContext = dispatchers.unconfined

    @Provides
    @DispatcherIO
    fun provideDispatcherIO(dispatchers: Dispatchers): CoroutineContext = dispatchers.io

    @Provides
    @DispatcherLog
    fun provideDispatcherLog(dispatchers: Dispatchers): CoroutineContext = dispatchers.log
}
