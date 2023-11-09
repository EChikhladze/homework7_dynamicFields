package com.example.homework7_dynamicfields

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.example.homework7_dynamicfields.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fieldList: MutableList<EditText> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener() {
            addField()
            binding.scrollView.post {
                binding.scrollView.fullScroll(View.FOCUS_DOWN)
            }
        }

        binding.btnRemove.setOnClickListener() {
            removeLastField()
        }
    }

    private fun addField() {
        val newField = EditText(this)

        newField.hint = binding.edFieldName.text
        newField.setHintTextColor(Color.LTGRAY)
        newField.setTextColor(Color.WHITE)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val marginSize = dpToPx(8)
        layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize)
        newField.layoutParams = layoutParams
        val paddingSize = dpToPx(16)
        newField.setPadding(paddingSize)

        if(binding.checkBox.isChecked) {
            newField.inputType = InputType.TYPE_CLASS_NUMBER
        }

        binding.layoutAddedFields.addView(newField)
        fieldList.add(newField)
    }

    private fun dpToPx(dp: Int): Int {
        val density = this.resources.displayMetrics.density
        val px = dp * density
        return px.toInt()
    }

    private fun removeLastField() {
        if (fieldList.isNotEmpty()) {
            val lastAddedField = fieldList.last()
            lastAddedField.visibility = View.GONE
            fieldList.removeLast()
        }
    }
}