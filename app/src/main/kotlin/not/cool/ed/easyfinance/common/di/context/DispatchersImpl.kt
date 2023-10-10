package not.cool.ed.easyfinance.common.di.context

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

class DispatchersImpl @Inject constructor(): Dispatchers {
    override val main: CoroutineContext = kotlinx.coroutines.Dispatchers.Main
    override val default: CoroutineContext = kotlinx.coroutines.Dispatchers.Default
    override val unconfined: CoroutineContext = kotlinx.coroutines.Dispatchers.Unconfined
    override val io: CoroutineContext = kotlinx.coroutines.Dispatchers.IO
    @OptIn(ExperimentalCoroutinesApi::class)
    override val log: CoroutineContext = kotlinx.coroutines.Dispatchers.IO.limitedParallelism(1)
}

@Module
@InstallIn(SingletonComponent::class)
interface DispatchersModule {

    @Binds
    @Singleton
    fun bindDispatchers(impl: DispatchersImpl): Dispatchers
}
