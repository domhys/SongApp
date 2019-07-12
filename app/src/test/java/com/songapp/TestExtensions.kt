package com.songapp

import org.mockito.ArgumentCaptor

fun <T> ArgumentCaptor<T>.fixedCapture(): T {
    capture()
    return uninitialized()
}

private fun <T> uninitialized(): T = null as T