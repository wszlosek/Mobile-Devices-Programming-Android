package pl.edu.uj.ii.szlosek.shop.realmDB.models.LoginData

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class LoginDataRealm (
    @PrimaryKey var id: Int = 0,
    @Required var login: String = "",
    @Required var password: String = ""
) : RealmObject()
