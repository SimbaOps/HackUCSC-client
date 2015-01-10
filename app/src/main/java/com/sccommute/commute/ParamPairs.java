package com.sccommute.commute;

import java.util.ArrayList;

/**
 * Created by simba on 1/10/15.
 */
public class ParamPairs {
    ArrayList<String> keys = new ArrayList<String>();
    ArrayList<String> values = new ArrayList<String>();
    public void addPair(String param, String value) {
        keys.add(param);
        values.add(value);
    }

    public String getPairsEncoded() {
        StringBuilder encodedBuilder = new StringBuilder();
        if(keys.size() == 0) {
            return encodedBuilder.toString();
        }
        encodedBuilder.append(keys.get(0)).append("=").append(values.get(0));
        for(int i = 1; i < keys.size(); i++) {
            encodedBuilder.append("&").append(keys.get(i)).append("=").append(values.get(i));
        }
        return encodedBuilder.toString();
    }
}
