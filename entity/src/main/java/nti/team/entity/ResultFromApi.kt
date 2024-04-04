package nti.team.entity

sealed class ResultFromApi {
    class Success <T>(val successData: T): ResultFromApi(){
    }
    class Error<T>(val ErrorMessage: T): ResultFromApi()
}