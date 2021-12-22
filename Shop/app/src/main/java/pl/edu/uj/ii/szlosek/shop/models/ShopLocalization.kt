package pl.edu.uj.ii.szlosek.shop.models

data class ShopLocalization(
    val id: Int, val name: String, val street: String,
    val city: String, val country: String
) {
    constructor() : this(0, "", "", "", "")
}
