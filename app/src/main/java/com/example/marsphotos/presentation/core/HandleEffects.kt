package com.example.marsphotos.presentation.core

import android.os.SystemClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun <SideEffect : ViewSideEffect> HandleEffects(
    effects: Flow<SideEffect>,
    handleEffect: suspend (SideEffect) -> Unit
) {
    val effectThrottleTime = 500L

    DisposableEffect(Unit) {
        val effectsJob = CoroutineScope(SupervisorJob() + Dispatchers.Main).launch {
            effects.throttle(effectThrottleTime).collectLatest { handleEffect(it) }
        }

        onDispose {
            effectsJob.cancel()
        }
    }
}

private fun <T> Flow<T>.throttle(waitMillis: Long) = flow {
    coroutineScope {
        val context = coroutineContext
        var nextMillis = 0L
        var delayPost: Deferred<Unit>? = null
        collect {
            val current = SystemClock.uptimeMillis()
            if (nextMillis < current) {
                nextMillis = current + waitMillis
                emit(it)
                delayPost?.cancel()
            } else {
                val delayNext = nextMillis
                delayPost?.cancel()
                delayPost = async(Dispatchers.Default) {
                    delay(nextMillis - current)
                    if (delayNext == nextMillis) {
                        nextMillis = SystemClock.uptimeMillis() + waitMillis
                        withContext(context) {
                            emit(it)
                        }
                    }
                }
            }
        }
    }
}