package com.example.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.advweek4.R
import com.example.advweek4.databinding.FragmentStudentDetailBinding
import com.example.advweek4.model.Student
import com.example.advweek4.util.loadImage
import com.example.advweek4.viewmodel.DetailViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_detail.view.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), NotificationListener, UpdateListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //dataBinding = DataBindingUtil.inflate(inflater, R.layout
        //    .fragment_student_detail, container, false)

        dataBinding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return dataBinding.root
        //return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(arguments != null) {
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentDetail
            viewModel.fetch(studentId)
        }

        dataBinding.notification = this
        dataBinding.update = this
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.studentLiveData.observe(viewLifecycleOwner){
            dataBinding.student = it
        }
    }

    override fun onNotificationClick(view: View) {
        viewModel.studentLiveData.observe(viewLifecycleOwner) {
            val student = it
            student?.let {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("mynotif", "Notification delayed after 5 seconds")
                        student.name?.let { it1 ->
                            MainActivity.showNotification(
                                it1, "Notification created", R
                                    .drawable.ic_baseline_person_24
                            )
                        }
                    }
            }
        }
    }

    override fun onUpdateClick(view: View) {
        Toast.makeText(view.context, "UPDATED", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }
}