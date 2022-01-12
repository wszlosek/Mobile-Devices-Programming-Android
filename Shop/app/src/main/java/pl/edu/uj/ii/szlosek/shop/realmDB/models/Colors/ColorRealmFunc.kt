package pl.edu.uj.ii.szlosek.shop.realmDB.models.Colors

import io.realm.Realm
import io.realm.RealmModel
import pl.edu.uj.ii.szlosek.shop.builds.getColors
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmConfig
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmOperations

class ColorRealmFunc {
    
    private val operations = RealmOperations()
    private val colorRealmClassName = ColorRealm::class.java

    fun getColorsRealm(): List<RealmModel> {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObjects(realm, colorRealmClassName)
    }

    fun getColorsRealm(id: Int): RealmModel? {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObject(realm, colorRealmClassName, id)
    }

    fun insertColorsRealm(colorModel: ColorRealm) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.insertObject(realm, colorModel)
    }

    fun deleteColorRealm(id: Int) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObject(realm, colorRealmClassName, id)
    }

    suspend fun colorsSynchronization() {
        val colors = getColors()
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObjects(realm, colorRealmClassName)
        colors.forEach {
            operations.insertObject(realm, ColorRealm(it.id, it.name))
        }
    }
}