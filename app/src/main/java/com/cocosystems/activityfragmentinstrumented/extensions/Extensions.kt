package com.cocosystems.activityfragmentinstrumented.extensions

import android.util.Patterns
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun <T: Number> T.asMoney(): String {
    val decimalSymbols = DecimalFormatSymbols.getInstance()
    decimalSymbols.decimalSeparator = '.'
    decimalSymbols.groupingSeparator = ','
    return DecimalFormat("$###,###,##0.00", decimalSymbols).format(this)
}

fun String.isEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.validPassword(): Boolean {
    return this.length >= 8
}