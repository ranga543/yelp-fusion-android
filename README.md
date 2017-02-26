# yelp-fusion-android
An Android library for the [Yelp Fusion API v3](https://www.yelp.com/developers/documentation/v3/get_started). It simplifies the 
process of authentication, request construction, and response parsing for Android developers using the 
[Yelp API v3](https://www.yelp.com/developers/documentation/v3/get_started). This clientlib has been tested with 
applications written in Android API level 15 and 25.
This library inspired by [YelpAndroid](https://github.com/Yelp/yelp-android)

## Installation
[ ![Download](https://api.bintray.com/packages/ranga543/android/yelp-fusion-client/images/download.svg) ](https://bintray.com/ranga543/android/yelp-fusion-client/_latestVersion)

```xml
<dependency>
  <groupId>ranga.android</groupId>
  <artifactId>yelp-fusion-client</artifactId>
  <version>0.1.1</version>
  <type>pom</type>
</dependency>
```

or [Gradle](http://gradle.org/):

```groovy
repositories {
    maven { url 'https://dl.bintray.com/ranga543/android' }
}

dependencies {
    ...
    compile 'ranga.android:yelp-fusion-client:0.1.1'
    ...
}
```
Proguard rules for the library can be found [here](https://github.com/ranga543/yelp-fusion-android/blob/master/yelp-fusion-client/proguard-rules.pro)

## Usage

### Basic usage
This library uses a `YelpFusionApi` object to query against the API. Instantiate a `YelpFusionApi` object by using 
`YelpFusionApiFactory` with your API keys.
```java
YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
YelpFusionApi yelpFusionApi = apiFactory.createAPI(appId, appSecret);
```

### [Search API](https://www.yelp.com/developers/documentation/v3/business_search)
Once you have a `YelpFusionApi` object you can use the `getBusinessSearch` function to generate a `Call` object which makes a request to 
the Search API.

The general params and locale options should be passed to the method as a `Map<String, String>`. The full list of 
parameters can be found in the [Search API Documentation](https://www.yelp.com/developers/documentation/v3/business_search).
```java
Map<String, String> params = new HashMap<>();

// general params
params.put("term", "indian food");
params.put("latitude", "40.581140");
params.put("longitude", "-111.914184");

Call<SearchResponse> call = yelpFusionApi.getBusinessSearch("San Francisco", params);
```

Now you can execute the `Call` object to send the request.
```java
Response<SearchResponse> response = call.execute();
```

You can also pass in a `Callback` object to send the request asynchronously. For more see [Asynchronous Requests](#asynchronous-requests) section.
```java
Callback<SearchResponse> callback = new Callback<SearchResponse>() {
    @Override
    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
        SearchResponse searchResponse = response.body();
        // Update UI text with the searchResponse.
    }
    @Override
    public void onFailure(Call<SearchResponse> call, Throwable t) {
        // HTTP error happened, do something to handle it.
    }
};

call.enqueue(callback);
```

Additionally there are two more search methods for searching by a [Phone](https://www.yelp.com/developers/documentation/v3/business_search_phone) or for [Transaction](https://www.yelp.com/developers/documentation/v3/transactions_search):
```java
// Phone Search
Call<SearchResponse> call = yelpFusionApi.getPhoneSearch("+18014384823");
Response<SearchResponse> response = call.execute();

// Transaction Search
Call<SearchResponse> call = yelpFusionApi.getTransactionSearch("delivery", params);
Response<SearchResponse> response = call.execute();
```

### [Business API](https://www.yelp.com/developers/documentation/v3/business)
To query the Business API, use the `getBusiness` function with a `id`.
```java
Call<Business> call = yelpFusionApi.getBusiness("saffron-valley-south-jordan");
Response<Business> response = call.execute();
```
### Asynchronous Requests
This library uses [Retrofit](http://square.github.io/retrofit/) as the HTTP client. To send a request asynchronously,
use `Call.enqueue()` to set `Callback` function for an asynchronous request.
```java
Callback<Business> callback = new Callback<Business>() {
    @Override
    public void onResponse(Call<Business> call, Response<Business> response) {
        Business business = response.body();
        // Update UI text with the Business object.
    }
    @Override
    public void onFailure(Call<Business> call, Throwable t) {
        // HTTP error happened, do something to handle it.
    }
};

Call<Business> call = yelpFusionApi.getBusiness(id);
call.enqueue(callback);
```

You can cancel asynchronous requests by simply call `cancel()` on `Call` objects. It is important to cancel your calls 
while your `Activity` is being destroyed to avoid memory leaks.
```java
Call<Business> call = yelpFusionApi.getBusiness(businessId);
call.enqueue(callback);

// Activity is being destroyed and the call should be canceled.
call.cancel();
```

For more information about the usage of asynchronous requests in Retrofit, see [Retrofit documentation](http://square.github.io/retrofit/).

## Responses
After `Call` object is executed, a `Response` contains parsed Java objects will be returned, use `Response.body()` to 
get parsed Java objects.

Search and phone search responses are parsed into `SearchResponse` objects.
```java
Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
SearchResponse searchResponse = call.execute().body();

int totalNumberOfResult = searchResponse.getTotal();  // 3

ArrayList<Business> businesses = searchResponse.getBusinesses();
String businessName = businesses.get(0).getName();  // "JapaCurry Truck"
Double rating = businesses.get(0).getRating();  // 4.0
```

Business responses are parsed into `Business` objects directly.
```java
Call<Business> call = yelpFusionApi.getBusiness("japacurry-truck-san-francisco");
Response<Business> response = call.execute();
Business business = response.body();

String businessName = business.getName();  // "JapaCurry Truck"
Double rating = business.getRating();  // 4.0
```

For a full list of available response fields, take a look at the [documentation](https://www.yelp.com/developers/documentation/v3/get_started) 
or the classes defined in [com.yelp.fusion.client.models](../../tree/master/yelp-fusion-client/src/main/java/com/yelp/fusion/client/models).

## Contributing
1. Fork it (https://github.com/ranga543/yelp-fusion-android/fork)
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create new Pull Request

## Testing
Please write tests for any new features. We use JUnit + Gradle so just run `./gradlew test` to run the full test suite.
To know more about running JUnit tests in Gradle, see [Gradle: The Java Plugin - Test](https://docs.gradle
.org/current/userguide/java_plugin.html#sec:java_test).

If you are adding a new integration test, you will need to connect to the Yelp Fusion API. You can set this up by putting 
your API keys into `src/test/resources/credentials.yaml` in the following format:
```
app_id: YOUR_APP_ID
app_secret: YOUR_APP_SECRET
```

To run the integration tests, execute `./gradlew integrationTest`. Integration tests will not be ran in the build
process by executing `./gradlew build`.
