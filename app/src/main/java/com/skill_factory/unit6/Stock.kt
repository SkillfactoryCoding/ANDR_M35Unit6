package com.skill_factory.unit6

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.*

class Stock(var name: String,_price: Int = 0, @DrawableRes val iconId: Int) :
    BaseObservable() {
    val priceObservable = ObservableField<Int>(_price)

    var price: Int = _price
        set(value) {
            field = value
            priceObservable.set(value)
        }


    companion object {
        @BindingAdapter("imageId")
        @JvmStatic
        fun loadImage(view: ImageView, imageId: Int) {
            view.setImageResource(imageId)
        }
    }

}