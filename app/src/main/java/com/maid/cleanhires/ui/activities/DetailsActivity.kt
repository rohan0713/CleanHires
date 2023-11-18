package com.maid.cleanhires.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat
import com.maid.cleanhires.R
import com.maid.cleanhires.data.local.room.ServiceDatabase
import com.maid.cleanhires.data.models.CartItems
import com.maid.cleanhires.data.models.ReviewsItem
import com.maid.cleanhires.databinding.ActivityDetailsBinding
import com.maid.cleanhires.repositories.CartRepository
import com.maid.cleanhires.ui.adapters.ReviewsAdapter
import com.maid.cleanhires.ui.viewmodels.CartViewModel
import com.maid.cleanhires.utils.DateAndTimePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var repository: CartRepository
    private val viewModel : CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityDetailsBinding.inflate(layoutInflater).also { binding = it }.root)

        window.statusBarColor = Color.WHITE

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//        val flag = sharedPreferences.getBoolean("cart", false)
//        var cartItemCount = sharedPreferences.getInt("item", 0)
//        Log.d("flag", flag.toString())
//
//        if(flag){
//
//            val charges = intent.getStringExtra("charges")
//            val amount = charges?.toInt()?.times(cartItemCount)
//            binding.tvTotalAmount.text = "Total Amount:\n₹$amount"
//            binding.linearLayoutFooter.visibility = View.VISIBLE
//
//        }else{
//            binding.linearLayoutFooter.visibility = View.GONE
//        }

        val name = intent.getStringExtra("name")
        val location = intent.getStringExtra("location")
        val rating = intent.getFloatExtra("rating", 5.0F)
        val charges = intent.getStringExtra("charges")
        val count = intent.getStringExtra("count")
        val duration = intent.getStringExtra("duration")
        val joined = intent.getStringExtra("joined")
        val img = intent.getStringExtra("image")
        val title = sharedPreferences.getString("title", "Food-Cooking")

        val list = intent.getSerializableExtra("reviews") as List<ReviewsItem>

        binding.tvWorkerName.text = name
        binding.tvWorkerLocation.text = location
        binding.tvPrice.text = "₹ $charges"
        binding.tvDuration.text = "• $duration mins"
        binding.tvJoined.text = "Serving Clean hires since $joined"
        binding.tvBooking.text = "Completed $count Bookings"
        binding.ratingBar.rating = rating

        Glide.with(binding.root).load(img).into(binding.ivPerson)

        viewModel.totalAmount.observe(this) {sum ->

            if(sum != null){
                binding.tvTotalAmount.text = "Total Amount:\n₹${sum.toString()}"
                binding.linearLayoutFooter.visibility = View.VISIBLE
            }else{
                binding.linearLayoutFooter.visibility = View.GONE
            }
        }

        binding.rvReviews.layoutManager = LinearLayoutManager(this)
        binding.rvReviews.adapter = ReviewsAdapter(list)

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

                    val bookingTime = "$date $hour:$minute"

                    Log.d("time", "$title - $bookingTime")
                    viewModel.insertIntoCart(CartItems(name!!, title!!, bookingTime, charges?.toInt()!!))

                    Toast.makeText(this, "Booking done successfully", Toast.LENGTH_SHORT).show()

//                    editor.putBoolean("cart", true)
//                    editor.putInt("item", ++cartItemCount)
//                    editor.apply()

//                    val amount = charges?.toInt()?.times(cartItemCount)
//                    binding.tvTotalAmount.text = "Total Amount:\n₹$amount"
                }
            }

        }

        binding.btnViewCart.setOnClickListener {
            Intent(this, CartActivity::class.java).also { startActivity(it) }
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}