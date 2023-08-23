package com.z.chatapplication.di

import com.z.chatapplication.interactors.home.ChangeCategory

class ChangeCategoryModule() {

    val changeCategory: ChangeCategory by lazy {
        ChangeCategory()
    }
}