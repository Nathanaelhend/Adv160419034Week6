package com.example.advweek4.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advweek4.R
import com.example.advweek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_list.*

class StudentListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private var studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = studentListAdapter

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE

            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
            txtError.visibility= View.GONE
        }
        observeViewModel()
    }


    private fun observeViewModel() {
        viewModel.studentLiveData.observe(viewLifecycleOwner){
            studentListAdapter.updateStudentList(it)
        }
        viewModel.studentsLoadErrorLiveData.observe(viewLifecycleOwner){
            txtError.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){ //sedang loading
                recView.visibility = View.GONE
                progressLoad.visibility  = View.VISIBLE
            }
            else{
                recView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
                txtError.visibility = View.GONE
            }
        }
    }
}