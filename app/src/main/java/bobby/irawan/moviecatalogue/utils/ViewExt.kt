package bobby.irawan.moviecatalogue.utils

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.moviecatalogue.AppController
import bobby.irawan.moviecatalogue.R
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Bobby Irawan on 28/10/20.
 */

fun ImageView.setForMovieBanner(imageUrl: String) =
    this.load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.ic_image_placeholder)
        error(R.drawable.ic_image_broken)
        fallback(R.drawable.ic_image_broken)
    }

fun ImageView.setForMovieInfo(imageUrl: String) =
    this.load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.ic_image_placeholder)
        error(R.drawable.ic_image_broken)
        fallback(R.drawable.ic_image_broken)
        transformations(RoundedCornersTransformation(20f, 20f, 0f, 0f))
    }

fun ImageView.setForMovieFavorite(imageUrl: String) =
    this.load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.ic_image_placeholder)
        error(R.drawable.ic_image_broken)
        fallback(R.drawable.ic_image_broken)
        transformations(RoundedCornersTransformation(20f, 0f, 20f, 0f))
    }

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun String?.orNoInfoString(): String =
    if (this.isNullOrEmpty()) AppController.getInstance()
        .getString(R.string.no_available_information) else this

fun Boolean?.orTrue() = this ?: true

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun TextView.isShowEmptyInfo(data: List<*>?, action: () -> Unit = {}) = if (data.isNullOrEmpty()) {
    this.setVisible()
    action()
} else {
    this.setGone()
}

fun RecyclerView.orGone(data: List<*>?) = if (data.isNullOrEmpty()) {
    this.setGone()
} else this.setVisible()

fun TextView.showNoInfoIf(data: List<*>?) = if (data.isNullOrEmpty()) {
    this.setVisible()
} else this.setGone()

fun View.showSlidingIf(predicate: Boolean) {
    if (predicate) showSlideUp()
    else hideSlideDown()
}

fun View.showSlideUp() {
    if (this.visibility != View.VISIBLE) {
        setVisible()
        val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
        startAnimation(
            TranslateAnimation(
                0f,
                0f,
                height.toFloat() + bottomMargin.toFloat(),
                0f
            ).apply {
                duration = 500
            })
    }
}

fun View.hideSlideDown() {
    if (this.visibility == View.VISIBLE) {
        setGone()
        if (this.animation?.hasEnded().orTrue()) {
            val bottomMargin = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
            startAnimation(
                TranslateAnimation(
                    0f,
                    0f,
                    0f,
                    height.toFloat() + bottomMargin.toFloat()
                ).apply {
                    duration = 500
                })
        } else {
            this.animation?.cancel()
        }
    }
}

fun ShimmerFrameLayout.setGoneAndStop() {
    this.setGone()
    this.stopShimmer()
}

fun View.showNoInternetSnackbar(action: () -> Unit) {
    Snackbar.make(
        this,
        this.context.getText(R.string.no_internet_label),
        Snackbar.LENGTH_INDEFINITE
    )
        .setAction(this.context.getText(R.string.retry_label)) {
            action()
        }
        .setBackgroundTint(ContextCompat.getColor(this.context, R.color.danger))
        .setActionTextColor(ContextCompat.getColor(this.context, android.R.color.white))
        .show()
}

fun Int.isZero() = this == 0