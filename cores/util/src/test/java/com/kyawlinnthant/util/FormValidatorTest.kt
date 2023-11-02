package com.kyawlinnthant.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
- at least one Upper letter
- at least one Lower letter
- at least one digit
- at least one special character !@#$%^&+-
- no space in password
- at least 6 chars
- not exceed 24 chars
 */
class FormValidatorTest {

    @Test
    fun `empty email returns false`() {
        val result = FormValidator.isVerifiedEmail(
            email = ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `invalid email returns false`() {
        val result = FormValidator.isVerifiedEmail(
            email = "123Apple"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid email returns true`() {
        val result = FormValidator.isVerifiedEmail(
            email = "dev.kyawlinnthant@gmail.com"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `empty password returns false`() {
        val result = FormValidator.isVerifiedPassword(
            password = ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password length less than 6 returns false`() {
        val result = FormValidator.isVerifiedPassword(
            password = "1234"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password length larger than 24 returns false`() {
        val result = FormValidator.isVerifiedPassword(
            password = "123456789012345678901234567890"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password does not contain Uppercase letter returns false`() {
        val result = FormValidator.isVerifiedPassword(
            password = "12345678"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password does not contain Lowercase letter returns false`() {
        val result = FormValidator.isVerifiedPassword(
            password = "12345678"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password does not contain Digit letter returns false`() {
        val result = FormValidator.isVerifiedPassword(
            password = "abcdefghijk"
        )
        assertThat(result).isFalse()
    }

    //    "!@#$%^&+-"
    @Test
    fun `password does not contain Special Character returns false`() {
        val result = FormValidator.isVerifiedPassword(
            password = "abcdefghijk"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password contains Space between letter returns false`() {
        val result = FormValidator.isVerifiedPassword(
            password = "abcd efghijk"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `correct password returns true`() {
        val result = FormValidator.isVerifiedPassword(
            password = "Apple!12345"
        )
        assertThat(result).isTrue()
    }
}