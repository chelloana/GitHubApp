package com.dicoding.githubappuser.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.githubappuser.api.RetrofitClient
import com.dicoding.githubappuser.databinding.ActivityDetailUserBinding
import com.dicoding.githubappuser.databinding.ActivityMainBinding
import com.dicoding.githubappuser.ui.factory.DetailViewModelFactory
import com.dicoding.githubappuser.ui.factory.FollowsViewModelFactory

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        // ??
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

//        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailUserViewModel::class.java]

        viewModel = ViewModelProvider(
            this,
            DetailViewModelFactory(username!!, RetrofitClient.apiInstance)
        )[DetailUserViewModel::class.java]

        observeLoading()

//        viewModel.setUserDetail(username)


        viewModel.user.observe(this) {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(binding.ivProfile)
                }
            }
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }

    }

    private fun observeLoading() {
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }
}