package nti.team.data


import nti.team.entity.Categories
import nti.team.entity.Product
import nti.team.entity.Tags
import retrofit2.http.GET


interface ApiInterface {

    @GET("/WorkTestServer/Categories.json")
    suspend fun getCategories(): List<Categories>

    @GET("/WorkTestServer/Tags.json")
    suspend fun getTags(): List<Tags>

    @GET("/WorkTestServer/Products.json")
    suspend fun getProducts(): List<Product>
}