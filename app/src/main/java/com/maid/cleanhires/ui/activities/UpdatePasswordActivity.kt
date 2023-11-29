package com.maid.cleanhires.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityUpdatePasswordBinding
import com.maid.cleanhires.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdatePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdatePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityUpdatePasswordBinding.inflate(layoutInflater).also { binding = it }.root)

        window.statusBarColor = Color.WHITE

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnUpdate.setOnClickListener {

            val email = binding.etEmail.text
            val password = binding.etPassword.text
            val confirmPassword = binding.etConfirm.text

            if (email != null && password != null && confirmPassword != null) {

                if (password.toString() == confirmPassword.toString()) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val response = RetrofitClient.userApi.updatePassword(
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