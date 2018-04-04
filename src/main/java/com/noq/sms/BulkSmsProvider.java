package com.noq.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Service
public class BulkSmsProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(BulkSmsProvider.class);

    private static final String EAPI_URL = "https://bulksms.vsms.net/eapi";

    public void sendSms(String to,String msg) {

        try {
            String data = "";

            data += "username=" + URLEncoder.encode("noQ", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("noQ@1092", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode(msg, "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn="+to;

            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL(EAPI_URL+"/submission/send_sms/2/2.0");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            LOGGER.info("Sms send response:" + response.toString());
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
