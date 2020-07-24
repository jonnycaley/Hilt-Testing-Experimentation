package com.example.hilt_testing_experimentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hilt_testing_experimentation.data.model.detailedpokemon.DetailedPokemonDto
import com.example.hilt_testing_experimentation.di.schedulers.Schedulers
import com.example.hilt_testing_experimentation.usecase.GetPokemon
import com.example.hilt_testing_experimentation.utils.Resource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

class MainViewModel @ViewModelInject constructor(
    private val getPokemon: GetPokemon,
    private val schedulers: Schedulers
) : ViewModel() {

    private var nextPage: String? = null

    private val _pokemon: MutableLiveData<Resource<List<DetailedPokemonDto>>> = MutableLiveData(Resource.loading())
    val pokemon: LiveData<Resource<List<DetailedPokemonDto>>>
        get() = _pokemon

    private var disposable = CompositeDisposable()

    init {
        loadPokemon()
    }

    fun loadPokemon() {
        _pokemon.value = Resource.loading()

        val pokemonList = mutableListOf<DetailedPokemonDto>()

        getPokemon()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .subscribe({ pokemon ->
                pokemonList.add(pokemon)
            },::setError,{
                _pokemon.value = Resource.success(pokemonList)
            })
            .addTo(disposable)
    }

    private fun setError(throwable: Throwable) {
        _pokemon.value = Resource.error(throwable.message ?: "")
    }
}