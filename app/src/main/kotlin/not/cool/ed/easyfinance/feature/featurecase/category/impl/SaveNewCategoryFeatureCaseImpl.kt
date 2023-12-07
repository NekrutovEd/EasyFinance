package not.cool.ed.easyfinance.feature.featurecase.category.impl

import not.cool.ed.easyfinance.common.enums.ReceiptType
import not.cool.ed.easyfinance.feature.data.category.api.CategoryRepository
import not.cool.ed.easyfinance.feature.data.category.api.NewCategory
import not.cool.ed.easyfinance.feature.data.transaction.api.NewCategory
import not.cool.ed.easyfinance.feature.featurecase.category.api.SaveNewCategoryFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.category.api.SaveNewCategoryResult
import javax.inject.Inject

internal class SaveNewCategoryFeatureCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository,
) : SaveNewCategoryFeatureCase {

    override suspend fun invoke(title: String, emoji: String, types: Set<ReceiptType>): SaveNewCategoryResult {
        if (title.isEmpty()) return SaveNewCategoryResult.EmptyTitle
        if (types.isEmpty()) return SaveNewCategoryResult.EmptyTypes

        val isAlreadyExist = categoryRepository.getByTitle(title) != null
        if (isAlreadyExist) return SaveNewCategoryResult.IsAlreadyExist

        val savedCategory = categoryRepository.save(NewCategory(title, emoji, types))
        return if (savedCategory != null) SaveNewCategoryResult.Success else SaveNewCategoryResult.FailOnSave
    }
}
