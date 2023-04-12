package javaastradropin;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class JavaAstraDropIn {

    final static String ASTRA_TOKEN       = System.getenv("ASTRA_TOKEN");
    final static String ASTRA_DB_ID       = System.getenv("ASTRA_DB_ID");
    final static String ASTRA_DB_REGION   = System.getenv("ASTRA_DB_REGION");
    //final static String ASTRA_DB_KEYSPACE = System.getenv("ASTRA_DB_KEYSPACE");

    public static void main(String[] args) {

        String apiRestEndpoint = new StringBuilder("https://")
                .append(ASTRA_DB_ID).append("-")
                .append(ASTRA_DB_REGION)
                .append(".apps.astra.datastax.com/api/rest")
                .toString();
        System.out.println("Rest Endpoint is " + apiRestEndpoint);

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            // Work with HTTP CLIENT
            listKeyspaces(httpClient, apiRestEndpoint);
        } catch (Exception e) {
			// e.printStackTrace();
        	System.out.println("Unable to reach service endpoint " + apiRestEndpoint);
		}		
	}

	private static void listKeyspaces(CloseableHttpClient httpClient, String apiRestEndpoint) throws Exception {
        // Build Request
        HttpGet listKeyspacesReq = new HttpGet(apiRestEndpoint + "/v2/schemas/keyspaces");
        listKeyspacesReq.addHeader("X-Cassandra-Token", ASTRA_TOKEN);

        // Execute Request
        try(CloseableHttpResponse res = (CloseableHttpResponse) httpClient.execute(listKeyspacesReq)) {
	        if (200 == res.getCode()) {
	        	System.out.println("[OK] Keyspaces list retrieved");
	        	System.out.println("Returned message: " + EntityUtils.toString(res.getEntity()));
	        }
        }
	}
}
