package not.cool.ed.easyfinance.feature.featurecase.account.impl

import not.cool.ed.easyfinance.feature.featurecase.account.api.SaveNewAccountFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.account.api.SaveNewAccountResult
import javax.inject.Inject

internal class SaveNewAccountFeatureCaseImpl @Inject constructor(
//    private val accountRepository: AccountRepository,
) : SaveNewAccountFeatureCase {

    override suspend fun invoke(title: String): SaveNewAccountResult {
        return SaveNewAccountResult.FailOnSave

//        if (title.isEmpty()) return SaveNewAccountResult.EmptyTitle
//
//        val isAlreadyExist = accountRepository.getByTitle(title) != null
//        if (isAlreadyExist) return SaveNewAccountResult.IsAlreadyExist
//
//        val savedCategory = accountRepository.save(NewAccount(title))
//        return if (savedCategory != null) SaveNewAccountResult.Success else SaveNewAccountResult.FailOnSave
    }
}
