package com.example.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4.R
import com.example.advweek4.databinding.StudentListItemBinding
import com.example.advweek4.model.Student
import com.example.advweek4.util.loadImage
import kotlinx.android.synthetic.main.fragment_student_detail.view.*
import kotlinx.android.synthetic.main.fragment_student_list.view.*
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter (val studentList: ArrayList<Student>) : RecyclerView
.Adapter<StudentListAdapter.studentViewHolder>(), StudentDetailClickListener {
    class studentViewHolder(var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): studentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = StudentListItemBinding.inflate(inflater, parent, false)
        return studentViewHolder(view)
    }

    override fun onBindViewHolder(holder: studentViewHolder, position: Int) {
        with(holder.view) {
            student = studentList[position]
            detailListener = this@StudentListAdapter
        }
        /*
        val student = studentList[position]
        with(holder.view) {
            textID.text = student.id
            textName.text = student.name
            buttonDetail.setOnClickListener {
                val action =
                    StudentListFragmentDirections.actionStudentDetail(student.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            imageStudentPhoto.loadImage(student.photoUrl, progressLoadingStudentPhoto)
        }
         */
    }

    override fun getItemCount() = studentList.size

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onStudentDetailClick(view: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(view.tag.toString())
        Navigation.findNavController(view).navigate(action)
    }
}