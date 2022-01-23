package pl.edu.uj.ii.szlosek.shop.realmDB

import io.realm.Realm
import io.realm.RealmModel
import io.realm.kotlin.deleteFromRealm
import pl.edu.uj.ii.szlosek.shop.realmDB.models.Cart.CartRealmFunc
import pl.edu.uj.ii.szlosek.shop.realmDB.models.Category.CategoryRealmFunc
import pl.edu.uj.ii.szlosek.shop.realmDB.models.Colors.ColorRealmFunc
import pl.edu.uj.ii.szlosek.shop.realmDB.models.LoginData.LoginDataRealmFunc
import pl.edu.uj.ii.szlosek.shop.realmDB.models.Products.ProductRealmFunc
import pl.edu.uj.ii.szlosek.shop.realmDB.models.ShopLocalization.ShopLocalizationRealmFunc
import pl.edu.uj.ii.szlosek.shop.realmDB.models.User.UserRealmFunc

class RealmOperations {

    fun getObjects(realm: Realm, classToGet: Class<out RealmModel>): List<RealmModel> {
        return realm
            .copyFromRealm(realm
            .where(classToGet)
            .findAll())
    }

    fun getObject(realm: Realm, classToGet: Class<out RealmModel>, id: Int): RealmModel? {
        return realm
            .where(classToGet)
            .equalTo("id", id)
            .findFirst()
    }

    fun insertObject(realm: Realm, model: RealmModel) {
        realm.executeTransaction  {
            realmTr -> realmTr.insert(model)
        }
    }

    fun deleteObject(realm: Realm, classToDelete: Class<out RealmModel>, id: Int) {
        realm.where(classToDelete).equalTo("id", id).findFirst()?.deleteFromRealm()
    }

    fun deleteObjects(realm: Realm, classToDelete: Class<out RealmModel>) {
        realm.executeTransaction {
            it.delete(classToDelete)
        }
    }

    suspend fun synchronizeAllModels() {
        val user = UserRealmFunc()
        val cart = CartRealmFunc()
        val categories = CategoryRealmFunc()
        val colors = ColorRealmFunc()
        val products = ProductRealmFunc()
        val shops = ShopLocalizationRealmFunc()
        val loginData = LoginDataRealmFunc()

        user.usersSynchronization()
        cart.cartsSynchronization()
        categories.categoriesSynchronization()
        colors.colorsSynchronization()
        shops.shopsSynchronization()
        products.productsSynchronization()
        loginData.loginDataSynchronization()
    }
}