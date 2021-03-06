package com.lavelindo.bukalelang.detailProduct

import com.lavelindo.bukalelang.common.di.ApplicationApi
import com.lavelindo.bukalelang.common.di.ApplicationComponent
import com.lavelindo.bukalelang.common.di.ApplicationModule
import com.lavelindo.bukalelang.common.domain.repository.BidRepository
import com.lavelindo.bukalelang.common.domain.repository.BidRepositoryImpl
import com.lavelindo.bukalelang.detailProduct.domain.BidProductUsecase
import com.lavelindo.bukalelang.detailProduct.domain.DetailsProductRepository
import com.lavelindo.bukalelang.detailProduct.domain.DetailsProductRepositoryImpl
import com.lavelindo.bukalelang.detailProduct.domain.GetProductDetailsUsecase
import space.traversal.kapsule.Injects

/**
 * Created by sujarwe on 5/28/17.
 */
class DetailsPresenterModule(val view: DetailsProductView) : Injects<ApplicationComponent> {
    private val executor by required { executor }
    private val mainThread by required { mainThread }
    private val api by required { api }
    private var repository: DetailsProductRepository
    private var bidRepository: BidRepository
    private var usecase: GetProductDetailsUsecase
    private var bidUsecase: BidProductUsecase
    init {
        inject(ApplicationComponent(ApplicationModule(), ApplicationApi()))
        repository = DetailsProductRepositoryImpl(api)
        bidRepository = BidRepositoryImpl(api)
        usecase = GetProductDetailsUsecase(executor,mainThread,repository)
        bidUsecase = BidProductUsecase(executor,mainThread,bidRepository)
    }
    val presenter get() = DetailProductPresenter(view,usecase,bidUsecase)
}