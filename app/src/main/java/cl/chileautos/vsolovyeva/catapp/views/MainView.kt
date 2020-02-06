package cl.chileautos.vsolovyeva.catapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import cl.chileautos.vsolovyeva.catapp.MainActivity
import cl.chileautos.vsolovyeva.catapp.R
import cl.chileautos.vsolovyeva.catapp.viewModel.MainViewModel
import cl.chileautos.vsolovyeva.common.base.BaseItemDecoration
import cl.chileautos.vsolovyeva.data.model.TheCatImage
import kotlinx.android.synthetic.main.main_view.view.*


class MainView(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val mainActivity = context as MainActivity

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(mainActivity).get(MainViewModel::class.java)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.main_view, this)
        entity_list.adapter =
            TheCatImageAdapter()
        (context?.resources?.getDimensionPixelOffset(R.dimen.default_padding)?.let {
            entity_list.addItemDecoration(
                BaseItemDecoration(
                    it
                )
            )
        })
        viewModel.observeData(mainActivity) {
            bind(it)
        }
        viewModel.fetch()
    }

    private fun bind(list: List<TheCatImage>) {
        (entity_list.adapter as TheCatImageAdapter).addAllAndNotify(list)
    }

}

