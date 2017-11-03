package com.google;

import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.LatLng;
import com.strat.springboot.controller.dto.TimezoneDTO;

import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/7/18.
 */
public class googleApiTest {
    
    static Logger log = LoggerFactory.getLogger(googleApiTest.class);
    
    static GeoApiContext geoApiContext = null;
    
    static boolean dayLight = true;
    
    static String key = "AIzaSyDz_APrJAX9pPJTN6javJcgno257ra8odo";
    
    static Double lat = 37.932148;
    // static Double lat = 33.807538;
    
    static Double lng = -122.349151;
    // static Double lng = -84.393438;
    
    static final int MAX_TEST = 1;
    
    public static void main(String[] args) {
        geoApiContext = new GeoApiContext().setApiKey(key);
        for(int i=0; i < MAX_TEST; i++) {
            PendingResult<TimeZone> result = getTimezone(lat, lng);
            try {
                TimeZone timeZone = result.await();
                TimezoneDTO dto = new TimezoneDTO();
                dto.setId(timeZone.getID());
                dto.setDisplayName(timeZone.getDisplayName(dayLight, TimeZone.LONG, Locale.getDefault(Locale.Category.DISPLAY)));
                log.debug("[GeoApiTest - {} ] timezone log :{}", i,dto.toString());
                if (dto != null && dto.getId() != null && dto.getId().length() <= 3) {
                    throw new RuntimeException("[GeoApiTest]Timezone.id is 'PST'. " + dto.toString());
                }
            } catch (Exception e) {
                log.debug("[GeoApiTest-{}] error.", i);
            }
        }
    }
    
    private static PendingResult<TimeZone> getTimezone(Double lat, Double lng) {
        long start = System.currentTimeMillis();
        LatLng location = new LatLng(lat, lng);
        PendingResult<TimeZone> result = TimeZoneApi.getTimeZone(geoApiContext, location);
        long end = System.currentTimeMillis();
        //log.debug("[GeoApiTest] google timezone : {}", (end - start) + " ms");
        return result;
    }
}
