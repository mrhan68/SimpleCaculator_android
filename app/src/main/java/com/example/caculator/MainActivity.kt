package com.example.caculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var txtScreen: TextView
    private var lastValue=0.0
    private var value=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtScreen = findViewById(R.id.txtScreen)

        val button1: Button=findViewById(R.id.button10)
        val button2: Button=findViewById(R.id.button13)
        val button3: Button=findViewById(R.id.button17)
        val button4: Button=findViewById(R.id.button9)
        val button5: Button=findViewById(R.id.button12)
        val button6: Button=findViewById(R.id.button15)
        val button7: Button=findViewById(R.id.button5)
        val button8: Button=findViewById(R.id.button6)
        val button9: Button=findViewById(R.id.button7)
        val button0: Button=findViewById(R.id.button14)
        val btnAdd: Button=findViewById(R.id.btnAdd)
        val btnSubtract: Button=findViewById(R.id.btnSubtract)
        val btnMultiply: Button=findViewById(R.id.btnMultiply)
        val btnDivide: Button=findViewById(R.id.btnDivide)
        button0.setOnClickListener { value=addToString(value,'0') }
        button1.setOnClickListener { value=addToString(value,'1') }
        button2.setOnClickListener { value=addToString(value,'2') }
        button3.setOnClickListener { value=addToString(value,'3') }
        button4.setOnClickListener { value=addToString(value,'4') }
        button5.setOnClickListener { value=addToString(value,'5') }
        button6.setOnClickListener { value=addToString(value,'6') }
        button7.setOnClickListener { value=addToString(value,'7') }
        button8.setOnClickListener { value=addToString(value,'8') }
        button9.setOnClickListener { value=addToString(value,'9') }
        btnAdd.setOnClickListener { value=addToString(value,'+') }
        btnSubtract.setOnClickListener { value=addToString(value,'-') }
        btnMultiply.setOnClickListener { value=addToString(value,'*') }
        btnDivide.setOnClickListener { value=addToString(value,'/') }


        findViewById<Button>(R.id.btnEquals).setOnClickListener { value=equalsClicked(value) }
        findViewById<Button>(R.id.btnCE).setOnClickListener { clearEntryClicked() }
        findViewById<Button>(R.id.btnC).setOnClickListener { clearClicked() }
        findViewById<Button>(R.id.btnBS).setOnClickListener { backspaceClicked() }
    }

    @SuppressLint("SetTextI18n")
    private fun addToString(inputString: String, char: Char): String {
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = "$inputString$char"
        return "$inputString$char"
    }

    private fun equalsClicked(inputStr: String): String {
        try {
            val result = ExpressionBuilder(inputStr)
                .build()
                .evaluate()
            lastValue= result
            val resultTextView: TextView = findViewById(R.id.textView)
            resultTextView.text = value+"="+result.toString()
            return result.toString()
        } catch (e: ArithmeticException) {
            println("Error: ${e.message}")
            return ""
        }
    }



    private fun clearEntryClicked() {
        value=""
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = value
    }


    private fun clearClicked() {
        lastValue = 0.0
        clearEntryClicked()
    }

    private fun backspaceClicked() {
        val currentText = value
        if (currentText.isNotEmpty()) {
            value = currentText.substring(0, currentText.length - 1)
            val resultTextView: TextView = findViewById(R.id.textView)
            resultTextView.text = value
        } else {
            clearEntryClicked()
        }
    }
}
