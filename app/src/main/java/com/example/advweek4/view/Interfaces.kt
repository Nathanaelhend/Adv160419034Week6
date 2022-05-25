package com.example.advweek4.view

import android.view.View
import com.example.advweek4.model.Student

interface StudentDetailClickListener {
    fun onStudentDetailClick(view: View)
}

interface NotificationListener {
    fun onNotificationClick(view: View)
}

interface UpdateListener {
    fun onUpdateClick(view: View)
}