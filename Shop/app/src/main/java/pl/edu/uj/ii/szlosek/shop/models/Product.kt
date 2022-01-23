package pl.edu.uj.ii.szlosek.shop.models

data class Product(
    val id: Int, val name: String, val categoryId: Int, val size: String,
    val colorId: Int, val price: Float, val description: String
) {
    constructor() : this(0, "", 0, "", 0, 0.0F, "")
}
