package com.example.lab3_1

import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class SixNumActivity : AppCompatActivity() {

    private lateinit var colorPreview: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_six_num)

        // Кнопка для выбора времени
        val selectTimeButton = findViewById<Button>(R.id.selectTimeButton)
        val items = arrayOf("One 1", "Two 2", "Three 3", "Four 4")
        selectTimeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this,
                { _, selectedHour, selectedMinute ->
                    Log.d("SelectedTime", "Your time: $selectedHour:$selectedMinute")
                },
                hour, minute, true)
            timePickerDialog.show()
        }

        val spin = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = adapter
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                Log.d("SpinnerSelection", "Your item: ${items[position]}")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val sliderValue = findViewById<TextView>(R.id.textBarView)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sliderValue.text = "Текущее значение: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Ползунки для RGB
        val redSeekBar = findViewById<SeekBar>(R.id.seekBarRed)
        val greenSeekBar = findViewById<SeekBar>(R.id.seekBarGreen)
        val blueSeekBar = findViewById<SeekBar>(R.id.seekBarBlue)
        val redValue = findViewById<TextView>(R.id.redValue)
        val greenValue = findViewById<TextView>(R.id.greenValue)
        val blueValue = findViewById<TextView>(R.id.blueValue)
        colorPreview = findViewById(R.id.colorPreview)

        val rgbChangeListener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (seekBar?.id) {
                    R.id.seekBarRed -> redValue.text = "Red: $progress"
                    R.id.seekBarGreen -> greenValue.text = "Green: $progress"
                    R.id.seekBarBlue -> blueValue.text = "Blue: $progress"
                }
                updateColorPreview()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        }

        redSeekBar.setOnSeekBarChangeListener(rgbChangeListener)
        greenSeekBar.setOnSeekBarChangeListener(rgbChangeListener)
        blueSeekBar.setOnSeekBarChangeListener(rgbChangeListener)
    }

    private fun updateColorPreview() {
        val red = findViewById<SeekBar>(R.id.seekBarRed).progress
        val green = findViewById<SeekBar>(R.id.seekBarGreen).progress
        val blue = findViewById<SeekBar>(R.id.seekBarBlue).progress
        colorPreview.setBackgroundColor(Color.rgb(red, green, blue))
    }
}
