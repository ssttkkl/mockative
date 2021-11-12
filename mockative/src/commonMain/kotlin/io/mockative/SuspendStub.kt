package io.mockative

import io.mockative.concurrency.AtomicList

internal class SuspendStub(val expectation: Expectation, private val invoke: suspend (Array<Any?>) -> Any?) {
    var invocations = AtomicList<Invocation>()

    suspend fun invoke(invocation: Invocation): Any? {
        val arguments = when (invocation) {
            is Invocation.Function -> invocation.arguments.toTypedArray()
            is Invocation.Getter -> emptyArray()
            is Invocation.Setter -> arrayOf(invocation.value)
        }

        val result = invoke(arguments)
        invocations.add(invocation)
        return result
    }
}
