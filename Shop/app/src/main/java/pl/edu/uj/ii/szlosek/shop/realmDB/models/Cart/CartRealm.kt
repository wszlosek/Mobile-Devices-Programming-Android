package pl.edu.uj.ii.szlosek.shop.realmDB.models.Cart

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import pl.edu.uj.ii.szlosek.shop.realmDB.models.Products.ProductRealm

open class CartRealm(
    @PrimaryKey var id: Int = 0,
    var userId: Int = 0,
    var productId: Int = 0,
    var amount: Int = 0
) : RealmObject()