package com.example.lab3_1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var isButtonPressed = false // Для сохранения состояния кнопки
    private var clickCount = 0 // Для отслеживания количества нажатий
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNum = findViewById<EditText>(R.id.editNum)


        editTextNum.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                val num = editTextNum.text.toString()


                Toast.makeText(applicationContext, num, Toast.LENGTH_SHORT).show()

                true
            } else {
                false
            }
        }

        val buttonStatusText = findViewById<TextView>(R.id.buttonStatusText)
        val counterButton = findViewById<Button>(R.id.counterButton)

        val holdButton = findViewById<Button>(R.id.toggleButton)
        holdButton.setBackgroundColor(Color.LTGRAY)
        // Обработчик касания для изменения цвета и текста при удерживании
        holdButton.setOnTouchListener {v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> { // Когда кнопка нажата
                    holdButton.text = "Удерживается"
                    holdButton.setBackgroundColor(Color.GREEN) // Меняем цвет на зелёный
                    buttonStatusText.text = "Нажата"
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> { // Когда кнопка отпущена
                    holdButton.text = "Удержи меня"
                    holdButton.setBackgroundColor(Color.LTGRAY) // Возвращаем цвет
                    buttonStatusText.text = "Отпущена"
                    true
                }
                else -> false
            }
        }

        // Обработчик для кнопки с количеством нажатий
        counterButton.setOnClickListener {
            clickCount++
            counterButton.text = "Количество нажатий: $clickCount"
        }
        val calenView = findViewById<CalendarView>(R.id.calendarView)
        calenView.setOnDateChangeListener { _, year, month, day ->
            Log.d("Selected date", "Your date is: $day/${month + 1}/$year")
        }
        val next= findViewById<Button>(R.id.nextButton)
        next.setOnClickListener{
            val myIntent = Intent(
                this,
                SixNumActivity::class.java
            )
            startActivity(myIntent)
        }
    }
}