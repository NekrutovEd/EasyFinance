package not.cool.ed.easyfinance.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import papa.PapaEvent
import papa.PapaEventListener

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        PapaEventListener.install { event ->
            when (event) {
                is PapaEvent.AppLaunch -> Log.i("PapaEventListener AppLaunch", event.toString())
                is PapaEvent.FrozenFrameOnTouch -> Log.i("PapaEventListener FrozenFrameOnTouch", event.toString())
                is PapaEvent.UsageError -> Log.i("PapaEventListener UsageError", event.toString())
            }
        }
    }
}
