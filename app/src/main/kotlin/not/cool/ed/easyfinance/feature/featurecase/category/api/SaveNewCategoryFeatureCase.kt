package not.cool.ed.easyfinance.feature.featurecase.category.api

import not.cool.ed.easyfinance.common.enums.ReceiptType

sealed interface SaveNewCategoryResult {
    object Success : SaveNewCategoryResult
    object EmptyTitle : SaveNewCategoryResult
    object EmptyTypes : SaveNewCategoryResult
    object FailOnSave : SaveNewCategoryResult
    object IsAlreadyExist : SaveNewCategoryResult
}

interface SaveNewCategoryFeatureCase {

    suspend operator fun invoke(title: String, emoji: String, types: Set<ReceiptType>): SaveNewCategoryResult
}
