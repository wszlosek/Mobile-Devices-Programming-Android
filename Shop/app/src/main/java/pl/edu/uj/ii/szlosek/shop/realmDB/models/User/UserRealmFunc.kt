package pl.edu.uj.ii.szlosek.shop.realmDB.models.User

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmModel
import io.realm.kotlin.executeTransactionAwait
import pl.edu.uj.ii.szlosek.shop.builds.getUsers
import pl.edu.uj.ii.szlosek.shop.models.User
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmConfig
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmOperations
import pl.edu.uj.ii.szlosek.shop.services.UserService

class UserRealmFunc {

    private val operations = RealmOperations()
    private val userRealmClassName = UserRealm::class.java

    fun getUsersRealm(): List<RealmModel> {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObjects(realm, userRealmClassName)
    }

    fun getUserRealm(id: Int): RealmModel? {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObject(realm, userRealmClassName, id)
    }

    fun insertUserRealm(userModel: UserRealm) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.insertObject(realm, userModel)
    }

    fun deleteUserRealm(id: Int) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObject(realm, userRealmClassName, id)
    }

    suspend fun usersSynchronization() {
        val users = getUsers()
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObjects(realm, userRealmClassName)
        users.forEach {
            operations.insertObject(realm, UserRealm(it.id, it.firstName, it.surname, it.localization, it.phoneNumber))
        }
    }
}