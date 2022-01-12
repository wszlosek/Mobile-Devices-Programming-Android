package pl.edu.uj.ii.szlosek.shop.realmDB.models.ShopLocalization

import io.realm.Realm
import io.realm.RealmModel
import pl.edu.uj.ii.szlosek.shop.builds.getShops
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmConfig
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmOperations
import pl.edu.uj.ii.szlosek.shop.realmDB.models.User.UserRealm

class ShopLocalizationRealmFunc {
    
    private val operations = RealmOperations()
    private val shopLocalizationRealmClassName = ShopLocalizationRealm::class.java

    fun getShopsRealm(): List<RealmModel> {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObjects(realm, shopLocalizationRealmClassName)
    }

    fun getShopRealm(id: Int): RealmModel? {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObject(realm, shopLocalizationRealmClassName, id)
    }

    fun insertShopRealm(userModel: UserRealm) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.insertObject(realm, userModel)
    }

    fun deleteShopRealm(id: Int) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObject(realm, shopLocalizationRealmClassName, id)
    }

    suspend fun shopsSynchronization() {
        val shops = getShops()
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObjects(realm, shopLocalizationRealmClassName)
        shops.forEach {
            operations.insertObject(realm, ShopLocalizationRealm(it.id, it.name, it.street, it.city, it.country))
        }
    }
}