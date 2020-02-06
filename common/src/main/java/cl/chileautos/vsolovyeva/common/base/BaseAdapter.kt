package cl.chileautos.vsolovyeva.common.base



import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {
    private var items: List<T> = ArrayList()


    override fun onBindViewHolder(vh: VH, i: Int) {
        vh.bind(getItem(i))
    }

    private fun getItem(i: Int): T {
        return items[i]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addAllAndNotify(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }
}
