package not.cool.ed.easyfinance.feature.featurecase.account.api

sealed interface SaveNewAccountResult {
    object Success : SaveNewAccountResult
    object EmptyTitle : SaveNewAccountResult
    object IsAlreadyExist : SaveNewAccountResult
    object FailOnSave : SaveNewAccountResult
}

interface SaveNewAccountFeatureCase {

    suspend operator fun invoke(title: String) : SaveNewAccountResult
}
