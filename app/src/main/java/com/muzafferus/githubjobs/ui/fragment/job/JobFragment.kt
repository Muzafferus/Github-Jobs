package com.muzafferus.githubjobs.ui.fragment.job


import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.muzafferus.githubjobs.R
import com.muzafferus.githubjobs.databinding.FragmentJobBinding

class JobFragment : Fragment() {

    private lateinit var binding: FragmentJobBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_job, container, false
            )
        val args =
            JobFragmentArgs.fromBundle(arguments!!)
        binding.job = args.selectJobData
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.textViewJobDescription.text =
                Html.fromHtml(binding.job?.description, Html.FROM_HTML_MODE_LEGACY)
        } else {
            binding.textViewJobDescription.text =
                Html.fromHtml(binding.job?.description)
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.textViewJobApply.text =
                Html.fromHtml(binding.job?.how_to_apply, Html.FROM_HTML_MODE_LEGACY)
        } else {
            binding.textViewJobApply.text =
                Html.fromHtml(binding.job?.how_to_apply)
        }

        binding.textViewJobType.text = "%s/%s".format(binding.job?.type, binding.job?.location)
        super.onViewCreated(view, savedInstanceState)
    }

}
