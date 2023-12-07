package not.cool.ed.easyfinance.feature.data.category.api

import not.cool.ed.easyfinance.feature.data.transaction.api.NewCategory

interface CategoryRepository {

    suspend fun getById(categoryId: Long): Category?

    suspend fun getByTitle(title: String): Category?

    suspend fun list(): List<Category>

    suspend fun save(newCategory: NewCategory): Category?

    suspend fun save(category: Category)

    suspend fun remove(category: Category)
}
