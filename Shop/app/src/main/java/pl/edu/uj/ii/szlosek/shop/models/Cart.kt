package pl.edu.uj.ii.szlosek.shop.models

data class Cart(val id: Int, val userId: Int, val productId: Int, val amount: Int) {
    constructor() : this(0, 0, 0, 0)
}
