package com.sccommute.commute;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by simba on 1/11/15.
 */
public class RouteInit {
    public static HashMap<String, Route> initRoutes() {
        HashMap<String, Route> routes = new HashMap<String, Route>();
        ArrayList<LatLng> stops16 = new ArrayList<LatLng>();
        stops16.add(new LatLng(36.970891,-122.024552));
        stops16.add(new LatLng(36.9689774,-122.0276118));
        stops16.add(new LatLng(36.968689,-122.0290745));
        stops16.add(new LatLng(36.9685142,-122.0311432));
        stops16.add(new LatLng(36.9689968,-122.0377961));
        stops16.add(new LatLng(36.9674437,-122.0391532));
        stops16.add(new LatLng(36.9666736,-122.040920));
        stops16.add(new LatLng(36.9674537,-122.0424921));
        stops16.add(new LatLng(36.9692587,-122.0456441));
        stops16.add(new LatLng(36.9741959,-122.0510622));
        stops16.add(new LatLng(36.9762787,-122.0526608));
        stops16.add(new LatLng(36.9784627,-122.0577697));
        stops16.add(new LatLng(36.9801354,-122.060144));
        stops16.add(new LatLng(36.9824746,-122.0629004));
        stops16.add(new LatLng(36.9839873,-122.0659206));
        stops16.add(new LatLng(36.9933836,-122.0652837));
        stops16.add(new LatLng(36.9967415,-122.0633505));
        stops16.add(new LatLng(36.9998623,-122.0635032));
        stops16.add(new LatLng(36.9998966,-122.0609981));
        stops16.add(new LatLng(36.9995196,-122.0566261));
        stops16.add(new LatLng(36.9972734,-122.0548944));
        stops16.add(new LatLng(36.9972734,-122.0548944));
        stops16.add(new LatLng(36.9939296,-122.0553392));
        stops16.add(new LatLng(36.9903686,-122.0548805));
        stops16.add(new LatLng(36.9856218,-122.0541882));
        stops16.add(new LatLng(36.9806614,-122.0536837));
        routes.put("16", new Route("soy`Ffo~gVu@lMRvEjAvF`@jHpDHhC{CtIGnCcArHr@fEvBRvJ`CxBzBYnCuAfC`@pA~ElBrAlEdA|@dB~V_RzEkOvJkOjP}S|CgF|@}L`IyClKqRdEoFdKkDlEcH`CkD~DkOrGkOmEkHmKiG`CaOlByFwCuu@eHz@rHk@tBhs@yApGaCfQlKrGlExGsGdLiDvMyGjLcD|AgHfBoFjHwDxJcDzG_B~BuNhCqD`@uC{@iCcDoFkLm]lTuCXoCeAcV|DkGN_ElA_EQuFi@}CQoCH}Ck@o@tA}CvBo@pCv@zG",
                stops16));
        return routes;
    }
}
