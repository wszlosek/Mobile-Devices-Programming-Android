package pl.edu.uj.ii.szlosek.shop.models

data class LoginData(
    val id: Int, val login: String, val password: String
) {
    constructor() : this(0, "", "")
}
