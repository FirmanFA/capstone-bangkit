package com.bangkit.capsstonebangkit.ui.community

import android.os.Bundle
import android.view.View
import com.bangkit.capsstonebangkit.R
import com.bangkit.capsstonebangkit.databinding.ActivityCommunityBinding
import com.bangkit.capsstonebangkit.ui.BaseActivity
import com.bangkit.capsstonebangkit.ui.community.agenda.CommunityAgendaFragment
import com.bangkit.capsstonebangkit.ui.community.member.CommunityMemberFragment

class CommunityActivity : BaseActivity<ActivityCommunityBinding>() {

    override fun getViewBinding() = ActivityCommunityBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnAgenda.setOnClickListener {
            binding.btnAnggota.alpha = 0.5f
            binding.btnAgenda.alpha = 1f

            supportFragmentManager.beginTransaction()
                .replace(R.id.community_fragment_container, CommunityAgendaFragment())
                .commit()

        }

        binding.btnAnggota.setOnClickListener {
            binding.btnAgenda.alpha = 0.5f
            binding.btnAnggota.alpha = 1f

            supportFragmentManager.beginTransaction()
                .replace(R.id.community_fragment_container, CommunityMemberFragment())
                .commit()

        }

        binding.btnCloseToken.setOnClickListener {
            binding.cvShareToken.visibility = View.GONE
            binding.btnNewAgenda.visibility = View.VISIBLE
        }


    }
}