/**
 * Created by Zestug on 6/2/16.
 */
package net.adobe.nativeextension.events {
import flash.events.Event;

public class LocationDataEvent extends Event {

    public static const GEOLOCATION_DONE:String = "GEO_DONE";

    public function LocationDataEvent(type:String, lat:String, long:String, bubbles:Boolean = false, cancelable:Boolean = false) {
        super(type, bubbles, cancelable);
        this._lat = lat;
        this._long = long;
    }
    var _lat:String;
    var _long:String;

    public function get latituted():String {
        return this._lat;
    }

    public function get longitude():String {
        return this._long;
    }
}
}
