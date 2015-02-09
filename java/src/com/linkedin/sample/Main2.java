package com.linkedin.sample;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;

import java.util.Scanner;

/**
 * Created by ywang on 09.02.15.
 */
public class Main2 {


    private static Scanner s;

    public static void main(String[] args) {
    String linkedinKey = "77mahxt83mbma8";    //add your LinkedIn key
    String linkedinSecret = "vpSWVa01dWq4dFfO"; //add your LinkedIn Secret

    LinkedInOAuthService oauthService;
    LinkedInRequestToken requestToken;

    System.out.println("Fetching request token from LinkedIn...");
    String authUrl = null;
    String authToken,authTokenSecret;

    oauthService= LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(linkedinKey,linkedinSecret);
    requestToken= oauthService.getOAuthRequestToken();
    authToken= requestToken.getToken();
    authTokenSecret = requestToken.getTokenSecret();

    System.out.println("Request token " +requestToken);
    System.out.println("Auth token" +authToken);
    System.out.println("Auth token secret" +authTokenSecret);

    authUrl = requestToken.getAuthorizationUrl();

    System.out.println("Copy below link in web browser to authorize. Copy the PIN obtained\n" + authUrl);
    System.out.println("Enter the PIN code:");

    String pin;

    try
    {

        s = new Scanner(System.in);
        pin = s.next();
        System.out.println("Fetching access token from LinkedIn...");

        LinkedInAccessToken accessToken =  oauthService.getOAuthAccessToken(requestToken, pin);
        System.out.println("Access token : " +  accessToken.getToken());
        System.out.println("Token secret : " +  accessToken.getTokenSecret());
        final LinkedInApiClientFactory factory =  LinkedInApiClientFactory.newInstance(linkedinKey,linkedinSecret);
        final LinkedInApiClient client =  factory.createLinkedInApiClient(accessToken);

        //posting status to profile
        client.updateCurrentStatus("LinkedIN API is cool!");

    }

    finally
    {
        System.out.println("Updated status!");
    }

    }

}
