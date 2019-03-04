# FutureNovaJava

FutureNovaJava is our networking wrapper using Futures. Please ignore the name, it was made using a name generator. 

## Example

To run the example project, clone the repo, and run the app from the `app` folder.

Customize the Endpoint class for less repition.

You usually can include this in the MainActivity.java in the `onCreate` method.
```java
Endpoint.Config config = new Endpoint.Config();
config.scheme = Optional.of("https");
config.host = Optional.of("transit-backend.cornellappdev.com");
config.commonPath = Optional.of("/api/v1");
Endpoint.config = config;
```

Then in any activity, you can create your Endpoints.

```java
Endpoint allStopsEndpoint = new Endpoint().path("allstops").method(Endpoint.Method.GET);
```

Finally, call your endpoint!

```java
FutureNovaRequest.make(Stops[].class, allStopsEndpoint).thenAccept(response -> {
    System.out.println(response.getSuccess());
    // Update UI (make sure to run on the main thread when updating UI elements!)
});

...

```

## Installation

Add the following to your root `build.gradle` file

```gradle
allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://www.jitpack.io" } //<-- Just add this line at the end
    }
}
```

Add the following implementations to your `app/build.gradle` file

```gradle
implementation 'com.squareup.okhttp3:okhttp:3.13.1'
implementation 'com.google.code.gson:gson:2.8.5'
implementation 'net.sourceforge.streamsupport:streamsupport:1.7.0'
implementation 'net.sourceforge.streamsupport:android-retrofuture:1.7.0'
implementation 'com.github.cuappdev:futurenovajava:002463b'
```

## Author

Cornell AppDev, cornellappdev@gmail.com

## License

FutureNova is available under the MIT license. See the LICENSE file for more info.
