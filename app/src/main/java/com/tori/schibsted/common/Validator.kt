package com.tori.schibsted.common

object Validator {

    //Unit Testing of search key input is valid or not
    fun validateInput(keyWord: String): Boolean{
        return !(keyWord.equals("") && keyWord.isEmpty())
    }
}