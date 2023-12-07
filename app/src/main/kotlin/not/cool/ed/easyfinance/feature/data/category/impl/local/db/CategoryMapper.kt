package not.cool.ed.easyfinance.feature.data.category.impl.local.db

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.common.enums.ReceiptType
import not.cool.ed.easyfinance.common.mapping.Mapper
import not.cool.ed.easyfinance.feature.data.category.api.Category
import not.cool.ed.easyfinance.feature.data.transaction.api.NewCategory
import not.cool.ed.easyfinance.feature.data.transaction.impl.local.db.CategoryEntity
import not.cool.ed.easyfinance.feature.data.transaction.impl.local.db.CategoryMapper
import javax.inject.Inject

class CategoryMapper @Inject constructor(
    private val adapter: JsonAdapter<Set<ReceiptType>>
) {

    val entityToDomain by lazy {
        Mapper<CategoryEntity, Category> {
            Category(
                id = it.id,
                title = it.title,
                emoji = it.emoji,
                types = adapter.fromJson(it.types)
                    ?: error("CategoryEntity.types (${it.types}) as NULL after convert from json"),
            )
        }
    }

    val domainToEntity by lazy {
        Mapper<Category, CategoryEntity> {
            CategoryEntity(
                id = it.id,
                title = it.title,
                emoji = it.emoji,
                types = adapter.toJson(it.types),
            )
        }
    }

    val newToEntity by lazy {
        Mapper<NewCategory, CategoryEntity> {
            CategoryEntity(
                id = 0,
                title = it.title,
                emoji = it.emoji,
                types = adapter.toJson(it.types),
            )
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
internal class CategoryMapperModule {

    @Provides
    fun bindCategoryMapper(adapter: JsonAdapter<Set<ReceiptType>>): CategoryMapper = CategoryMapper(adapter)

    @Provides
    fun provideJsonAdapterForSetReceiptType(moshi: Moshi): JsonAdapter<Set<ReceiptType>> = moshi.adapter()
}
