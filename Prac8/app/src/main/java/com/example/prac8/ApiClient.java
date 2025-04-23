package com.example.prac8;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
	private static final String BASE_URL = "https://random.dog/";
	private static Retrofit retrofit = null;
	public static DogApi getApi() {
		retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		return retrofit.create(DogApi.class);
	}
}
