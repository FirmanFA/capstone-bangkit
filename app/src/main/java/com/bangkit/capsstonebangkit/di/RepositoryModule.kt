package com.bangkit.capsstonebangkit.di

import com.bangkit.capsstonebangkit.repository.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::UserRepository)
}