package com.example.hilt_testing_experimentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hilt_testing_experimentation.di.schedulers.AppSchedulers
import com.example.hilt_testing_experimentation.domain.PokeRepository
import com.example.hilt_testing_experimentation.domain.detailedpokemon.DetailedPokemon
import com.example.hilt_testing_experimentation.utils.Resource
import com.example.hilt_testing_experimentation.utils.getOffsetFromUrl
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

class MainViewModel @ViewModelInject constructor(
    private val pokeRepository: PokeRepository,
    private val schedulers: AppSchedulers
) : ViewModel() {

    private val pokemonList = mutableListOf<DetailedPokemon>()

    private val _pokemon: MutableLiveData<Resource<List<DetailedPokemon>>> = MutableLiveData(Resource.loading())
    val pokemon: LiveData<Resource<List<DetailedPokemon>>>
        get() = _pokemon

    private val _nextPageOffset: MutableLiveData<Int?> = MutableLiveData(0)
    val nextPageOffset: LiveData<Int?>
        get() = _nextPageOffset

    private var disposable = CompositeDisposable()

    init {
        _pokemon.value = Resource.loading()
        loadPokemon()
    }

    fun loadPokemon(offset: Int = 0) {
        pokeRepository.getPokemonList(offset)
            .doOnSuccess { _nextPageOffset.postValue(it.next?.getOffsetFromUrl()) }
            .flattenAsObservable { it.results }
            .flatMap { pokeRepository.getDetailedPokemon(it.name).toObservable() }
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

    fun loadMorePokemon(offset: Int) {
        loadPokemon(offset)
    }
}