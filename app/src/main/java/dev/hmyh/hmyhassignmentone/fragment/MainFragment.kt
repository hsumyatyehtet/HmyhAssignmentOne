package dev.hmyh.hmyhassignmentone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dev.hmyh.hmyhassignmentone.R
import dev.hmyh.hmyhassignmentone.adapter.MovieAdapter
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import dev.hmyh.hmyhassignmentone.utils.getBundleMovieDetail
import dev.hmyh.hmyhassignmentone.viewmodels.MovieViewModel
import io.reactivex.disposables.CompositeDisposable
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

        mMovieViewModel.getNavigateToMovieDetailLiveData().observe(viewLifecycleOwner, Observer {movie->

            if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED){
                movie.id?.let {
                    findNavController().navigate(
                        R.id.action_mainFragment_to_detailFragment,
                        getBundleMovieDetail(it)
                    )
                }
            }

        })

    }

    private fun setUpViewModel() {
        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        mAdapter = MovieAdapter(mMovieViewModel)
        val layoutManager = GridLayoutManager(context, 2)
        rvMovie.layoutManager = layoutManager
        rvMovie.adapter = mAdapter

    }


}