package com.example.typingpractice.di

import android.app.Application
import androidx.room.Room
import com.example.typingpractice.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideScoreDatabase(app: Application): ScoreDatabase {
        return Room.databaseBuilder(
            app,
            ScoreDatabase::class.java,
            ScoreDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideScoreRepository(db: ScoreDatabase): ScoreRepository {
        return ScoreRepositoryImpl(db.scoreDao)
    }

    @Provides
    @Singleton
    fun provideLetterRepository(db: ScoreDatabase): LetterRepository {
        return LetterRepositoryImpl(db.letterDao)
    }

    @Provides
    @Singleton
    fun provideGetScores(repository: ScoreRepository): GetScores {
        return GetScores(repository)
    }

    @Provides
    @Singleton
    fun provideGetLetters(repository: LetterRepository): GetLetters {
        return GetLetters(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteScore(repository: ScoreRepository): DeleteScore {
        return DeleteScore(repository)
    }

    @Provides
    @Singleton
    fun provideInsertScore(repository: ScoreRepository): InsertScore {
        return InsertScore(repository)
    }

    @Provides
    @Singleton
    fun provideInsertLetter(repository: LetterRepository): InsertLetter {
        return InsertLetter(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteAllScores(repository: ScoreRepository): DeleteAllScores {
        return DeleteAllScores(repository)
    }


}