package com.example.dialogs.tabs

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.dialogs.Params
import com.example.dialogs.R
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [Tab2.newInstance] factory method to
 * create an instance of this fragment.
 */
internal class TimeFragment(private var params: Params) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = LinearLayout(context)
        ll.id = View.generateViewId()
        ll.orientation = LinearLayout.VERTICAL
        ll.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        val timePicker = TimePicker(ContextThemeWrapper(context, params.pickerTheme))
        timePicker.id = View.generateViewId()
        timePicker.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        timePicker.setOnTimeChangedListener { tP, i, i2 ->
            params.timeChangeListener(i, i2)
        }

        timePicker.hour = params.dateTime.get(Calendar.HOUR_OF_DAY)
        timePicker.minute = params.dateTime.get(Calendar.MINUTE)
        if (params.is24HourView) {
            timePicker.setIs24HourView(true)
        }

        ll.addView(timePicker)
        ll.addView(getButtonLayout())

        return ll
    }

    private fun getButtonLayout(): View {
        val outValue = TypedValue()
        context?.theme?.resolveAttribute(R.attr.selectableItemBackground, outValue, true)

        val buttonLayout = LinearLayout(context)
        buttonLayout.id = View.generateViewId()
        buttonLayout.orientation = LinearLayout.HORIZONTAL
        buttonLayout.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

        val width = getButtonWidth()
        params.positiveButtonText?.apply {
            val positiveButton = TextView(context)
            positiveButton.id = View.generateViewId()
            positiveButton.layoutParams =
                LinearLayout.LayoutParams(
                    width,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            positiveButton.gravity = Gravity.CENTER
            positiveButton.setTypeface(positiveButton.typeface, Typeface.BOLD)
            positiveButton.setTextColor(params.tabTheme)
            positiveButton.text = this
            positiveButton.setOnClickListener {
                params.positiveButtonClickListener?.onClick()
            }
            positiveButton.setBackgroundResource(outValue.resourceId)
            buttonLayout.addView(positiveButton)
        }

        params.neutralButtonText?.apply {
            val neutralButton = TextView(context)
            neutralButton.id = View.generateViewId()
            neutralButton.layoutParams =
                LinearLayout.LayoutParams(
                    width,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            neutralButton.gravity = Gravity.CENTER
            neutralButton.setTypeface(neutralButton.typeface, Typeface.BOLD)
            neutralButton.setTextColor(params.tabTheme)
            neutralButton.text = this
            neutralButton.setOnClickListener {
                params.neutralButtonClickListener?.onClick()
            }
            neutralButton.setBackgroundResource(outValue.resourceId)
            buttonLayout.addView(neutralButton)
        }

        params.negativeButtonText?.apply {
            val negativeButton = TextView(context)
            negativeButton.id = View.generateViewId()
            negativeButton.layoutParams =
                LinearLayout.LayoutParams(
                    width,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            negativeButton.gravity = Gravity.CENTER
            negativeButton.setTypeface(negativeButton.typeface, Typeface.BOLD)
            negativeButton.setTextColor(params.tabTheme)
            negativeButton.text = this
            negativeButton.setOnClickListener {
                params.negativeButtonClickListener?.onClick()
            }
            negativeButton.setBackgroundResource(outValue.resourceId)
            buttonLayout.addView(negativeButton)
        }

        return buttonLayout
    }

    private fun getButtonWidth(): Int {
        return if (params.neutralButtonText != null && params.negativeButtonText != null && params.positiveButtonText != null) {
            params.width/3
        } else if(params.positiveButtonText != null && params.negativeButtonText != null) {
            params.width/2
        } else if(params.positiveButtonText != null && params.neutralButtonText != null) {
            params.width/2
        } else if(params.negativeButtonText != null && params.neutralButtonText != null) {
            params.width/2
        } else {
            params.width
        }
    }
}
