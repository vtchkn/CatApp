package cl.chileautos.vsolovyeva.catapp.app

import android.app.Application
import cl.chileautos.vsolovyeva.catapp.BuildConfig
import cl.chileautos.vsolovyeva.data.api.TheCatApiService
import cl.chileautos.vsolovyeva.data.repository.CatRepo

class App : Application() {

    val repo: CatRepo by lazy {
        CatRepo(TheCatApiService.create(BuildConfig.BASE_URL), BuildConfig.API_KEY)
    }
}
