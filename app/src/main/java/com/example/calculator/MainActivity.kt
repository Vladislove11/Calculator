package com.example.calculator

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var inputTV: TextView
    private lateinit var resultTV: TextView
    private lateinit var button_1_BTN: Button
    private lateinit var button_2_BTN: Button
    private lateinit var button_3_BTN: Button
    private lateinit var button_4_BTN: Button
    private lateinit var button_5_BTN: Button
    private lateinit var button_6_BTN: Button
    private lateinit var button_7_BTN: Button
    private lateinit var button_8_BTN: Button
    private lateinit var button_9_BTN: Button
    private lateinit var button_0_BTN: Button
    private lateinit var divBTN: Button
    private lateinit var multBTN: Button
    private lateinit var difBTN: Button
    private lateinit var sumBTN: Button
    private lateinit var equalsBTN: Button
    private lateinit var resetBTN: Button
    private var arg1: String = ""
    private var arg2: String = ""
    private var checkArgsOwnOrTwo: Boolean = true
    private var allString: String = ""
    private var stateEndOperation: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = ""
        //toolbar.overflowIcon. (R.drawable.arrow26)

        inputTV = findViewById(R.id.inputTV)
        resultTV = findViewById(R.id.resultTV)
        button_1_BTN = findViewById(R.id.button_1_BTN)
        button_2_BTN = findViewById(R.id.button_2_BTN)
        button_3_BTN = findViewById(R.id.button_3_BTN)
        button_4_BTN = findViewById(R.id.button_4_BTN)
        button_5_BTN = findViewById(R.id.button_5_BTN)
        button_6_BTN = findViewById(R.id.button_6_BTN)
        button_7_BTN = findViewById(R.id.button_7_BTN)
        button_8_BTN = findViewById(R.id.button_8_BTN)
        button_9_BTN = findViewById(R.id.button_9_BTN)
        button_0_BTN = findViewById(R.id.button_0_BTN)
        divBTN = findViewById(R.id.divBTN)
        multBTN = findViewById(R.id.multBTN)
        difBTN = findViewById(R.id.difBTN)
        sumBTN = findViewById(R.id.sumBTN)
        equalsBTN = findViewById(R.id.equalsBTN)
        resetBTN = findViewById(R.id.resetBTN)

        equalsBTN.setOnClickListener{
            if(allString.indexOf("/") > 0){
                resultTV.text = "${Operation(arg1.toDouble(), arg2.toDouble()).div()}"
            }
            if(allString.indexOf("*") > 0){
                resultTV.text = "${Operation(arg1.toDouble(), arg2.toDouble()).mult()}"
            }
            if(allString.indexOf("-") > 0){
                resultTV.text = "${Operation(arg1.toDouble(), arg2.toDouble()).dif()}"
            }
            if(allString.indexOf("+") > 0){
                resultTV.text = "${Operation(arg1.toDouble(), arg2.toDouble()).sum()}"
            }
            inputTV.text = allString + " ="
            stateEndOperation = true
        }
        resetBTN.setOnClickListener {
            arg1 = ""
            arg2 = ""
            allString = ""
            resultTV.text = ""
            inputTV.text = ""
            checkArgsOwnOrTwo = true
            stateEndOperation = false
            Toast.makeText(
                this, "Данные очищены", Toast.LENGTH_LONG
            ).show()
        }

        divBTN.setOnClickListener {
            clickExpression(divBTN)
        }
        multBTN.setOnClickListener {
            clickExpression(multBTN)
        }
        difBTN.setOnClickListener {
            clickExpression(difBTN)
        }
        sumBTN.setOnClickListener {
            clickExpression(sumBTN)
        }

        button_1_BTN.setOnClickListener {
            clickNum(button_1_BTN)
        }
        button_2_BTN.setOnClickListener {
            clickNum(button_2_BTN)
        }
        button_3_BTN.setOnClickListener {
            clickNum(button_3_BTN)
        }
        button_4_BTN.setOnClickListener {
            clickNum(button_4_BTN)
        }
        button_5_BTN.setOnClickListener {
            clickNum(button_5_BTN)
        }
        button_6_BTN.setOnClickListener {
            clickNum(button_6_BTN)
        }
        button_7_BTN.setOnClickListener {
            clickNum(button_7_BTN)
        }
        button_8_BTN.setOnClickListener {
            clickNum(button_8_BTN)
        }
        button_9_BTN.setOnClickListener {
            clickNum(button_9_BTN)
        }
        button_0_BTN.setOnClickListener {
            clickNum(button_0_BTN)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_context, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.exitMenuMain ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun clickNum(button: Button){
        if (!stateEndOperation) {
            if (checkArgsOwnOrTwo) {
                arg1 += button.text
                allString = arg1
            } else {
                arg2 = button.text.toString()
                allString += arg2
            }
            inputTV.text = allString
        } else {
            Toast.makeText(
                this, "Для продолжения нажмите reset", Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun clickExpression(button: Button){
        if(arg1 != "" && checkArgsOwnOrTwo){
            checkArgsOwnOrTwo = false
            allString = "$arg1 ${button.text} "
        } else {
            if (button.text == definitionOperation(allString)){
                Toast.makeText(
                    this, "Операция ${button.text} уже была выбрана", Toast.LENGTH_LONG
                ).show()
            } else {
            Toast.makeText(
                this, "Уже была выбрана операция ${definitionOperation(allString)}. Для " +
                        "смены операции нажмите кнопку reset", Toast.LENGTH_LONG
            ).show()
            }
        }
        inputTV.text = allString
    }

    private fun definitionOperation(tmpStrinf: String): String{
        if(allString.indexOf("/") > 0){
            return "/"
        }
        if(allString.indexOf("*") > 0){
            return "*"
        }
        if(allString.indexOf("-") > 0){
            return "-"
        }
        if(allString.indexOf("+") > 0){
            return "+"
        }
        return "null"
    }
}