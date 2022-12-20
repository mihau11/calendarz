package com.nkodem.calendarz

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

fun por(d1:String,d2:String):Int {
    var dz1=d1.split('/')[0].toInt()
    var ms1=d1.split('/')[1].toInt()
    var rk1=d1.split('/')[2].toInt()
    var dz2=d2.split('/')[0].toInt()
    var ms2=d2.split('/')[1].toInt()
    var rk2=d2.split('/')[2].toInt()
    var tab = arrayOf(0,31,28,31,30,31,30,31,31,30,31,30,31)
    for (i in 0..ms1-1){
        dz1=dz1+tab[i]
    }
    for (i in 0..ms2-1){
        dz2=dz2+tab[i]
    }
    dz1=dz1+((rk1-2021)*365)
    dz2=dz2+((rk2-2021)*365)
    var odp = dz2-dz1

    return(odp)
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
        val dzis = sdf.format(Date())
        dod=dzis
        ddo=dod
        cod.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var mies=month+1
            dod = "$dayOfMonth/$mies/$year"
        }
        cdo.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var mies=month+1
            ddo = "$dayOfMonth/$mies/$year"
        }
        guzik.setOnClickListener {
            val prr = por(dod,ddo)
            if(prr>=0 && por(dzis,dod)>=0&&prr<2*365) {
                label.setText("Przyjazd: $dod\nWyjazd: $ddo\nWyciaczka trwa ${prr+1} dni")
            }
            else{
                label.setText("Źle wybrana data!")
            }
        }
        findViewById<Button>(R.id.exit).setOnClickListener {
            finish()
        }
    }
}