package com.nkodem.calendarz

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

fun por(d1:String,d2:String):Int {
    var startDateValue = Date(d1)
    var endDateValue = Date(d2)

    val mdiff=kotlin.math.abs(startDateValue.time-endDateValue.time)
    val mdiffdates=mdiff/(24*3600)
    val diff: Long = endDateValue.getTime() - startDateValue.getTime()
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24 + 1
    return(mdiffdates.toInt())
}
class MainActivity : AppCompatActivity() {
    var dod: String = Date().toString()
    var ddo: String = Date().toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val guzik = findViewById<Button>(R.id.guzik)
        val cod = findViewById<CalendarView>(R.id.calOd)
        val cdo = findViewById<CalendarView>(R.id.calDo)
        val label = findViewById<TextView>(R.id.textView)
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        cod.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var mies=month+1
            if (mies>9&&dayOfMonth>9) {
                dod = "$dayOfMonth/$mies/$year"
            }
            else if (mies>9){
                dod = "0$dayOfMonth/$mies/$year"
            }
            else if(dayOfMonth>9){
                dod="$dayOfMonth/0$mies/$year"
            }
            else{
                dod="0$dayOfMonth/0$mies/$year"
            }
        }
        cdo.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var mies=month+1
            if (mies>9&&dayOfMonth>9) {
                ddo = "$dayOfMonth/$mies/$year"
            }
            else if (mies>9){
                ddo = "0$dayOfMonth/$mies/$year"
            }
            else if(dayOfMonth>9){
                ddo="$dayOfMonth/0$mies/$year"
            }
            else{
                ddo="0$dayOfMonth/0$mies/$year"
            }
        }
        guzik.setOnClickListener {
            if(por(dod,ddo)>0 && por(sdf.format(cod.date),dod)>0) {
                label.setText("Przyjazd: $dod\nWyjazd: $ddo\nWyciaczka trwa ${ddo.compareTo(dod)+1} dni")
            }
            else{
                label.setText("Źle wybrana data!")
            }

        }
    }
}