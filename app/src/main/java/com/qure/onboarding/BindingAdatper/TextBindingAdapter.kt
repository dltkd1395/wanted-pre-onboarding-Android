package com.qure.onboarding.BindingAdatper

import android.annotation.SuppressLint
import android.os.Build
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.time.Instant
import androidx.annotation.RequiresApi as RequiresApi1

object TextBindingAdapter {

    @SuppressLint("SetTextI18n")
    @BindingAdapter("timeText")
    @JvmStatic
    @androidx.annotation.RequiresApi(Build.VERSION_CODES.O)
    fun timeText(textView : TextView, publisheAt:String) {

        val instant : Instant = Instant.now()
        val output = instant.toString()

        val now = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatTime(output))
        val before = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatTime(publisheAt))


        val diffSec     = (now.time - before.time) / 1000
        val diffMin     = (now.time - before.time) / (60*1000)
        val diffHor     = (now.time - before.time) / (60 * 60 * 1000)
        val diffDays    = diffSec / (24 * 60 * 60)
        val diffMonths  = (now.year*12 + now.month) - (before.year*12 + before.month)
        val diffYears   = now.year - before.year

        if(diffYears > 0){
            textView.setText("${diffYears} years ago")
            println(diffYears)
        }
        else if(diffMonths > 0){
            textView.setText("${diffMonths} months ago")
            println(diffMonths)
        }
        else if (diffDays > 0){
            textView.setText("${diffDays} days ago")

        }
        else if(diffHor > 0){
            textView.setText("${diffHor} hours ago")

        }
        else if(diffMin > 0){
            textView.setText("${diffMin} minutes ago")
        }
        else if(diffSec > 0){
            textView.setText("${diffSec} seconds age")
        } else {
            textView.setText("")
        }

    }

    private fun formatTime(t:String) : String {

        var time = t.split("T").toMutableList()
        time[1] = time[1].substring(0, 8)

        return time[0] + " " + time[1]
    }

    @BindingAdapter("categoryText")
    @JvmStatic
    fun CategoryText(textView: TextView, category : String){

        val category = category.first().uppercase()+category.substring(1,category.length)

        textView.setText("Category - ${category}")

    }


}