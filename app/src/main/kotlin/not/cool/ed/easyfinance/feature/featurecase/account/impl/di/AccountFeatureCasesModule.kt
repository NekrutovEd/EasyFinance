package not.cool.ed.easyfinance.feature.featurecase.account.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.feature.featurecase.account.api.GetAccountsFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.account.api.SaveNewAccountFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.account.impl.GetAccountsFeatureCaseImpl
import not.cool.ed.easyfinance.feature.featurecase.account.impl.SaveNewAccountFeatureCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal interface AccountFeatureCasesModule {

    @Binds
    fun bindSaveAccountFeatureCase(impl: SaveNewAccountFeatureCaseImpl): SaveNewAccountFeatureCase

    @Binds
    fun bindGetAccountsFeatureCase(impl: GetAccountsFeatureCaseImpl): GetAccountsFeatureCase

}
