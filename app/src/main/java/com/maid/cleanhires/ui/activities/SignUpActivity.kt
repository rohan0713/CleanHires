package com.maid.cleanhires.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.maid.cleanhires.databinding.ActivitySignUpBinding
import com.maid.cleanhires.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivitySignUpBinding.inflate(layoutInflater).also { binding = it }.root)

        window.statusBarColor = android.graphics.Color.WHITE

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnSignUp.setOnClickListener {

            val email = binding.etEmail.text
            val password = binding.etPassword.text
            val confirmPassword = binding.etConfirm.text

            if (email != null && password != null && confirmPassword != null) {

                if (password.toString() == confirmPassword.toString()) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val response = RetrofitClient.userApi.createAccount(
                            email.toString(),
                            password.toString()
                        );
                        if (response.isSuccessful) {
                            withContext(Dispatchers.Main) {
                                val flag = response.body().let {
                                    it?.status
                                }
                                if (flag == true) {
                                    Toast.makeText(
                                        binding.root.context,
                                        response.body()?.message.toString(),
                                        Toast.LENGTH_LONG
                                    ).show()
                                    finish()
                                } else {
                                    Toast.makeText(
                                        binding.root.context,
                                        response.body()?.message.toString(),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        binding.root.context,
                        "Password is not matching",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}