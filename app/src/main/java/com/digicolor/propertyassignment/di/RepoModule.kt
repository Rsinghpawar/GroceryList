package com.digicolor.propertyassignment.di

import com.digicolor.propertyassignment.data.repo.GroceryRepoImpl
import com.digicolor.propertyassignment.domain.GroceryRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    @Singleton
    fun bindRepo(groceryRepoImpl: GroceryRepoImpl): GroceryRepo
}