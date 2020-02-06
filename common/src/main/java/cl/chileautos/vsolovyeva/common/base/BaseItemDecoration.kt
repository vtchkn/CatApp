package cl.chileautos.vsolovyeva.common.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseItemDecoration
/**
 * @param top_bottom for top and bottom margin
 * @param left_right for left and right margin
 */(private var top_bottom: Int, private var left_right: Int? = 0) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = top_bottom
        outRect.top = top_bottom
        if (left_right != null) {
            outRect.right = left_right!!
            outRect.left = left_right!!
        }
    }
}