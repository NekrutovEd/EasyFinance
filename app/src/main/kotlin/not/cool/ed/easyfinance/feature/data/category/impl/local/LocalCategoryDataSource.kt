package not.cool.ed.easyfinance.feature.data.category.impl.local

import not.cool.ed.easyfinance.feature.data.category.api.Category
import not.cool.ed.easyfinance.feature.data.category.api.NewCategory

interface LocalCategoryDataSource {

    suspend fun getById(categoryId: Long): Category?

    suspend fun getByTitle(title: String): Category?

    suspend fun list(): List<Category>

    suspend fun save(category: NewCategory): Category?

    suspend fun update(category: Category)

    suspend fun remove(category: Category)
}
