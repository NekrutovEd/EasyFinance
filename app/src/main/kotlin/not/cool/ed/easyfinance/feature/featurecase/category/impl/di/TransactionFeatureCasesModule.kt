package not.cool.ed.easyfinance.feature.featurecase.category.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.feature.featurecase.transaction.api.SaveNewTransactionFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.transaction.impl.SaveTransactionFeatureCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal interface TransactionFeatureCasesModule {

    @Binds
    fun bindSaveTransactionFeatureCase(impl: SaveTransactionFeatureCaseImpl): SaveNewTransactionFeatureCase

}
