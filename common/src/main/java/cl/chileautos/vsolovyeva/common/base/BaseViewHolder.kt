package cl.chileautos.vsolovyeva.common.base


import androidx.recyclerview.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setOnClickListener(listener: View.OnClickListener) {
        itemView.setOnClickListener(listener)
    }

    abstract fun bind(item: T)

    fun bind(item: T, position: Int) {
        bind(item)
    }
}

