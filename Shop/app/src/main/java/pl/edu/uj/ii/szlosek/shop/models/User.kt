package pl.edu.uj.ii.szlosek.shop.models

data class User(
    val id: Int, val firstName: String, val surname: String,
    val localization: String, val phoneNumber: String
) {
    constructor() : this(0, "", "", "", "")
}

