package com.m21droid.github.domain.usecases

interface BaseUseCase<In, Out> {

    fun execute(input: In): Out

}