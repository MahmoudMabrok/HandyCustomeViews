package tools.mo3ta.handyview.util

import android.content.Context
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
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


        toggle.setOnClickListener {
            if (isHidden) {
                // so show
                edPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                // hide
                edPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            // change state
            isHidden = isHidden.not()
        }
    }

    fun getEdit(): EditText? {
        return edPassword
    }

}