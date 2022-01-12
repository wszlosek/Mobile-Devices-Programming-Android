package pl.edu.uj.ii.szlosek.shop.realmDB.models.Products

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class ProductRealm(
    @PrimaryKey var id: Int = 0,
    @Required var name: String = "",
    var categoryId: Int = 0,
    var size: String = "",
    var colorId: Int = 0,
    var price: Float = 0F,
    var description: String = ""
) : RealmObject()