package com.tori.schibsted.common

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{

    @Test
    fun whenInputIsValid(){
        val searchKey ="Dog"
        val result = Validator.validateInput(searchKey)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsNotValid(){
        val searchKey =""
        val result = Validator.validateInput(searchKey)
        assertThat(result).isEqualTo(false)
    }

}