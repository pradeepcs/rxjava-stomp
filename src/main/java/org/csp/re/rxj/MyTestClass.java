package org.csp.re.rxj;

import io.vertx.core.Vertx;
import io.vertx.ext.stomp.StompClient;
import io.vertx.ext.stomp.StompClientConnection;
import rx.Observable;
import rx.Subscription;
import rx.subjects.PublishSubject;

public class MyTestClass {

	public static void main(String... strings) {
		/*
		 * Observable<String> observable = Observable.create(new
		 * Observable.OnSubscribe<String>() {
		 * 
		 * @Override public void call(Subscriber<? super String> subscriber) {
		 * 
		 * StompClient.create(Vertx.vertx()).connect(ar -> { if (ar.succeeded())
		 * { StompClientConnection connection = ar.result();
		 * connection.subscribe("/topic/logstash", frame ->
		 * subscriber.onNext(frame.getBodyAsString())); } else {
		 * System.out.println("Failed to connect to the STOMP server: " +
		 * ar.cause().toString()); } });
		 * 
		 * } }); Subscription sub = observable.subscribe(new MyObserver());
		 * while(!sub.isUnsubscribed());
		 */

		ObservableList<String> olist = new ObservableList<>();

		StompClient.create(Vertx.vertx()).connect(ar -> {
			if (ar.succeeded()) {
				StompClientConnection connection = ar.result();
				connection.subscribe("/topic/logstash", frame -> olist.add(frame.getBodyAsString()));
			} else {
				System.out.println("Failed to connect to the STOMP server: " + ar.cause().toString());
			}
		});

		Subscription sub = olist.getObservable().subscribe(new MyObserver());
		while (!sub.isUnsubscribed());

		System.out.println("End of main");
	}

	public static class ObservableList<T> {

		protected final PublishSubject<T> onAdd;

		public ObservableList() {
			this.onAdd = PublishSubject.create();
		}

		public void add(T value) {
			onAdd.onNext(value);
		}

		public Observable<T> getObservable() {
			return onAdd;
		}
	}

}
