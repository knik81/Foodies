package nti.team.entity

interface UseCaseApiInterface {
    suspend fun getCategories(): ResultFromApi
    suspend fun getTags(): ResultFromApi
    suspend fun getProducts(): ResultFromApi
}