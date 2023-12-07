package not.cool.ed.easyfinance.feature.featurecase.category.impl

import not.cool.ed.easyfinance.common.enums.ReceiptType
import not.cool.ed.easyfinance.feature.data.transaction.api.CategoryRepository
import not.cool.ed.easyfinance.feature.featurecase.category.api.GetDebitCategoriesFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.category.api.model.DebitCategory
import javax.inject.Inject

internal class GetDebitCategoriesFeatureCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository,
) : GetDebitCategoriesFeatureCase {

    override suspend fun invoke(): List<DebitCategory> = categoryRepository.list()
        .filter { it.types.contains(ReceiptType.Debit) }
        .map { DebitCategory(it.id, it.title, it.emoji) }
}
