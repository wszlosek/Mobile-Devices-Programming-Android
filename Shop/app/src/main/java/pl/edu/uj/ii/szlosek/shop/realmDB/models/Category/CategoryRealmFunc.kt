package pl.edu.uj.ii.szlosek.shop.realmDB.models.Category

import io.realm.Realm
import io.realm.RealmModel
import pl.edu.uj.ii.szlosek.shop.builds.getCategories
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmConfig
import pl.edu.uj.ii.szlosek.shop.realmDB.RealmOperations

class CategoryRealmFunc {

    private val operations = RealmOperations()
    private val categoryRealmClassName = CategoryRealm::class.java

    fun getCategoriesRealm(): List<RealmModel> {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObjects(realm, categoryRealmClassName)
    }

    fun getCategoryRealm(id: Int): RealmModel? {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        return operations.getObject(realm, categoryRealmClassName, id)
    }

    fun insertCategoryRealm(categoryModel: CategoryRealm) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.insertObject(realm, categoryModel)
    }

    fun deleteCategoryRealm(id: Int) {
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObject(realm, categoryRealmClassName, id)
    }

    suspend fun categoriesSynchronization() {
        val categories = getCategories()
        val realm = Realm.getInstance(RealmConfig.providesRealmConfig())
        operations.deleteObjects(realm, categoryRealmClassName)
        categories.forEach {
            operations.insertObject(realm, CategoryRealm(it.id, it.name))
        }
    }
}