package tools.mahmoudmabrok.customeviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        startTime.addListener { hour, minutes ->
            Log.d(
                "AppApp",
                "start $hour $minutes , ,End ${endTime.getHours()} ${endTime.getMinutes()}"
            )
            val res = endTime.isBeforeOrEqual(hour, minutes)
            Toast.makeText(this , "Start state  $res" , Toast.LENGTH_SHORT).show()
        }

        endTime.addListener { hour, minutes ->
            Log.d(
                "AppApp",
                "end $hour $minutes  ,Start ${startTime.getHours()} ${startTime.getMinutes()}"
            )
            val res = startTime.isAfterOrEqual(hour, minutes)
            Toast.makeText(this , "End state  $res" , Toast.LENGTH_SHORT).show()
        }

    }
}