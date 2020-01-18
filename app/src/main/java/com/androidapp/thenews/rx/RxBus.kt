package com.androidapp.thenews.rx

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by S.Nur Uysal on 2019-11-03.
 */
// Use object so we have a singleton instance
object RxBus {

    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    // Listen should return an Observable and not the publisher
    // Using ofType we filter only events that match that class type
    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}