package com.carbit3333333.cpyptoappdroid.actovitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carbit3333333.cpyptoappdroid.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
