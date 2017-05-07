package com.se491.app.two2er.Utilities;

import com.stormpath.sdk.utils.StringUtils;

import okhttp3.Headers;

/**
 * Created by pazra on 4/15/2017.
 */

public class ServerApiUtilities {


    //API Endpoint Routes:
    public static final String SERVER_API_URL_ROUTE_BOOKING = "booking/";
    public static final String SERVER_API_URL_ROUTE_USERS = "users/";
    public static final String SERVER_API_URL_ROUTE_SUBJECTS = "subjects/";

    private static final String SERVER_API_URL_AUTH = "http://lowcost-env.niuk5squp9.us-east-2.elasticbeanstalk.com/apiauth/";
    private static final String SERVER_API_URL_NOAUTH = "http://lowcost-env.niuk5squp9.us-east-2.elasticbeanstalk.com/api/";
    private static final String SERVER_API_URL_ROUTES_TEST = "http://lowcost-env.niuk5squp9.us-east-2.elasticbeanstalk.com/";

    public static Headers buildStandardHeaders(String accessToken) {
        Headers.Builder builder = new Headers.Builder();
        builder.add("Accept", "application/json");

        if (StringUtils.isNotBlank(accessToken)) {
            builder.add("Authorization", "Bearer " + accessToken);
        }

        return builder.build();
    }

    public static String GetServerApiUrl() { return SERVER_API_URL_AUTH; }
    public static String GetServerApiUrl_NoAuth() {
        return SERVER_API_URL_NOAUTH;
    }
    public static String GetServerApiUrl_RoutesTest() {
        return SERVER_API_URL_ROUTES_TEST;
    }
}
