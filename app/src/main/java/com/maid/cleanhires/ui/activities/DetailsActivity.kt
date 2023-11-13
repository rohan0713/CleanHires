package com.maid.cleanhires.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
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

        val name = intent.getStringExtra("name")
        val location = intent.getStringExtra("location")
        val rating = intent.getFloatExtra("rating", 5.0F)
        val charges = intent.getStringExtra("charges")
        val count = intent.getStringExtra("count")
        val duration = intent.getStringExtra("duration")
        val joined = intent.getStringExtra("joined")
        val img = intent.getStringExtra("image")

        binding.tvWorkerName.text = name
        binding.tvWorkerLocation.text = location
        binding.tvPrice.text = "₹ $charges"
        binding.tvDuration.text = "• $duration mins"
        binding.tvJoined.text = "Serving Clean hires since $joined"
        binding.tvBooking.text = "Completed $count Bookings"
        binding.ratingBar.rating = rating

        Glide.with(binding.root).load(img).into(binding.ivPerson)

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
                    Toast.makeText(this, "Booking done successfully", Toast.LENGTH_SHORT).show()
                    binding.linearLayoutFooter.visibility = View.VISIBLE
                }
            }

        }

        binding.btnViewCart.setOnClickListener {
            Intent(this, CartActivity::class.java).also { startActivity(it) }
        }
    }
}