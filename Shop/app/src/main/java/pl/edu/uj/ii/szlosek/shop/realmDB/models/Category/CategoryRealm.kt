package pl.edu.uj.ii.szlosek.shop.realmDB.models.Category

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class CategoryRealm(
    @PrimaryKey var id: Int = 0,
    @Required var name: String = ""
) : RealmObject()