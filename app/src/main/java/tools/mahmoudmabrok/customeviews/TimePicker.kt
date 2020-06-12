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

    private var finalHour = -1
    private var finalMinute = -1

    private var ask24 = true
    private var show24 = true


    private var timeListener: ((hour: Int, minutes: Int) -> Unit)? = null


    init {
        // get attributes
        val typed = context.obtainStyledAttributes(attrs, R.styleable.TimePicker)

        val head = typed.getString(R.styleable.TimePicker_headText)
        ask24 = typed.getBoolean(R.styleable.TimePicker_ask24Hour, true)
        show24 = typed.getBoolean(R.styleable.TimePicker_show24Hour, true)

        val endDrawable = typed.getDrawable(R.styleable.TimePicker_icon)
        val timeBG = typed.getDrawable(R.styleable.TimePicker_timeViewBG)

        // after use, we should free/recycle as it consume memory
        typed.recycle()

        Log.d(
            "AppApp",
            "timeBG $timeBG,  endDrawable $endDrawable,  $ask24 show24$show24 , end $endDrawable"
        )

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.time, this)

        // init values
        header.text = head
        tvTime.setCompoundDrawablesRelative(
            null,
            null,
            endDrawable ?: context.getDrawable(R.drawable.ic_time),
            null
        )

     //    tvTime.background = timeBG

        tvTime.setOnClickListener {
            openDialoge()
        }

    }


    fun addListener(listener: (hour: Int, minutes: Int) -> Unit) {
        this.timeListener = listener
    }


    fun getTimeAS12Hour(): String? {
        return if (finalHour == -1) null else DateTiemHelper.get12HourTime(finalHour, finalMinute)
    }

    fun getTimeAS24Hour(): String? {
        return if (finalHour == -1) null else DateTiemHelper.get24HourTime(finalHour, finalMinute)
    }
    fun getHours() = finalHour
    fun getMinutes() = finalMinute


    fun isAfterOrEqual (hour: Int, minutes: Int):Boolean {
        if (finalHour == -1) return true

        return  DateTiemHelper.isAfterOrEqual(hour , minutes , finalHour, finalMinute)
    }


    fun isBeforeOrEqual (hour: Int, minutes: Int):Boolean {
        if (finalHour == -1) return true

        return  DateTiemHelper.isBeforeOrEqual(hour , minutes , finalHour, finalMinute)
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

        val picker = TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                placeNewTime(hourOfDay, minute)
                // call Higher Order Function provided by User
                timeListener?.let { listener ->
                    listener(hourOfDay, minute)
                }
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
                DateTiemHelper.get24HourTime(finalHour, finalMinute)
            } else {
                DateTiemHelper.get12HourTime(finalHour, finalMinute)
            }
            tvTime.text = res
        }

    }


}
