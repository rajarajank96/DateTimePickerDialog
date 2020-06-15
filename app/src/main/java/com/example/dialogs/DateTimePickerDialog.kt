package com.example.dialogs

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.util.*


class DateTimePickerDialog: DialogFragment() {

    private var onDateChangeListener: String = String()
    private var onTimeChangeListener: String = String()

    private val params = Params()

    var pickerTheme: Int = R.style.my_dialog_theme
    var tabTheme: Int = Color.BLUE
    var tabTextColor: Int = Color.parseColor("#727272")
    var tabSelectedTextColor: Int = Color.WHITE
    var tabIndicatorColor: Int = Color.YELLOW

    fun setOnDateChangeListener(listener: (year: Int, monthOfYear: Int, dayOfMonth: Int) -> Unit) {
        params.dateChangeListener = listener
    }

    fun setOnTimeChangeListener(listener: (hour: Int, minute: Int) -> Unit) {
        params.timeChangeListener = listener
    }

    fun setDateTime(calendar: Calendar) {
        params.dateTime = calendar
    }

    var is24HourView = false

    fun setPositiveButton(text: String, onClickListener: OnClickListener) {
        params.positiveButtonText = text
        params.positiveButtonClickListener = onClickListener
    }

    fun setNeutralButton(text: String, onClickListener: OnClickListener) {
        params.neutralButtonText = text
        params.neutralButtonClickListener = onClickListener
    }

    fun setNegativeButton(text: String, onClickListener: OnClickListener) {
        params.negativeButtonText = text
        params.negativeButtonClickListener = onClickListener
    }

    private lateinit var myPagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onStart() {
        super.onStart()

        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels

        params.width = (width * 0.7).toInt()
        val window = dialog?.window ?: return
        val params = window.attributes
        params.height = (height * 0.7).toInt()
        window.attributes = params

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        params.is24HourView = this.is24HourView
        params.pickerTheme = this.pickerTheme
        params.tabTheme = this.tabTheme

        myPagerAdapter = PagerAdapter(childFragmentManager, 2)
        myPagerAdapter.params = this.params
        context?.apply { viewPager = ViewPager(this) }
        context?.apply { tabLayout = TabLayout(this) }

        val ll = LinearLayout(context)
        ll.id = View.generateViewId()
        ll.orientation = LinearLayout.VERTICAL
        ll.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

        viewPager.id = View.generateViewId()
        viewPager.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

        tabLayout.id = View.generateViewId()
        tabLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setBackgroundColor(tabTheme)
        tabLayout.setTabTextColors(tabTextColor, tabSelectedTextColor)
        tabLayout.setSelectedTabIndicatorColor(tabIndicatorColor)

        ll.addView(tabLayout)
        viewPager.adapter = myPagerAdapter
        ll.addView(viewPager)

        return ll
    }
}