package com.example.dialogs.tabs

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.example.dialogs.Params
import com.example.dialogs.PickerMode
import com.example.dialogs.R
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [Tab1.newInstance] factory method to
 * create an instance of this fragment.
 */
internal class DateFragment(private var params: Params) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("InflateParams")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = LinearLayout(context)
        ll.id = View.generateViewId()
        ll.orientation = LinearLayout.VERTICAL
        ll.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        val datePicker : DatePicker = if (params.pickerMode == PickerMode.SPINNER) {
            inflater.inflate(R.layout.date_picker, null) as DatePicker
        } else {
            DatePicker(ContextThemeWrapper(context, params.pickerTheme))
        }

        datePicker.id = View.generateViewId()
        datePicker.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        datePicker.setOnDateChangedListener { p0, p1, p2, p3 ->
            params.dateChangeListener(p1, p2, p3)
        }

        datePicker.updateDate(
            params.dateTime.get(Calendar.YEAR),
            params.dateTime.get(Calendar.MONTH),
            params.dateTime.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.firstDayOfWeek = params.dateTime.get(Calendar.DAY_OF_WEEK)

        ll.addView(datePicker)
        return ll
    }

}