package pl.edu.uj.ii.szlosek.shop.realmDB.models.Cart

import io.realm.Realm
import io.realm.RealmModel
import pl.edu.uj.ii.szlosek.shop.builds.getCarts
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmConfig
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmOperations
import pl.edu.uj.ii.szlosek.shop.realmDB.models.User.UserRealm

class CartRealmFunc {

    private val operations = RealmOperations()
    private val cartRealmClassName = CartRealm::class.java

    fun getCartsRealm(): List<RealmModel> {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObjects(realm, cartRealmClassName)
    }

    fun getCartRealm(id: Int): RealmModel? {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObject(realm, cartRealmClassName, id)
    }

    fun insertCartRealm(cartModel: CartRealm) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.insertObject(realm, cartModel)
    }

    fun deleteCartRealm(id: Int) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObject(realm, cartRealmClassName, id)
    }

    suspend fun cartsSynchronization() {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        val carts = getCarts()
        operations.deleteObjects(realm, cartRealmClassName)
        carts.forEach {
            operations.insertObject(realm, CartRealm(it.id, it.userId, it.productId, it.amount))
        }
    }
}