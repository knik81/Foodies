package nti.team.domain

import nti.team.entity.RepositoryApiInterface
import nti.team.entity.UseCaseApiInterface
import javax.inject.Inject

class UseCaseApi @Inject constructor(
    private val repositoryApi: RepositoryApiInterface
) : UseCaseApiInterface {
    override suspend fun getCategories() = repositoryApi.getCategories()

    override suspend fun getTags() = repositoryApi.getTags()

    override suspend fun getProducts() = repositoryApi.getProducts()
}