package pl.edu.uj.ii.szlosek.shop.realmDB.models.User

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmConfig
import pl.edu.uj.ii.szlosek.shop.services.UserService

class UserRealmFunc {

    suspend fun userSynchronization() {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
      //  val users = UserService.getUsers()
    }
}