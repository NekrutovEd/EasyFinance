package not.cool.ed.easyfinance.feature.data.category.impl.local.db

import not.cool.ed.easyfinance.feature.data.transaction.impl.local.db.CategoryDao

interface CategoryDatabase {

    fun categoryDao(): CategoryDao
}
