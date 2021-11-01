package dk.nillerr.mockative

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise

private val testScope = MainScope()

actual fun runBlockingTest(block: suspend CoroutineScope.() -> Unit): dynamic =
    testScope.promise { block() }
