package pl.edu.uj.ii.szlosek.shop.realmDB.models.ShopLocalization

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class ShopLocalizationRealm(
    @PrimaryKey var id: Int = 0,
    @Required var name: String = "",
    var street: String = "",
    @Required var city: String = "",
    @Required var country: String = "",
) : RealmObject()