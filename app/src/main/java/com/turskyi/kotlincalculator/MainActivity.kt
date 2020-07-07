package com.turskyi.kotlincalculator

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main), View.OnClickListener {

    companion object {
        private const val MENU_RESET_ID = 1
        private const val MENU_QUIT_ID = 2
    }

    private var oper = ""

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* прописываем обработчик */
        btnAdd.setOnClickListener(this)
        btnSub.setOnClickListener(this)
        btnMult.setOnClickListener(this)
        btnDiv.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        var result = 0f

        /* Проверяем поля на пустоту */
        if (TextUtils.isEmpty(etNum1.text.toString()) ||
            TextUtils.isEmpty(etNum2.text.toString())
        ) return

        /* читаем EditText и заполняем переменные числами */
        val num1: Float = java.lang.Float.parseFloat(etNum1.text.toString())
        val num2: Float = java.lang.Float.parseFloat(etNum2.text.toString())

        /* определяем нажатую кнопку и выполняем соответствующую операцию */
        /* в oper пишем операцию, потом будем использовать в выводе */
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
        }

        /* формируем строку вывода */
        tvResult.text =
            TextUtils.concat(num1.toString(), oper, num2.toString(), " = ", result.toString())
    }

    /* создание меню */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_RESET_ID, 0, "Reset")
        menu.add(0, MENU_QUIT_ID, 0, "Quit")
        return super.onCreateOptionsMenu(menu)
    }

    /* обработка нажатий на пункты меню */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_RESET_ID -> {
                /* очищаем поля */
                etNum1.setText("")
                etNum2.setText("")
                tvResult.text = ""
            }
            MENU_QUIT_ID ->
                /* выход из приложения */
                finish()
        }
        return super.onOptionsItemSelected(item)
    }
}