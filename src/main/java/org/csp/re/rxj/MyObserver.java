package org.csp.re.rxj;

import rx.Subscriber;

public class MyObserver extends Subscriber<String> {

	@Override
	public void onCompleted() {
		System.out.println("OnComplete");
		
	}

	@Override
	public void onError(Throwable arg0) {
		arg0.printStackTrace();
	}

	@Override
	public void onNext(String arg0) {
		System.out.println("OnNext : " + arg0);
		if(arg0.startsWith("{\"message\":\"2016-09-26")) {
			onCompleted();
			unsubscribe();
		}
	}

}
