package not.cool.ed.easyfinance.feature.data.category.impl.local

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.feature.data.category.api.Category
import not.cool.ed.easyfinance.feature.data.category.api.NewCategory
import not.cool.ed.easyfinance.feature.data.category.impl.local.db.CategoryDao
import not.cool.ed.easyfinance.feature.data.transaction.impl.local.db.CategoryMapper
import javax.inject.Inject
import javax.inject.Provider

internal class LocalCategoryDataSourceImpl @Inject constructor(
    private val daoProvider: Provider<CategoryDao>,
    private val mapper: CategoryMapper,
) : LocalCategoryDataSource {

    private val dao get() = daoProvider.get()

    override suspend fun getById(categoryId: Long): Category? = dao.getById(categoryId)?.let(mapper.entityToDomain)

    override suspend fun getByTitle(title: String): Category? = dao.getByTitle(title)?.let(mapper.entityToDomain)

    override suspend fun list(): List<Category> = dao.list().map(mapper.entityToDomain)

    override suspend fun save(category: NewCategory): Category? {
        val newCategoryId = dao.insert(mapper.newToEntity(category))
        return getById(newCategoryId)
    }

    override suspend fun update(category: Category) = dao.update(mapper.domainToEntity(category))

    override suspend fun remove(category: Category) = dao.delete(mapper.domainToEntity(category))
}

@Module
@InstallIn(ViewModelComponent::class)
internal interface LocalCategoryDataSourceModule {

    @Binds
    fun bindLocalCategoryDataSource(impl: LocalCategoryDataSourceImpl): LocalCategoryDataSource
}
