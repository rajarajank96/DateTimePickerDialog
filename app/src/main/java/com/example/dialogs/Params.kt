package com.example.dialogs

import android.graphics.Color
import java.util.*

internal class Params {

    var width = 0

    internal var positiveButtonText: String? = null
    internal var positiveButtonClickListener: OnClickListener? = null

    internal var negativeButtonText: String? = null
    internal var negativeButtonClickListener: OnClickListener? = null

    internal var neutralButtonText: String? = null
    internal var neutralButtonClickListener: OnClickListener? = null

    var is24HourView = false

    internal var dateTime: Calendar = Calendar.getInstance()

    internal var dateChangeListener: ((Int, Int, Int) -> Unit) = { i: Int, i1: Int, i2: Int -> }

    internal var timeChangeListener: ((Int, Int) -> Unit) = { i: Int, i1: Int -> }

    var pickerTheme: Int = R.style.my_dialog_theme
    var tabTheme: Int = Color.BLUE

    var pickerMode: PickerMode = PickerMode.CALENDAR

    fun setOnDateChangeListener(listener: (year: Int, monthOfYear: Int, dayOfMonth: Int) -> Unit) {
        this.dateChangeListener = listener
    }

    fun setOnTimeChangeListener(listener: (hour: Int, minute: Int) -> Unit) {
        this.timeChangeListener = listener
    }

    fun setDateTime(calendar: Calendar) {
        this.dateTime = calendar
    }

    fun setPositiveButton(text: String, onClickListener: OnClickListener) {
        this.positiveButtonText = text
        this.positiveButtonClickListener = onClickListener
    }

    fun setNeutralButton(text: String, onClickListener: OnClickListener) {
        this.neutralButtonText = text
        this.neutralButtonClickListener = onClickListener
    }

    fun setNegativeButton(text: String, onClickListener: OnClickListener) {
        this.negativeButtonText = text
        this.negativeButtonClickListener = onClickListener
    }

}