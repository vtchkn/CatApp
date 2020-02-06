package cl.chileautos.vsolovyeva.catapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import cl.chileautos.vsolovyeva.catapp.R
import cl.chileautos.vsolovyeva.data.model.TheCatImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.image_view.view.*

class CatImageView(context: Context?, attrs: AttributeSet?) : MaterialCardView(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.image_view, this)
        layoutParams =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


    fun bind(item: TheCatImage) {
        Glide.with(this)
            .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
            .load(item.url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(entity_image_view)
    }
}