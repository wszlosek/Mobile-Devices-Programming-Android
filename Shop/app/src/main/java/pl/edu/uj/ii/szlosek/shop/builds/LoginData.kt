package pl.edu.uj.ii.szlosek.shop.builds

import pl.edu.uj.ii.szlosek.shop.models.LoginData
import pl.edu.uj.ii.szlosek.shop.services.LoginDataService

suspend fun getLoginData(): List<LoginData> {
    return loginDataService.getLoginData()
}

suspend fun getLoginData(id: Int): LoginData {
    return loginDataService.getLoginData(id)
}

suspend fun createColor(loginData: LoginData) {
    return loginDataService.createLoginData(loginData)
}

suspend fun updateLoginData(id: Int, loginData: LoginData) {
    return loginDataService.updateLoginData(id, loginData)
}

suspend fun deleteLoginData(id: Int) {
    return loginDataService.deleteLoginData(id)
}