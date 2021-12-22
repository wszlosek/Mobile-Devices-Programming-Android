package pl.edu.uj.ii.szlosek.shop.models

data class Category(val id: Int, val name: String) {
    constructor() : this(0, "")
}
