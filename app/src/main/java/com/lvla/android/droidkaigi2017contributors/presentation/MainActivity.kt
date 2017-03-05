package com.lvla.android.droidkaigi2017contributors.presentation

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.lvla.android.droidkaigi2017contributors.R
import com.lvla.android.droidkaigi2017contributors.databinding.ActivityMainBinding
import com.lvla.android.droidkaigi2017contributors.databinding.ItemContributorBinding
import com.lvla.android.droidkaigi2017contributors.di.MainModule
import com.lvla.android.droidkaigi2017contributors.domain.entity.Contributor
import com.squareup.picasso.Picasso
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainPresenter.Contract {

  private val binding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }
  @Inject lateinit var presenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (application as App).component.plus(MainModule(this)).inject(this)
    binding.listView.numColumns = 4
    presenter.getContributors()
  }

  override fun onDestroy() {
    presenter.dispose()
    super.onDestroy()
  }

  override fun showContributors(contributors: List<Contributor>) {
    Timber.i("#showContributos")
    binding.listView.adapter = ContributorsAdapter(this, contributors)
  }

  class ContributorsAdapter(context: Context, contributors: List<Contributor>) : ArrayAdapter<Contributor>(context, 0, contributors) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
      val binding: ItemContributorBinding
      if (convertView == null) {
        binding = ItemContributorBinding.bind(View.inflate(context, R.layout.item_contributor, null))
        binding.root.tag = binding
      } else {
        binding = convertView.tag as ItemContributorBinding
      }

      val contributor = getItem(position)
      Picasso.with(context).load(contributor.avatarUrl).into(binding.avatarImage)

      return binding.root
    }
  }
}
