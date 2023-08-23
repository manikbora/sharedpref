package com.example.sharedpref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.sharedpref.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var bgColor = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("com.example.sharedpref.shared_pref", MODE_PRIVATE)
        val editor = sharedPref.edit()

        bgColor = sharedPref.getString("COLOR_KEY", "no color").toString()
        if(bgColor != "no color") {
            setLayoutBackgroundColor(bgColor)
        }

        binding.btnChangeBackground.setOnClickListener {
            bgColor = binding.spColor.selectedItem.toString()
            setLayoutBackgroundColor(bgColor)

            editor.putString("COLOR_KEY", bgColor)
            editor.apply()
        }

        binding.btnClear.setOnClickListener {
            editor.clear()
            editor.apply()
            binding.root.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.white))
        }

    }

    private fun setLayoutBackgroundColor(color: String) {
        when(color) {
            "Red" -> binding.root.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.red))
            "Green" -> binding.root.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.green))
            "Blue" -> binding.root.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.blue))
        }
    }
}