package not.cool.ed.easyfinance.feature.featurecase.category.api

import not.cool.ed.easyfinance.feature.featurecase.category.api.model.DebitCategory

interface GetDebitCategoriesFeatureCase {

    suspend operator fun invoke(): List<DebitCategory>
}
