package com.turskyi.kotlincalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val MENU_RESET_ID = 1
        private const val MENU_QUIT_ID = 2
    }

    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText

    private lateinit var btnAdd: Button
    private lateinit var btnSub: Button
    private lateinit var btnMult: Button
    private lateinit var btnDiv: Button

    private lateinit var tvResult: TextView

    private var oper = ""

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // находим элементы
        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)

        btnAdd = findViewById(R.id.btnAdd)
        btnSub = findViewById(R.id.btnSub)
        btnMult = findViewById(R.id.btnMult)
        btnDiv = findViewById(R.id.btnDiv)

        tvResult = findViewById(R.id.tvResult)

        // прописываем обработчик
        btnAdd.setOnClickListener(this)
        btnSub.setOnClickListener(this)
        btnMult.setOnClickListener(this)
        btnDiv.setOnClickListener(this)

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View) {
        // TODO Auto-generated method stub
        var num1 = 0f
        var num2 = 0f
        var result = 0f

        // Проверяем поля на пустоту
        if (TextUtils.isEmpty(etNum1.text.toString()) || TextUtils.isEmpty(etNum2.text.toString())) {
            return
        }

        // читаем EditText и заполняем переменные числами
        num1 = java.lang.Float.parseFloat(etNum1.text.toString())
        num2 = java.lang.Float.parseFloat(etNum2.text.toString())

        // определяем нажатую кнопку и выполняем соответствующую операцию
        // в oper пишем операцию, потом будем использовать в выводе
        when (v.id) {
            R.id.btnAdd -> {
                oper = "+"
                result = num1 + num2
            }
            R.id.btnSub -> {
                oper = "-"
                result = num1 - num2
            }
            R.id.btnMult -> {
                oper = "*"
                result = num1 * num2
            }
            R.id.btnDiv -> {
                oper = "/"
                result = num1 / num2
            }
            else -> {
            }
        }

        // формируем строку вывода
        tvResult.text = "$num1 $oper $num2 = $result"
    }

    // создание меню
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_RESET_ID, 0, "Reset")
        menu.add(0, MENU_QUIT_ID, 0, "Quit")
        return super.onCreateOptionsMenu(menu)
    }

    // обработка нажатий на пункты меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_RESET_ID -> {
                // очищаем поля
                etNum1.setText("")
                etNum2.setText("")
                tvResult.text = ""
            }
            MENU_QUIT_ID ->
                // выход из приложения
                finish()
        }
        return super.onOptionsItemSelected(item)
    }
}