package cl.chileautos.vsolovyeva.catapp.views

import android.view.ViewGroup
import cl.chileautos.vsolovyeva.common.base.BaseAdapter
import cl.chileautos.vsolovyeva.common.base.BaseViewHolder
import cl.chileautos.vsolovyeva.data.model.TheCatImage

class TheCatImageAdapter : BaseAdapter<TheCatImage, BaseViewHolder<TheCatImage>>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BaseViewHolder<TheCatImage> {
        return object : BaseViewHolder<TheCatImage>(
            CatImageView(
                viewGroup.context,
                null
            )
        ) {
            override fun bind(item: TheCatImage) {
                (itemView as CatImageView).bind(item)
            }
        }
    }
}