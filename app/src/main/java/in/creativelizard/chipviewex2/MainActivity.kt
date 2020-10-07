package `in`.creativelizard.chipviewex2

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ImageSpan
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val itms = arrayListOf<String>("Name 1","Name 2","Name 3")
    var arrayAdp : ArrayAdapter<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        horizontal_scroll_view.background = acField.background
        acField.background = null

        arrayAdp = ArrayAdapter(this,android.R.layout.select_dialog_item,itms)

        acField.threshold = 1
        acField.setAdapter(arrayAdp)
        onActonPerform()
    }

    private fun onActonPerform() {

        acField.setOnItemClickListener { adapterView, view, i, l ->
            val chip = Chip(this@MainActivity)
            chip.text = itms[i]//.substring(0, trimmed.length - 1)
            chip.isCloseIconEnabled = true

            //Callback fired when chip close icon is clicked
            chip.setOnCloseIconClickListener {
                chipGroup.removeView(chip)
            }

            chipGroup.addView(chip)
            acField.editableText?.clear()
        }

        acField.setOnKeyListener { _, _, event ->
            if (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DEL) {
                if (acField.length() == 0 && chipGroup.childCount > 0) {
                    val chip = chipGroup.getChildAt(chipGroup.childCount - 1) as Chip
                    chipGroup.removeView(chip)
                }
            }
            false
        }

    }

}