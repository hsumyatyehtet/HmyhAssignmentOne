package dev.hmyh.hmyhassignmentone.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.hmyh.hmyhassignmentone.data.vos.MovieListVO
import dev.hmyh.hmyhassignmentone.data.vos.TopRatedMovieVO
import dev.hmyh.hmyhassignmentone.persistence.daos.MovieListDao
import dev.hmyh.hmyhassignmentone.persistence.daos.TopRatedMovieDao
import dev.hmyh.hmyhassignmentone.persistence.typeconverters.MovieTypeConverter

@Database(
    entities = [
        TopRatedMovieVO::class,
        MovieListVO::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    MovieTypeConverter::class
)
abstract class HmyhAssignmentOneDatabase: RoomDatabase() {

    abstract fun topRatedMovieDao(): TopRatedMovieDao
    abstract fun movieListDao(): MovieListDao

    companion object {

        @Volatile
        private var INSTANCE: HmyhAssignmentOneDatabase? = null

        private const val DB_NAME = "HmyhAssignmentOne.db"

        fun getDatabase(context: Context): HmyhAssignmentOneDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HmyhAssignmentOneDatabase::class.java, DB_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

    }
}