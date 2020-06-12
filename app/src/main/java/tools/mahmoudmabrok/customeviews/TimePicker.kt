package tools.mahmoudmabrok.customeviews

import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.time.view.*
import tools.mahmoudmabrok.customeviews.util.DateTiemHelper
import java.util.*

/**
 * A Time Picker View
 * TextView with Icon
 * When click show Dialoge
 * when Time is selected
 * add it to view
 * ability to return date as Object or string (formatted)
 */
class TimePicker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var finalHour = -1
    var finalMinute = -1

    var ask24 = true
    var show24 = true

    init {
        // get attributes
        val typed = context.obtainStyledAttributes(attrs, R.styleable.TimePicker)
        val head = typed.getString(R.styleable.TimePicker_headText)
        ask24 = typed.getBoolean(R.styleable.TimePicker_ask24Hour, true)
        show24 = typed.getBoolean(R.styleable.TimePicker_show24Hour, true)

        // after use, we should free/recycle as it consume memory
        typed.recycle()


        Log.d("AppApp" ,"ask24$ask24 show24$show24" )

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.time, this)


        header.text = head

        tvTime.setOnClickListener {
            openDialoge()
        }
    }


    fun getTimeAS12Hour(): String {
        return tvTime.text.toString()
    }

    fun getTimeAS24Hour(): String {
        return tvTime.text.toString()
    }

    private fun openDialoge() {
        val calender = Calendar.getInstance().apply {
            time = Date()
        }

        /**
         * prepare hours, minutes for dialoge
         * if this first time so  get current time else place last selected time
         */
        val hours = if (finalHour == -1) calender.get(Calendar.HOUR_OF_DAY) else finalHour
        val minutes = if (finalMinute == -1) calender.get(Calendar.MINUTE) else finalMinute

        val picker = TimePickerDialog(context,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                placeNewTime(hourOfDay, minute)
            }, hours, minutes, ask24
        )

        picker.show()
    }

    /**
     * place time with dialoge at text view
     */
    private fun placeNewTime(hourOfDay: Int, minute: Int) {
        finalHour = hourOfDay
        finalMinute = minute

        if (finalMinute > -1 && finalMinute > -1) {
            val res = if (show24) {
                // get 24 Hour
                DateTiemHelper.get24HourTime(finalHour,finalMinute)
            } else {
                DateTiemHelper.get12HourTime(finalHour, finalMinute)
            }
            tvTime.text = res
        }

    }


}
