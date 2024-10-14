package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private var currentInput = ""
    private var operand1: Int? = null
    private var pendingOperation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.tv_display)

        // Các button số
        val button0: Button = findViewById(R.id.btn_0)
        val button1: Button = findViewById(R.id.btn_1)
        val button2: Button = findViewById(R.id.btn_2)
        val button3: Button = findViewById(R.id.btn_3)
        val button4: Button = findViewById(R.id.btn_4)
        val button5: Button = findViewById(R.id.btn_5)
        val button6: Button = findViewById(R.id.btn_6)
        val button7: Button = findViewById(R.id.btn_7)
        val button8: Button = findViewById(R.id.btn_8)
        val button9: Button = findViewById(R.id.btn_9)

        // Các button phép tính
        val buttonAdd: Button = findViewById(R.id.btn_add)
        val buttonSub: Button = findViewById(R.id.btn_sub)
        val buttonMul: Button = findViewById(R.id.btn_mul)
        val buttonDiv: Button = findViewById(R.id.btn_div)
        val buttonEqual: Button = findViewById(R.id.btn_equal)
        val buttonClear: Button = findViewById(R.id.btn_c)

        // Set event listener cho các button số
        val numberButtons = listOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
        for (button in numberButtons) {
            button.setOnClickListener {
                currentInput += (button.text).toString()
                textViewResult.text = currentInput
            }
        }

        // Event cho button Clear
        buttonClear.setOnClickListener {
            currentInput = ""
            operand1 = null
            pendingOperation = ""
            textViewResult.text = "0"
        }

        // Set event cho các button phép tính
        val operatorButtons = listOf(buttonAdd, buttonSub, buttonMul, buttonDiv)
        for (button in operatorButtons) {
            button.setOnClickListener {
                val operator = (button.text).toString()
                performOperation(operator)
            }
        }

        // Event cho button "="
        buttonEqual.setOnClickListener {
            performOperation(pendingOperation)
        }
    }

    private fun performOperation(operator: String) {
        if (currentInput.isNotEmpty()) {
            val value = currentInput.toInt()
            if (operand1 == null) {
                operand1 = value
            } else {
                when (pendingOperation) {
                    "+" -> operand1 = operand1!! + value
                    "-" -> operand1 = operand1!! - value
                    "x" -> operand1 = operand1!! * value
                    "/" -> operand1 = if (value != 0) operand1!! / value else 0// Tránh chia cho 0
                }
            }
        }

        pendingOperation = operator
        currentInput = ""
        textViewResult.text = operand1.toString()
    }
}
