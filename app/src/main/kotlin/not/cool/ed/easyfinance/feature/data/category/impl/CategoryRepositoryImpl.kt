package not.cool.ed.easyfinance.feature.data.category.impl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.withContext
import not.cool.ed.easyfinance.common.di.context.DispatcherIO
import not.cool.ed.easyfinance.feature.data.category.api.Category
import not.cool.ed.easyfinance.feature.data.transaction.api.CategoryRepository
import not.cool.ed.easyfinance.feature.data.transaction.api.NewCategory
import not.cool.ed.easyfinance.feature.data.transaction.impl.CategoryRepositoryImpl
import not.cool.ed.easyfinance.feature.data.transaction.impl.local.LocalCategoryDataSource
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class CategoryRepositoryImpl @Inject constructor(
    private val localCategoryDataSource: LocalCategoryDataSource,
    @DispatcherIO private val context: CoroutineContext,
) : CategoryRepository {

    override suspend fun getById(categoryId: Long): Category? = withContext(context) {
        localCategoryDataSource.getById(categoryId)
    }

    override suspend fun getByTitle(title: String): Category? = withContext(context) {
        localCategoryDataSource.getByTitle(title)
    }

    override suspend fun list(): List<Category> = withContext(context) {
        localCategoryDataSource.list()
    }

    override suspend fun save(newCategory: NewCategory): Category? = withContext(context) {
        localCategoryDataSource.save(newCategory)
    }

    override suspend fun save(category: Category) = withContext(context) {
        localCategoryDataSource.update(category)
    }

    override suspend fun remove(category: Category) = withContext(context) {
        localCategoryDataSource.remove(category)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
internal interface CategoryRepositoryModule {

    @Binds
    fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository
}
