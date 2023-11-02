package com.maid.cleanhires.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityDetailsBinding
import com.maid.cleanhires.ui.adapters.ReviewsAdapter
import com.maid.cleanhires.utils.DateAndTimePicker
import java.text.SimpleDateFormat
import java.util.Locale

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityDetailsBinding.inflate(layoutInflater).also { binding = it }.root)

        window.statusBarColor = Color.WHITE
        binding.rvReviews.layoutManager = LinearLayoutManager(this)
        binding.rvReviews.adapter = ReviewsAdapter()

        binding.btnBook.setOnClickListener {

            val datePicker = DateAndTimePicker().datePicker
            datePicker.show(supportFragmentManager, "DATE_PICKER")

            datePicker.addOnPositiveButtonClickListener {
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val date = sdf.format(it)

                val picker = DateAndTimePicker().timePicker
                picker.show(supportFragmentManager, "tag");

                picker.addOnPositiveButtonClickListener {
                    val hour = picker.hour
                    val minute = picker.minute

                    Log.d("time", "$date $hour $minute")
                }
            }

        }
    }
}