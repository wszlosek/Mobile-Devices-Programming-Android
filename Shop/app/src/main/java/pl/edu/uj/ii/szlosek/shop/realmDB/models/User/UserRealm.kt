package pl.edu.uj.ii.szlosek.shop.realmDB.models.User

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class UserRealm(): RealmObject() {
    @PrimaryKey
    var id: Int = 0

    @Required
    var firstName: String = ""
    @Required
    var surname: String = ""
    var localization: String = ""
    @Required
    var phoneNumber: String = ""
}