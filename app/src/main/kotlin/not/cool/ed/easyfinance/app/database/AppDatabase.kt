package not.cool.ed.easyfinance.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import not.cool.ed.easyfinance.app.database.AppDatabase.Companion.DB_NAME
import not.cool.ed.easyfinance.feature.data.transaction.impl.local.db.CategoryDatabase
import not.cool.ed.easyfinance.feature.data.transaction.impl.local.db.CategoryEntity

@Database(
    entities = [
        CategoryEntity::class,
    ],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase(),
    CategoryDatabase {

    companion object {
        const val DB_NAME = "AppDatabase"
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal class AppDatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, DB_NAME).build()
}
