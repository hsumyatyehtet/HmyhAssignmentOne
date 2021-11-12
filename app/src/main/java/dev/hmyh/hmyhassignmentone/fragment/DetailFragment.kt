package dev.hmyh.hmyhassignmentone.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dev.hmyh.hmyhassignmentone.R
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import dev.hmyh.hmyhassignmentone.utils.getApiMovieDate
import dev.hmyh.hmyhassignmentone.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment: Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private lateinit var mViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        setUpListener()
        setUpDataObservation()

    }

    private fun setUpListener() {
        ivBack.setOnClickListener {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    private fun setUpDataObservation() {
        var movieId = args.movieId

        mViewModel.getMovieById(movieId).observe(viewLifecycleOwner, Observer {
            it?.let { movieListVO->
                bindData(movieListVO)
            }
        })

    }

    private fun bindData(movieList: MovieListVO) {

        var photoPath ="http://image.tmdb.org/t/p/w500"

        Glide.with(this)
            .load(photoPath+movieList.posterPath)
            .into(ivMovieDetail)
        tvMovieDetailTitle.text = movieList.title ?: ""
        tvMovieDetailOverview.text = movieList.overView ?: ""
        tvMovieDetailYear.text = getApiMovieDate(movieList.releaseDate.toString())
        tvMovieDetailPopular.text = movieList.popularity.toString()
        tvMovieDetailVountCount.text = movieList.voteCount.toString()

    }

}