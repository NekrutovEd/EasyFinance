package not.cool.ed.easyfinance.feature.data.category.impl.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.app.database.AppDatabase
import not.cool.ed.easyfinance.feature.data.transaction.impl.local.db.CategoryDao
import not.cool.ed.easyfinance.feature.data.transaction.impl.local.db.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category WHERE id = :categoryId")
    fun getById(categoryId: Long): CategoryEntity?

    @Query("SELECT * FROM category WHERE title = :title LIMIT 1")
    fun getByTitle(title: String): CategoryEntity?

    @Query("SELECT * FROM category")
    fun list(): List<CategoryEntity>

    @Insert
    fun insert(category: CategoryEntity): Long

    @Update
    fun update(category: CategoryEntity)

    @Delete
    fun delete(category: CategoryEntity)
}

@Module
@InstallIn(ViewModelComponent::class)
internal class CategoryDaoModule {

    @Provides
    fun provideCategoryDao(database: AppDatabase): CategoryDao = database.categoryDao()
}
