package com.example.lab3_1

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.lab3_1.databinding.ActivitySixNumBinding
import java.util.Calendar

class SixNumActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_six_num)
        // Кнопка для открытия TimePickerDialog
        val selectTimeButton = findViewById<Button>(R.id.selectTimeButton)
        val items = arrayOf("One 1", "Two 2", "Three 3", "Four 4")
        // Обработчик нажатия на кнопку
        selectTimeButton.setOnClickListener {
            // Получаем текущее время
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // Открываем TimePickerDialog для выбора времени
            val timePickerDialog = TimePickerDialog(this,
                { _, selectedHour, selectedMinute ->
                    // Выводим выбранное время в консоль
                    Log.d("SelectedTime", "Your time: $selectedHour:$selectedMinute")
                },
                hour, minute, true) // true означает 24-часовой формат

            // Показываем диалог выбора времени
            timePickerDialog.show()
        }
        val spin = findViewById<Spinner>(R.id.spinner)
        // Создаем адаптер для Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Устанавливаем адаптер для Spinner
        spin.adapter = adapter

        // Обработчик выбора элемента в Spinner
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Отображаем выбранный элемент в консоли
                Log.d("SpinnerSelection", "Your item: ${items[position]}")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Не требуется обрабатывать ничего
            }
        }
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val sliderValue = findViewById<TextView>(R.id.textBarView)
        // Обработчик изменения значения ползунка
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Отображение текущего значения ползунка в TextView
                sliderValue.text = "Текущее значение: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Можно обработать начало изменения ползунка, если нужно
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Можно обработать конец изменения ползунка, если нужно
            }
        })
    }
}