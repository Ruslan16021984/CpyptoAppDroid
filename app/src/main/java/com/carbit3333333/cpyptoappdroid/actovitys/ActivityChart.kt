package com.carbit3333333.cpyptoappdroid.actovitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.carbit3333333.cpyptoappdroid.R
import com.carbit3333333.cpyptoappdroid.chart.LatestChart
import com.carbit3333333.cpyptoappdroid.di.App
import com.carbit3333333.cpyptoappdroid.mvp.contract.LatestChartContract
import com.carbit3333333.cpyptoappdroid.mvp.presenters.LatestChartPresenter
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_chart.*
import kotlinx.android.synthetic.main.recycler_view_item.*
import java.text.DecimalFormat
import javax.inject.Inject

class ActivityChart : AppCompatActivity(), OnChartValueSelectedListener, LatestChartContract.View{
    @Inject
    lateinit var latestChart: LatestChart
    @Inject
    lateinit var presenter: LatestChartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        App.appComponent.inject(this)
        presenter.attach(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("name")
        val markerCapRank = intent.getIntExtra("marketCapRank", 0)
        val symbol = intent.getStringExtra("symbol")
        val marketCap = intent.getStringExtra("marketCap")
        val marketCapChangePercentage24h = intent?.getFloatExtra("marketCapChangePercentage24h", 0.0f)
        val priceChangePercentage24h = intent?.getFloatExtra("priceChangePercentage24h", 0.0f)
        val totalVolume = intent?.getFloatExtra("totalVolume", 0.0f)
        val ath = intent?.getFloatExtra("ath", 0.0f)
        val athChangePercentage = intent?.getFloatExtra("athChangePercentage", 0f)
        val circulatingSupply = intent?.getDoubleExtra("circulatingSupply", 0.0)
        val totalSupply = intent?.getDoubleExtra("totalSupply", 0.0)
        val image = intent.getStringExtra("image")

        Glide.with(this).load(image).into(ivCurrencyDetailIcon)

        supportActionBar?.title = name
        val df = DecimalFormat("#")
        df.maximumFractionDigits = 2

        tvDetailMarketCapRank.text = markerCapRank.toString()
        tvMarketCapChange.text = marketCapChangePercentage24h.toString()
        tvATH.text = ath.toString()
        tvAthChange.text = ath.toString()
        tvCirculationSupply.text = df.format(circulatingSupply)
        tvTotalSupply.text = totalSupply.toString()

        presenter.makeChart(intent.getStringExtra("id"))
        latestChart.initChart(chartCurrency)

    }

    override fun onNothingSelected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addEntryToChart(value: Float, date: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addEntryToChart(date: Float, value: Float) {
        latestChart.addEntry(value, date)
    }

    override fun showProgress() {
        progressChart.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressChart.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }
}
