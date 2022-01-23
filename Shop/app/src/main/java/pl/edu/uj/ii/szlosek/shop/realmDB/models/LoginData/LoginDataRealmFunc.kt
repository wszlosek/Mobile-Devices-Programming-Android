package pl.edu.uj.ii.szlosek.shop.realmDB.models.LoginData

import io.realm.Realm
import io.realm.RealmModel
import pl.edu.uj.ii.szlosek.shop.builds.getLoginData
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmConfig
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmOperations

class LoginDataRealmFunc {

    private val operations = RealmOperations()
    private val loginDataRealmClassName = LoginDataRealm::class.java

    fun getLoginDataRealm(): List<RealmModel> {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObjects(realm, loginDataRealmClassName)
    }

    fun getLoginDataRealm(id: Int): RealmModel? {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObject(realm, loginDataRealmClassName, id)
    }

    fun insertLoginDataRealm(loginDataModel: LoginDataRealm) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.insertObject(realm, loginDataModel)
    }

    fun deleteLoginDataRealm(id: Int) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObject(realm, loginDataRealmClassName, id)
    }

    suspend fun loginDataSynchronization() {
        val loginData = getLoginData()
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObjects(realm, loginDataRealmClassName)
        loginData.forEach {
            operations.insertObject(realm, LoginDataRealm(it.id, it.login, it.password))
        }
    }
}