package cl.chileautos.vsolovyeva.catapp.viewModel

import android.app.Application
import androidx.lifecycle.*
import cl.chileautos.vsolovyeva.catapp.app.App
import cl.chileautos.vsolovyeva.data.model.TheCatImage
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val data = MutableLiveData<List<TheCatImage>>()

    fun fetch() {
        viewModelScope.launch {
            data.postValue(getApplication<App>().repo.loadCats())
        }
    }

    fun observeData(owner: LifecycleOwner, observer: (List<TheCatImage>) -> Unit) {
        data.observe(owner, Observer<List<TheCatImage>> { t -> t?.let { observer.invoke(t) } })
    }

}