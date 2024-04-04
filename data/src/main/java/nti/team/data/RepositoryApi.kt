package nti.team.data

import android.util.Log
import com.best.data.api.RetrofitApi
import nti.team.entity.RepositoryApiInterface
import nti.team.entity.ResultFromApi
import javax.inject.Inject

class RepositoryApi @Inject constructor(): RepositoryApiInterface {

    override suspend fun getCategories(): ResultFromApi {
        return try {
            ResultFromApi.Success(
                RetrofitApi.api.getCategories()
            )
        }catch (e: Throwable) {
            Log.d("Nik", "Ошибка: RepositoryAPI.getCategories - $e")
            ResultFromApi.Error(
                "Ошибка: RepositoryAPI.getCategories - $e"
            )
        }
    }

    override suspend fun getTags(): ResultFromApi {
        return try {
            ResultFromApi.Success(
                RetrofitApi.api.getTags()
            )
        }catch (e: Throwable) {
            Log.d("Nik", "Ошибка: RepositoryAPI.getTags - $e")
            ResultFromApi.Error(
                "Ошибка: RepositoryAPI.getTags - $e"
            )
        }
    }


    override suspend fun getProducts(): ResultFromApi {
        Log.d("Nik", "getProducts")
        return try {
            val tt = RetrofitApi.api.getProducts()
            //Log.d("Nik", tt.size.toString())
            ResultFromApi.Success(
                tt
                //RetrofitApi.api.getProducts()
            )
        }catch (e: Throwable) {
            Log.d("Nik", "Ошибка: RepositoryAPI.getProducts - $e")
            ResultFromApi.Error(
                "Ошибка: RepositoryAPI.getProducts - $e"
            )
        }
    }
}
