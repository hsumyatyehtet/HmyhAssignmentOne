package dev.hmyh.hmyhassignmentone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dev.hmyh.hmyhassignmentone.R
import dev.hmyh.hmyhassignmentone.adapter.MovieAdapter
import dev.hmyh.hmyhassignmentone.data.vos.MovieVO
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var mAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpListener()
    }

    private fun setUpRecyclerView() {
        mAdapter = MovieAdapter()
        val layoutManager = GridLayoutManager(context, 2)
        rvMovie.layoutManager = layoutManager
        rvMovie.adapter = mAdapter

        mAdapter.setNewData(
            mutableListOf(
                MovieVO(1, "ShowDrop"), MovieVO(2, "DP"),
                MovieVO(3, "Jirisan"), MovieVO(4, "Mr.Queen"),
                MovieVO(5, "Start-Up"), MovieVO(6, "DOTS")
            )
        )
    }

    private fun setUpListener() {
//        btnGoDetail.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_mainFragment_to_detailFragment
//            )
//        }
    }

}