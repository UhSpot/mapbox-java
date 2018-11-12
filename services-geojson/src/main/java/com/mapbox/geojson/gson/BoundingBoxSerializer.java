package com.mapbox.geojson.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mapbox.geojson.BoundingBox;
import com.mapbox.geojson.Point;

import java.lang.reflect.Type;

/**
 * Serializer used for converting the {@link BoundingBox} object inside a GeoJson object to a JSON
 * element which can be read by GSON and added to the final JSON output.
 *
 * @since 3.0.0
 */
public class BoundingBoxSerializer implements JsonSerializer<BoundingBox> {

  /**
   * Empty constructor to prevent relying on the default one.
   *
   * @since 3.0.0
   */
  public BoundingBoxSerializer() {
    // Empty Constructor
  }

  /**
   * Converts the {@link BoundingBox} object into a JsonArray.
   *
   * @param src       a {@link BoundingBox}
   * @param typeOfSrc common superinterface for all types in the Java
   * @param context   context for deserialization that is passed to a custom deserializer during
   *                  invocation of its {@link com.google.gson.JsonDeserializationContext} method
   * @return a JsonArray containing the raw coordinates
   * @since 3.0.0
   */
  @Override
  public JsonElement serialize(BoundingBox src, Type typeOfSrc, JsonSerializationContext context) {
    JsonArray bbox = new JsonArray();

    // Southwest
    Point point = src.southwest();
    bbox.add(new JsonPrimitive(point.longitude()));
    bbox.add(new JsonPrimitive(point.latitude()));
    if (point.hasAltitude()) {
      bbox.add(new JsonPrimitive(point.altitude()));
    }

    // Northeast
    point = src.northeast();
    bbox.add(new JsonPrimitive(point.longitude()));
    bbox.add(new JsonPrimitive(point.latitude()));
    if (point.hasAltitude()) {
      bbox.add(new JsonPrimitive(point.altitude()));
    }

    return bbox;
  }
}
