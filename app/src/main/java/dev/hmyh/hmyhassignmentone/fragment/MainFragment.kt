package dev.hmyh.hmyhassignmentone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import dev.hmyh.hmyhassignmentone.R
import dev.hmyh.hmyhassignmentone.adapter.MovieAdapter
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import dev.hmyh.hmyhassignmentone.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var mAdapter: MovieAdapter
    private lateinit var mMovieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        setUpRecyclerView()
        setUpListener()

        setUpOnUiReady()
        setUpDataObservations()
    }

    private fun setUpOnUiReady() {
        mMovieViewModel.onUiReady()
    }

    private fun setUpDataObservations() {
        mMovieViewModel.getMovieListLiveData().observe(viewLifecycleOwner, Observer {
            it?.let { topRatedMove->
                topRatedMove.movieList.let { movieList->
                    mAdapter.setNewData(movieList as MutableList<MovieListVO>)
                }
            }
        })
    }

    private fun setUpViewModel() {
        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        mAdapter = MovieAdapter()
        val layoutManager = GridLayoutManager(context, 2)
        rvMovie.layoutManager = layoutManager
        rvMovie.adapter = mAdapter

//        mAdapter.setNewData(
//            mutableListOf(
//                MovieListVO(1, "ShowDrop"), MovieListVO(2, "DP"),
//                MovieListVO(3, "Jirisan"), MovieListVO(4, "Mr.Queen"),
//                MovieListVO(5, "Start-Up"), MovieListVO(6, "DOTS")
//            )
//        )
    }

    private fun setUpListener() {
//        btnGoDetail.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_mainFragment_to_detailFragment
//            )
//        }
    }

}