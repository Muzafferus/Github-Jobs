package com.muzafferus.githubjobs.ui.fragment.job_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.muzafferus.githubjobs.R
import com.muzafferus.githubjobs.data.JobDatabase
import com.muzafferus.githubjobs.databinding.FragmentJobListBinding
import com.muzafferus.githubjobs.factory.JobListViewModelFactory
import com.muzafferus.githubjobs.view_model.JobListViewModel

class JobListFragment : Fragment() {

    private lateinit var viewModel: JobListViewModel
    private lateinit var binding: FragmentJobListBinding
    private val adapter: JobListAdapter = JobListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_job_list, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = JobDatabase.getInstance(application).jobDatabaseDao
        val viewModelFactory = JobListViewModelFactory(dataSource, application)
        viewModel = ViewModelProviders
            .of(this, viewModelFactory).get(JobListViewModel::class.java)


        binding.jobList.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.jobDataList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
        })

        adapter.setOnClickListener(onClickListener = {
            view?.findNavController()?.navigate(
                JobListFragmentDirections
                    .actionJobListFragmentToJobFragment(
                        it
                    )
            )
        })
    }

}
