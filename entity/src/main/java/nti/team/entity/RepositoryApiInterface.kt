package nti.team.entity

interface RepositoryApiInterface {
    suspend fun getCategories(): ResultFromApi
    suspend fun getTags(): ResultFromApi
    suspend fun getProducts(): ResultFromApi
}