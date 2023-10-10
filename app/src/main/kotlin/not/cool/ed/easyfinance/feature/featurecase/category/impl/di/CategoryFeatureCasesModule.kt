package not.cool.ed.easyfinance.feature.featurecase.category.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import not.cool.ed.easyfinance.feature.featurecase.category.api.GetDebitCategoriesFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.category.api.SaveNewCategoryFeatureCase
import not.cool.ed.easyfinance.feature.featurecase.category.impl.GetDebitCategoriesFeatureCaseImpl
import not.cool.ed.easyfinance.feature.featurecase.category.impl.SaveNewCategoryFeatureCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal interface CategoryFeatureCasesModule {

    @Binds
    fun bindSaveCategoryFeatureCase(impl: SaveNewCategoryFeatureCaseImpl): SaveNewCategoryFeatureCase

    @Binds
    fun bindGetListCategoriesFeatureCase(impl: GetDebitCategoriesFeatureCaseImpl): GetDebitCategoriesFeatureCase

}
