package com.maid.cleanhires.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maid.cleanhires.repositories.ServiceRepository

class ViewModelProviderFactory(
    private val repository: ServiceRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ServiceViewModel(repository) as T
    }
}