package pl.edu.uj.ii.szlosek.shop.realmDB

import io.realm.Realm
import io.realm.RealmConfiguration

object RealmConfig {
    private val realmVersion = 1L

    fun providesRealmConfig(): RealmConfiguration =
        RealmConfiguration.Builder()
            .schemaVersion(realmVersion)
            .build()
}