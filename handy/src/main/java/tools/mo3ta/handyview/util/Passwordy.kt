package tools.mo3ta.handyview.util

import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.password.view.*
import tools.mo3ta.handyview.R

class Passwordy @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var isHidden = true

    init {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.password, this)

        Log.d("TestApp", "l sasasa")


        toggle.setOnClickListener {
            if (isHidden) {
                // so show
                edPasswordy.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                // hide
                edPasswordy.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            // move cursor to end of text
            val length = edPasswordy.text.length

            /*
             edPasswordy.postDelayed(
             {Log.d("TestApp" , "l $length")}
          , 250)
            */

            edPasswordy.append("")

            // change state
            isHidden = isHidden.not()
        }
    }

    fun getEdit(): EditText? {
        return edPasswordy
    }

}