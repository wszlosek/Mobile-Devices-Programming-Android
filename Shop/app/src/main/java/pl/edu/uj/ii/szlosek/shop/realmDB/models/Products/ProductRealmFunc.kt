package pl.edu.uj.ii.szlosek.shop.realmDB.models.Products

import io.realm.Realm
import io.realm.RealmModel
import pl.edu.uj.ii.szlosek.shop.builds.getProducts
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmConfig
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmOperations

class ProductRealmFunc {
    private val operations = RealmOperations()
    private val productRealmClassName = ProductRealm::class.java

    fun getProductsRealm(): List<RealmModel> {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObjects(realm, productRealmClassName)
    }

    fun getProductRealm(id: Int): RealmModel? {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObject(realm, productRealmClassName, id)
    }

    fun insertProductRealm(productModel: ProductRealm) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.insertObject(realm, productModel)
    }

    fun deleteProductRealm(id: Int) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObject(realm, productRealmClassName, id)
    }

    suspend fun productsSynchronization() {
        val products = getProducts()
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObjects(realm, productRealmClassName)
        products.forEach {
            operations.insertObject(realm, ProductRealm(it.id, it.name, it.categoryId,
            it.size, it.colorId, it.price, it.description))
        }
    }
}