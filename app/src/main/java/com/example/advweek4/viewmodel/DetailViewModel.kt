package com.example.advweek4.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advweek4.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel : ViewModel() {
    val studentLiveData= MutableLiveData<Student>()

    fun fetch() {
        studentLiveData.value =
            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100" +
                    "jpg/cc0000/ffffff")


    }
}