package android.support.media;

import android.content.res.AssetManager.AssetInputStream;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.InputDeviceCompat;
import android.util.Log;
import android.util.Pair;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExifInterface {
    public static final short ALTITUDE_ABOVE_SEA_LEVEL = (short) 0;
    public static final short ALTITUDE_BELOW_SEA_LEVEL = (short) 1;
    private static final Charset ASCII = Charset.forName("US-ASCII");
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_1 = new int[]{4};
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_2 = new int[]{8};
    public static final int[] BITS_PER_SAMPLE_RGB = new int[]{8, 8, 8};
    static final short BYTE_ALIGN_II = (short) 18761;
    static final short BYTE_ALIGN_MM = (short) 19789;
    public static final int COLOR_SPACE_S_RGB = 1;
    public static final int COLOR_SPACE_UNCALIBRATED = 65535;
    public static final short CONTRAST_HARD = (short) 2;
    public static final short CONTRAST_NORMAL = (short) 0;
    public static final short CONTRAST_SOFT = (short) 1;
    public static final int DATA_DEFLATE_ZIP = 8;
    public static final int DATA_HUFFMAN_COMPRESSED = 2;
    public static final int DATA_JPEG = 6;
    public static final int DATA_JPEG_COMPRESSED = 7;
    public static final int DATA_LOSSY_JPEG = 34892;
    public static final int DATA_PACK_BITS_COMPRESSED = 32773;
    public static final int DATA_UNCOMPRESSED = 1;
    private static final boolean DEBUG = false;
    private static final byte[] EXIF_ASCII_PREFIX = new byte[]{(byte) 65, (byte) 83, (byte) 67, (byte) 73, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
    private static final ExifTag[] EXIF_POINTER_TAGS = new ExifTag[]{new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, 4), new ExifTag(TAG_ORF_CAMERA_SETTINGS_IFD_POINTER, 8224, 1), new ExifTag(TAG_ORF_IMAGE_PROCESSING_IFD_POINTER, 8256, 1)};
    static final ExifTag[][] EXIF_TAGS = new ExifTag[][]{IFD_TIFF_TAGS, IFD_EXIF_TAGS, IFD_GPS_TAGS, IFD_INTEROPERABILITY_TAGS, IFD_THUMBNAIL_TAGS, IFD_TIFF_TAGS, ORF_MAKER_NOTE_TAGS, ORF_CAMERA_SETTINGS_TAGS, ORF_IMAGE_PROCESSING_TAGS, PEF_TAGS};
    public static final short EXPOSURE_MODE_AUTO = (short) 0;
    public static final short EXPOSURE_MODE_AUTO_BRACKET = (short) 2;
    public static final short EXPOSURE_MODE_MANUAL = (short) 1;
    public static final short EXPOSURE_PROGRAM_ACTION = (short) 6;
    public static final short EXPOSURE_PROGRAM_APERTURE_PRIORITY = (short) 3;
    public static final short EXPOSURE_PROGRAM_CREATIVE = (short) 5;
    public static final short EXPOSURE_PROGRAM_LANDSCAPE_MODE = (short) 8;
    public static final short EXPOSURE_PROGRAM_MANUAL = (short) 1;
    public static final short EXPOSURE_PROGRAM_NORMAL = (short) 2;
    public static final short EXPOSURE_PROGRAM_NOT_DEFINED = (short) 0;
    public static final short EXPOSURE_PROGRAM_PORTRAIT_MODE = (short) 7;
    public static final short EXPOSURE_PROGRAM_SHUTTER_PRIORITY = (short) 4;
    public static final short FILE_SOURCE_DSC = (short) 3;
    public static final short FILE_SOURCE_OTHER = (short) 0;
    public static final short FILE_SOURCE_REFLEX_SCANNER = (short) 2;
    public static final short FILE_SOURCE_TRANSPARENT_SCANNER = (short) 1;
    public static final short FLAG_FLASH_FIRED = (short) 1;
    public static final short FLAG_FLASH_MODE_AUTO = (short) 24;
    public static final short FLAG_FLASH_MODE_COMPULSORY_FIRING = (short) 8;
    public static final short FLAG_FLASH_MODE_COMPULSORY_SUPPRESSION = (short) 16;
    public static final short FLAG_FLASH_NO_FLASH_FUNCTION = (short) 32;
    public static final short FLAG_FLASH_RED_EYE_SUPPORTED = (short) 64;
    public static final short FLAG_FLASH_RETURN_LIGHT_DETECTED = (short) 6;
    public static final short FLAG_FLASH_RETURN_LIGHT_NOT_DETECTED = (short) 4;
    private static final List<Integer> FLIPPED_ROTATION_ORDER = Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(7), Integer.valueOf(4), Integer.valueOf(5)});
    public static final short FORMAT_CHUNKY = (short) 1;
    public static final short FORMAT_PLANAR = (short) 2;
    public static final short GAIN_CONTROL_HIGH_GAIN_DOWN = (short) 4;
    public static final short GAIN_CONTROL_HIGH_GAIN_UP = (short) 2;
    public static final short GAIN_CONTROL_LOW_GAIN_DOWN = (short) 3;
    public static final short GAIN_CONTROL_LOW_GAIN_UP = (short) 1;
    public static final short GAIN_CONTROL_NONE = (short) 0;
    public static final String GPS_DIRECTION_MAGNETIC = "M";
    public static final String GPS_DIRECTION_TRUE = "T";
    public static final String GPS_DISTANCE_KILOMETERS = "K";
    public static final String GPS_DISTANCE_MILES = "M";
    public static final String GPS_DISTANCE_NAUTICAL_MILES = "N";
    public static final String GPS_MEASUREMENT_2D = "2";
    public static final String GPS_MEASUREMENT_3D = "3";
    public static final short GPS_MEASUREMENT_DIFFERENTIAL_CORRECTED = (short) 1;
    public static final String GPS_MEASUREMENT_INTERRUPTED = "V";
    public static final String GPS_MEASUREMENT_IN_PROGRESS = "A";
    public static final short GPS_MEASUREMENT_NO_DIFFERENTIAL = (short) 0;
    public static final String GPS_SPEED_KILOMETERS_PER_HOUR = "K";
    public static final String GPS_SPEED_KNOTS = "N";
    public static final String GPS_SPEED_MILES_PER_HOUR = "M";
    static final byte[] IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(ASCII);
    private static final ExifTag[] IFD_EXIF_TAGS = new ExifTag[]{new ExifTag(TAG_EXPOSURE_TIME, 33434, 5), new ExifTag(TAG_F_NUMBER, 33437, 5), new ExifTag(TAG_EXPOSURE_PROGRAM, 34850, 3), new ExifTag(TAG_SPECTRAL_SENSITIVITY, 34852, 2), new ExifTag(TAG_PHOTOGRAPHIC_SENSITIVITY, 34855, 3), new ExifTag(TAG_OECF, 34856, 7), new ExifTag(TAG_EXIF_VERSION, 36864, 2), new ExifTag(TAG_DATETIME_ORIGINAL, 36867, 2), new ExifTag(TAG_DATETIME_DIGITIZED, 36868, 2), new ExifTag(TAG_COMPONENTS_CONFIGURATION, 37121, 7), new ExifTag(TAG_COMPRESSED_BITS_PER_PIXEL, 37122, 5), new ExifTag(TAG_SHUTTER_SPEED_VALUE, 37377, 10), new ExifTag(TAG_APERTURE_VALUE, 37378, 5), new ExifTag(TAG_BRIGHTNESS_VALUE, 37379, 10), new ExifTag(TAG_EXPOSURE_BIAS_VALUE, 37380, 10), new ExifTag(TAG_MAX_APERTURE_VALUE, 37381, 5), new ExifTag(TAG_SUBJECT_DISTANCE, 37382, 5), new ExifTag(TAG_METERING_MODE, 37383, 3), new ExifTag(TAG_LIGHT_SOURCE, 37384, 3), new ExifTag(TAG_FLASH, 37385, 3), new ExifTag(TAG_FOCAL_LENGTH, 37386, 5), new ExifTag(TAG_SUBJECT_AREA, 37396, 3), new ExifTag(TAG_MAKER_NOTE, 37500, 7), new ExifTag(TAG_USER_COMMENT, 37510, 7), new ExifTag(TAG_SUBSEC_TIME, 37520, 2), new ExifTag(TAG_SUBSEC_TIME_ORIGINAL, 37521, 2), new ExifTag(TAG_SUBSEC_TIME_DIGITIZED, 37522, 2), new ExifTag(TAG_FLASHPIX_VERSION, 40960, 7), new ExifTag(TAG_COLOR_SPACE, 40961, 3), new ExifTag(TAG_PIXEL_X_DIMENSION, 40962, 3, 4), new ExifTag(TAG_PIXEL_Y_DIMENSION, 40963, 3, 4), new ExifTag(TAG_RELATED_SOUND_FILE, 40964, 2), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, 4), new ExifTag(TAG_FLASH_ENERGY, 41483, 5), new ExifTag(TAG_SPATIAL_FREQUENCY_RESPONSE, 41484, 7), new ExifTag(TAG_FOCAL_PLANE_X_RESOLUTION, 41486, 5), new ExifTag(TAG_FOCAL_PLANE_Y_RESOLUTION, 41487, 5), new ExifTag(TAG_FOCAL_PLANE_RESOLUTION_UNIT, 41488, 3), new ExifTag(TAG_SUBJECT_LOCATION, 41492, 3), new ExifTag(TAG_EXPOSURE_INDEX, 41493, 5), new ExifTag(TAG_SENSING_METHOD, 41495, 3), new ExifTag(TAG_FILE_SOURCE, 41728, 7), new ExifTag(TAG_SCENE_TYPE, 41729, 7), new ExifTag(TAG_CFA_PATTERN, 41730, 7), new ExifTag(TAG_CUSTOM_RENDERED, 41985, 3), new ExifTag(TAG_EXPOSURE_MODE, 41986, 3), new ExifTag(TAG_WHITE_BALANCE, 41987, 3), new ExifTag(TAG_DIGITAL_ZOOM_RATIO, 41988, 5), new ExifTag(TAG_FOCAL_LENGTH_IN_35MM_FILM, 41989, 3), new ExifTag(TAG_SCENE_CAPTURE_TYPE, 41990, 3), new ExifTag(TAG_GAIN_CONTROL, 41991, 3), new ExifTag(TAG_CONTRAST, 41992, 3), new ExifTag(TAG_SATURATION, 41993, 3), new ExifTag(TAG_SHARPNESS, 41994, 3), new ExifTag(TAG_DEVICE_SETTING_DESCRIPTION, 41995, 7), new ExifTag(TAG_SUBJECT_DISTANCE_RANGE, 41996, 3), new ExifTag(TAG_IMAGE_UNIQUE_ID, 42016, 2), new ExifTag(TAG_DNG_VERSION, 50706, 1), new ExifTag(TAG_DEFAULT_CROP_SIZE, 50720, 3, 4)};
    private static final int IFD_FORMAT_BYTE = 1;
    static final int[] IFD_FORMAT_BYTES_PER_FORMAT = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    private static final int IFD_FORMAT_DOUBLE = 12;
    private static final int IFD_FORMAT_IFD = 13;
    static final String[] IFD_FORMAT_NAMES = new String[]{"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE"};
    private static final int IFD_FORMAT_SBYTE = 6;
    private static final int IFD_FORMAT_SINGLE = 11;
    private static final int IFD_FORMAT_SLONG = 9;
    private static final int IFD_FORMAT_SRATIONAL = 10;
    private static final int IFD_FORMAT_SSHORT = 8;
    private static final int IFD_FORMAT_STRING = 2;
    private static final int IFD_FORMAT_ULONG = 4;
    private static final int IFD_FORMAT_UNDEFINED = 7;
    private static final int IFD_FORMAT_URATIONAL = 5;
    private static final int IFD_FORMAT_USHORT = 3;
    private static final ExifTag[] IFD_GPS_TAGS = new ExifTag[]{new ExifTag(TAG_GPS_VERSION_ID, 0, 1), new ExifTag(TAG_GPS_LATITUDE_REF, 1, 2), new ExifTag(TAG_GPS_LATITUDE, 2, 5), new ExifTag(TAG_GPS_LONGITUDE_REF, 3, 2), new ExifTag(TAG_GPS_LONGITUDE, 4, 5), new ExifTag(TAG_GPS_ALTITUDE_REF, 5, 1), new ExifTag(TAG_GPS_ALTITUDE, 6, 5), new ExifTag(TAG_GPS_TIMESTAMP, 7, 5), new ExifTag(TAG_GPS_SATELLITES, 8, 2), new ExifTag(TAG_GPS_STATUS, 9, 2), new ExifTag(TAG_GPS_MEASURE_MODE, 10, 2), new ExifTag(TAG_GPS_DOP, 11, 5), new ExifTag(TAG_GPS_SPEED_REF, 12, 2), new ExifTag(TAG_GPS_SPEED, 13, 5), new ExifTag(TAG_GPS_TRACK_REF, 14, 2), new ExifTag(TAG_GPS_TRACK, 15, 5), new ExifTag(TAG_GPS_IMG_DIRECTION_REF, 16, 2), new ExifTag(TAG_GPS_IMG_DIRECTION, 17, 5), new ExifTag(TAG_GPS_MAP_DATUM, 18, 2), new ExifTag(TAG_GPS_DEST_LATITUDE_REF, 19, 2), new ExifTag(TAG_GPS_DEST_LATITUDE, 20, 5), new ExifTag(TAG_GPS_DEST_LONGITUDE_REF, 21, 2), new ExifTag(TAG_GPS_DEST_LONGITUDE, 22, 5), new ExifTag(TAG_GPS_DEST_BEARING_REF, 23, 2), new ExifTag(TAG_GPS_DEST_BEARING, 24, 5), new ExifTag(TAG_GPS_DEST_DISTANCE_REF, 25, 2), new ExifTag(TAG_GPS_DEST_DISTANCE, 26, 5), new ExifTag(TAG_GPS_PROCESSING_METHOD, 27, 7), new ExifTag(TAG_GPS_AREA_INFORMATION, 28, 7), new ExifTag(TAG_GPS_DATESTAMP, 29, 2), new ExifTag(TAG_GPS_DIFFERENTIAL, 30, 3)};
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS = new ExifTag[]{new ExifTag(TAG_INTEROPERABILITY_INDEX, 1, 2)};
    private static final int IFD_OFFSET = 8;
    private static final ExifTag[] IFD_THUMBNAIL_TAGS = new ExifTag[]{new ExifTag(TAG_NEW_SUBFILE_TYPE, 254, 4), new ExifTag(TAG_SUBFILE_TYPE, 255, 4), new ExifTag(TAG_THUMBNAIL_IMAGE_WIDTH, 256, 3, 4), new ExifTag(TAG_THUMBNAIL_IMAGE_LENGTH, 257, 3, 4), new ExifTag(TAG_BITS_PER_SAMPLE, (int) VoiceWakeuperAidl.RES_SPECIFIED, 3), new ExifTag(TAG_COMPRESSION, (int) VoiceWakeuperAidl.RES_FROM_CLIENT, 3), new ExifTag(TAG_PHOTOMETRIC_INTERPRETATION, 262, 3), new ExifTag(TAG_IMAGE_DESCRIPTION, 270, 2), new ExifTag(TAG_MAKE, 271, 2), new ExifTag(TAG_MODEL, 272, 2), new ExifTag(TAG_STRIP_OFFSETS, 273, 3, 4), new ExifTag(TAG_ORIENTATION, 274, 3), new ExifTag(TAG_SAMPLES_PER_PIXEL, 277, 3), new ExifTag(TAG_ROWS_PER_STRIP, 278, 3, 4), new ExifTag(TAG_STRIP_BYTE_COUNTS, 279, 3, 4), new ExifTag(TAG_X_RESOLUTION, 282, 5), new ExifTag(TAG_Y_RESOLUTION, 283, 5), new ExifTag(TAG_PLANAR_CONFIGURATION, 284, 3), new ExifTag(TAG_RESOLUTION_UNIT, 296, 3), new ExifTag(TAG_TRANSFER_FUNCTION, 301, 3), new ExifTag(TAG_SOFTWARE, (int) TinkerReport.KEY_LOADED_MISSING_PATCH_FILE, 2), new ExifTag(TAG_DATETIME, (int) TinkerReport.KEY_LOADED_MISSING_PATCH_INFO, 2), new ExifTag(TAG_ARTIST, 315, 2), new ExifTag(TAG_WHITE_POINT, 318, 5), new ExifTag(TAG_PRIMARY_CHROMATICITIES, 319, 5), new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT, (int) InputDeviceCompat.SOURCE_DPAD, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, 4), new ExifTag(TAG_Y_CB_CR_COEFFICIENTS, 529, 5), new ExifTag(TAG_Y_CB_CR_SUB_SAMPLING, 530, 3), new ExifTag(TAG_Y_CB_CR_POSITIONING, 531, 3), new ExifTag(TAG_REFERENCE_BLACK_WHITE, 532, 5), new ExifTag(TAG_COPYRIGHT, 33432, 2), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag(TAG_DNG_VERSION, 50706, 1), new ExifTag(TAG_DEFAULT_CROP_SIZE, 50720, 3, 4)};
    private static final ExifTag[] IFD_TIFF_TAGS = new ExifTag[]{new ExifTag(TAG_NEW_SUBFILE_TYPE, 254, 4), new ExifTag(TAG_SUBFILE_TYPE, 255, 4), new ExifTag(TAG_IMAGE_WIDTH, 256, 3, 4), new ExifTag(TAG_IMAGE_LENGTH, 257, 3, 4), new ExifTag(TAG_BITS_PER_SAMPLE, (int) VoiceWakeuperAidl.RES_SPECIFIED, 3), new ExifTag(TAG_COMPRESSION, (int) VoiceWakeuperAidl.RES_FROM_CLIENT, 3), new ExifTag(TAG_PHOTOMETRIC_INTERPRETATION, 262, 3), new ExifTag(TAG_IMAGE_DESCRIPTION, 270, 2), new ExifTag(TAG_MAKE, 271, 2), new ExifTag(TAG_MODEL, 272, 2), new ExifTag(TAG_STRIP_OFFSETS, 273, 3, 4), new ExifTag(TAG_ORIENTATION, 274, 3), new ExifTag(TAG_SAMPLES_PER_PIXEL, 277, 3), new ExifTag(TAG_ROWS_PER_STRIP, 278, 3, 4), new ExifTag(TAG_STRIP_BYTE_COUNTS, 279, 3, 4), new ExifTag(TAG_X_RESOLUTION, 282, 5), new ExifTag(TAG_Y_RESOLUTION, 283, 5), new ExifTag(TAG_PLANAR_CONFIGURATION, 284, 3), new ExifTag(TAG_RESOLUTION_UNIT, 296, 3), new ExifTag(TAG_TRANSFER_FUNCTION, 301, 3), new ExifTag(TAG_SOFTWARE, (int) TinkerReport.KEY_LOADED_MISSING_PATCH_FILE, 2), new ExifTag(TAG_DATETIME, (int) TinkerReport.KEY_LOADED_MISSING_PATCH_INFO, 2), new ExifTag(TAG_ARTIST, 315, 2), new ExifTag(TAG_WHITE_POINT, 318, 5), new ExifTag(TAG_PRIMARY_CHROMATICITIES, 319, 5), new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT, (int) InputDeviceCompat.SOURCE_DPAD, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, 4), new ExifTag(TAG_Y_CB_CR_COEFFICIENTS, 529, 5), new ExifTag(TAG_Y_CB_CR_SUB_SAMPLING, 530, 3), new ExifTag(TAG_Y_CB_CR_POSITIONING, 531, 3), new ExifTag(TAG_REFERENCE_BLACK_WHITE, 532, 5), new ExifTag(TAG_COPYRIGHT, 33432, 2), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag(TAG_RW2_SENSOR_TOP_BORDER, 4, 4), new ExifTag(TAG_RW2_SENSOR_LEFT_BORDER, 5, 4), new ExifTag(TAG_RW2_SENSOR_BOTTOM_BORDER, 6, 4), new ExifTag(TAG_RW2_SENSOR_RIGHT_BORDER, 7, 4), new ExifTag(TAG_RW2_ISO, 23, 3), new ExifTag(TAG_RW2_JPG_FROM_RAW, 46, 7)};
    private static final int IFD_TYPE_EXIF = 1;
    private static final int IFD_TYPE_GPS = 2;
    private static final int IFD_TYPE_INTEROPERABILITY = 3;
    private static final int IFD_TYPE_ORF_CAMERA_SETTINGS = 7;
    private static final int IFD_TYPE_ORF_IMAGE_PROCESSING = 8;
    private static final int IFD_TYPE_ORF_MAKER_NOTE = 6;
    private static final int IFD_TYPE_PEF = 9;
    static final int IFD_TYPE_PREVIEW = 5;
    static final int IFD_TYPE_PRIMARY = 0;
    static final int IFD_TYPE_THUMBNAIL = 4;
    private static final int IMAGE_TYPE_ARW = 1;
    private static final int IMAGE_TYPE_CR2 = 2;
    private static final int IMAGE_TYPE_DNG = 3;
    private static final int IMAGE_TYPE_JPEG = 4;
    private static final int IMAGE_TYPE_NEF = 5;
    private static final int IMAGE_TYPE_NRW = 6;
    private static final int IMAGE_TYPE_ORF = 7;
    private static final int IMAGE_TYPE_PEF = 8;
    private static final int IMAGE_TYPE_RAF = 9;
    private static final int IMAGE_TYPE_RW2 = 10;
    private static final int IMAGE_TYPE_SRW = 11;
    private static final int IMAGE_TYPE_UNKNOWN = 0;
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_LENGTH_TAG = new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, 4);
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_TAG = new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT, (int) InputDeviceCompat.SOURCE_DPAD, 4);
    static final byte[] JPEG_SIGNATURE = new byte[]{MARKER, MARKER_SOI, MARKER};
    public static final String LATITUDE_NORTH = "N";
    public static final String LATITUDE_SOUTH = "S";
    public static final short LIGHT_SOURCE_CLOUDY_WEATHER = (short) 10;
    public static final short LIGHT_SOURCE_COOL_WHITE_FLUORESCENT = (short) 14;
    public static final short LIGHT_SOURCE_D50 = (short) 23;
    public static final short LIGHT_SOURCE_D55 = (short) 20;
    public static final short LIGHT_SOURCE_D65 = (short) 21;
    public static final short LIGHT_SOURCE_D75 = (short) 22;
    public static final short LIGHT_SOURCE_DAYLIGHT = (short) 1;
    public static final short LIGHT_SOURCE_DAYLIGHT_FLUORESCENT = (short) 12;
    public static final short LIGHT_SOURCE_DAY_WHITE_FLUORESCENT = (short) 13;
    public static final short LIGHT_SOURCE_FINE_WEATHER = (short) 9;
    public static final short LIGHT_SOURCE_FLASH = (short) 4;
    public static final short LIGHT_SOURCE_FLUORESCENT = (short) 2;
    public static final short LIGHT_SOURCE_ISO_STUDIO_TUNGSTEN = (short) 24;
    public static final short LIGHT_SOURCE_OTHER = (short) 255;
    public static final short LIGHT_SOURCE_SHADE = (short) 11;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_A = (short) 17;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_B = (short) 18;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_C = (short) 19;
    public static final short LIGHT_SOURCE_TUNGSTEN = (short) 3;
    public static final short LIGHT_SOURCE_UNKNOWN = (short) 0;
    public static final short LIGHT_SOURCE_WARM_WHITE_FLUORESCENT = (short) 16;
    public static final short LIGHT_SOURCE_WHITE_FLUORESCENT = (short) 15;
    public static final String LONGITUDE_EAST = "E";
    public static final String LONGITUDE_WEST = "W";
    static final byte MARKER = (byte) -1;
    static final byte MARKER_APP1 = (byte) -31;
    private static final byte MARKER_COM = (byte) -2;
    static final byte MARKER_EOI = (byte) -39;
    private static final byte MARKER_SOF0 = (byte) -64;
    private static final byte MARKER_SOF1 = (byte) -63;
    private static final byte MARKER_SOF10 = (byte) -54;
    private static final byte MARKER_SOF11 = (byte) -53;
    private static final byte MARKER_SOF13 = (byte) -51;
    private static final byte MARKER_SOF14 = (byte) -50;
    private static final byte MARKER_SOF15 = (byte) -49;
    private static final byte MARKER_SOF2 = (byte) -62;
    private static final byte MARKER_SOF3 = (byte) -61;
    private static final byte MARKER_SOF5 = (byte) -59;
    private static final byte MARKER_SOF6 = (byte) -58;
    private static final byte MARKER_SOF7 = (byte) -57;
    private static final byte MARKER_SOF9 = (byte) -55;
    private static final byte MARKER_SOI = (byte) -40;
    private static final byte MARKER_SOS = (byte) -38;
    private static final int MAX_THUMBNAIL_SIZE = 512;
    public static final short METERING_MODE_AVERAGE = (short) 1;
    public static final short METERING_MODE_CENTER_WEIGHT_AVERAGE = (short) 2;
    public static final short METERING_MODE_MULTI_SPOT = (short) 4;
    public static final short METERING_MODE_OTHER = (short) 255;
    public static final short METERING_MODE_PARTIAL = (short) 6;
    public static final short METERING_MODE_PATTERN = (short) 5;
    public static final short METERING_MODE_SPOT = (short) 3;
    public static final short METERING_MODE_UNKNOWN = (short) 0;
    private static final ExifTag[] ORF_CAMERA_SETTINGS_TAGS = new ExifTag[]{new ExifTag(TAG_ORF_PREVIEW_IMAGE_START, 257, 4), new ExifTag(TAG_ORF_PREVIEW_IMAGE_LENGTH, (int) VoiceWakeuperAidl.RES_SPECIFIED, 4)};
    private static final ExifTag[] ORF_IMAGE_PROCESSING_TAGS = new ExifTag[]{new ExifTag(TAG_ORF_ASPECT_FRAME, 4371, 3)};
    private static final byte[] ORF_MAKER_NOTE_HEADER_1 = new byte[]{(byte) 79, (byte) 76, (byte) 89, (byte) 77, (byte) 80, (byte) 0};
    private static final int ORF_MAKER_NOTE_HEADER_1_SIZE = 8;
    private static final byte[] ORF_MAKER_NOTE_HEADER_2 = new byte[]{(byte) 79, (byte) 76, (byte) 89, (byte) 77, (byte) 80, (byte) 85, (byte) 83, (byte) 0, (byte) 73, (byte) 73};
    private static final int ORF_MAKER_NOTE_HEADER_2_SIZE = 12;
    private static final ExifTag[] ORF_MAKER_NOTE_TAGS = new ExifTag[]{new ExifTag(TAG_ORF_THUMBNAIL_IMAGE, 256, 7), new ExifTag(TAG_ORF_CAMERA_SETTINGS_IFD_POINTER, 8224, 4), new ExifTag(TAG_ORF_IMAGE_PROCESSING_IFD_POINTER, 8256, 4)};
    private static final short ORF_SIGNATURE_1 = (short) 20306;
    private static final short ORF_SIGNATURE_2 = (short) 21330;
    public static final int ORIENTATION_FLIP_HORIZONTAL = 2;
    public static final int ORIENTATION_FLIP_VERTICAL = 4;
    public static final int ORIENTATION_NORMAL = 1;
    public static final int ORIENTATION_ROTATE_180 = 3;
    public static final int ORIENTATION_ROTATE_270 = 8;
    public static final int ORIENTATION_ROTATE_90 = 6;
    public static final int ORIENTATION_TRANSPOSE = 5;
    public static final int ORIENTATION_TRANSVERSE = 7;
    public static final int ORIENTATION_UNDEFINED = 0;
    public static final int ORIGINAL_RESOLUTION_IMAGE = 0;
    private static final int PEF_MAKER_NOTE_SKIP_SIZE = 6;
    private static final String PEF_SIGNATURE = "PENTAX";
    private static final ExifTag[] PEF_TAGS = new ExifTag[]{new ExifTag(TAG_COLOR_SPACE, 55, 3)};
    public static final int PHOTOMETRIC_INTERPRETATION_BLACK_IS_ZERO = 1;
    public static final int PHOTOMETRIC_INTERPRETATION_RGB = 2;
    public static final int PHOTOMETRIC_INTERPRETATION_WHITE_IS_ZERO = 0;
    public static final int PHOTOMETRIC_INTERPRETATION_YCBCR = 6;
    private static final int RAF_INFO_SIZE = 160;
    private static final int RAF_JPEG_LENGTH_VALUE_SIZE = 4;
    private static final int RAF_OFFSET_TO_JPEG_IMAGE_OFFSET = 84;
    private static final String RAF_SIGNATURE = "FUJIFILMCCD-RAW";
    public static final int REDUCED_RESOLUTION_IMAGE = 1;
    public static final short RENDERED_PROCESS_CUSTOM = (short) 1;
    public static final short RENDERED_PROCESS_NORMAL = (short) 0;
    public static final short RESOLUTION_UNIT_CENTIMETERS = (short) 3;
    public static final short RESOLUTION_UNIT_INCHES = (short) 2;
    private static final List<Integer> ROTATION_ORDER = Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(6), Integer.valueOf(3), Integer.valueOf(8)});
    private static final short RW2_SIGNATURE = (short) 85;
    public static final short SATURATION_HIGH = (short) 0;
    public static final short SATURATION_LOW = (short) 0;
    public static final short SATURATION_NORMAL = (short) 0;
    public static final short SCENE_CAPTURE_TYPE_LANDSCAPE = (short) 1;
    public static final short SCENE_CAPTURE_TYPE_NIGHT = (short) 3;
    public static final short SCENE_CAPTURE_TYPE_PORTRAIT = (short) 2;
    public static final short SCENE_CAPTURE_TYPE_STANDARD = (short) 0;
    public static final short SCENE_TYPE_DIRECTLY_PHOTOGRAPHED = (short) 1;
    public static final short SENSITIVITY_TYPE_ISO_SPEED = (short) 3;
    public static final short SENSITIVITY_TYPE_REI = (short) 2;
    public static final short SENSITIVITY_TYPE_REI_AND_ISO = (short) 6;
    public static final short SENSITIVITY_TYPE_SOS = (short) 1;
    public static final short SENSITIVITY_TYPE_SOS_AND_ISO = (short) 5;
    public static final short SENSITIVITY_TYPE_SOS_AND_REI = (short) 4;
    public static final short SENSITIVITY_TYPE_SOS_AND_REI_AND_ISO = (short) 7;
    public static final short SENSITIVITY_TYPE_UNKNOWN = (short) 0;
    public static final short SENSOR_TYPE_COLOR_SEQUENTIAL = (short) 5;
    public static final short SENSOR_TYPE_COLOR_SEQUENTIAL_LINEAR = (short) 8;
    public static final short SENSOR_TYPE_NOT_DEFINED = (short) 1;
    public static final short SENSOR_TYPE_ONE_CHIP = (short) 2;
    public static final short SENSOR_TYPE_THREE_CHIP = (short) 4;
    public static final short SENSOR_TYPE_TRILINEAR = (short) 7;
    public static final short SENSOR_TYPE_TWO_CHIP = (short) 3;
    public static final short SHARPNESS_HARD = (short) 2;
    public static final short SHARPNESS_NORMAL = (short) 0;
    public static final short SHARPNESS_SOFT = (short) 1;
    private static final int SIGNATURE_CHECK_SIZE = 5000;
    static final byte START_CODE = (byte) 42;
    public static final short SUBJECT_DISTANCE_RANGE_CLOSE_VIEW = (short) 2;
    public static final short SUBJECT_DISTANCE_RANGE_DISTANT_VIEW = (short) 3;
    public static final short SUBJECT_DISTANCE_RANGE_MACRO = (short) 1;
    public static final short SUBJECT_DISTANCE_RANGE_UNKNOWN = (short) 0;
    private static final String TAG = "ExifInterface";
    public static final String TAG_APERTURE_VALUE = "ApertureValue";
    public static final String TAG_ARTIST = "Artist";
    public static final String TAG_BITS_PER_SAMPLE = "BitsPerSample";
    public static final String TAG_BODY_SERIAL_NUMBER = "BodySerialNumber";
    public static final String TAG_BRIGHTNESS_VALUE = "BrightnessValue";
    public static final String TAG_CAMARA_OWNER_NAME = "CameraOwnerName";
    public static final String TAG_CFA_PATTERN = "CFAPattern";
    public static final String TAG_COLOR_SPACE = "ColorSpace";
    public static final String TAG_COMPONENTS_CONFIGURATION = "ComponentsConfiguration";
    public static final String TAG_COMPRESSED_BITS_PER_PIXEL = "CompressedBitsPerPixel";
    public static final String TAG_COMPRESSION = "Compression";
    public static final String TAG_CONTRAST = "Contrast";
    public static final String TAG_COPYRIGHT = "Copyright";
    public static final String TAG_CUSTOM_RENDERED = "CustomRendered";
    public static final String TAG_DATETIME = "DateTime";
    public static final String TAG_DATETIME_DIGITIZED = "DateTimeDigitized";
    public static final String TAG_DATETIME_ORIGINAL = "DateTimeOriginal";
    public static final String TAG_DEFAULT_CROP_SIZE = "DefaultCropSize";
    public static final String TAG_DEVICE_SETTING_DESCRIPTION = "DeviceSettingDescription";
    public static final String TAG_DIGITAL_ZOOM_RATIO = "DigitalZoomRatio";
    public static final String TAG_DNG_VERSION = "DNGVersion";
    private static final String TAG_EXIF_IFD_POINTER = "ExifIFDPointer";
    public static final String TAG_EXIF_VERSION = "ExifVersion";
    public static final String TAG_EXPOSURE_BIAS_VALUE = "ExposureBiasValue";
    public static final String TAG_EXPOSURE_INDEX = "ExposureIndex";
    public static final String TAG_EXPOSURE_MODE = "ExposureMode";
    public static final String TAG_EXPOSURE_PROGRAM = "ExposureProgram";
    public static final String TAG_EXPOSURE_TIME = "ExposureTime";
    public static final String TAG_FILE_SOURCE = "FileSource";
    public static final String TAG_FLASH = "Flash";
    public static final String TAG_FLASHPIX_VERSION = "FlashpixVersion";
    public static final String TAG_FLASH_ENERGY = "FlashEnergy";
    public static final String TAG_FOCAL_LENGTH = "FocalLength";
    public static final String TAG_FOCAL_LENGTH_IN_35MM_FILM = "FocalLengthIn35mmFilm";
    public static final String TAG_FOCAL_PLANE_RESOLUTION_UNIT = "FocalPlaneResolutionUnit";
    public static final String TAG_FOCAL_PLANE_X_RESOLUTION = "FocalPlaneXResolution";
    public static final String TAG_FOCAL_PLANE_Y_RESOLUTION = "FocalPlaneYResolution";
    public static final String TAG_F_NUMBER = "FNumber";
    public static final String TAG_GAIN_CONTROL = "GainControl";
    public static final String TAG_GAMMA = "Gamma";
    public static final String TAG_GPS_ALTITUDE = "GPSAltitude";
    public static final String TAG_GPS_ALTITUDE_REF = "GPSAltitudeRef";
    public static final String TAG_GPS_AREA_INFORMATION = "GPSAreaInformation";
    public static final String TAG_GPS_DATESTAMP = "GPSDateStamp";
    public static final String TAG_GPS_DEST_BEARING = "GPSDestBearing";
    public static final String TAG_GPS_DEST_BEARING_REF = "GPSDestBearingRef";
    public static final String TAG_GPS_DEST_DISTANCE = "GPSDestDistance";
    public static final String TAG_GPS_DEST_DISTANCE_REF = "GPSDestDistanceRef";
    public static final String TAG_GPS_DEST_LATITUDE = "GPSDestLatitude";
    public static final String TAG_GPS_DEST_LATITUDE_REF = "GPSDestLatitudeRef";
    public static final String TAG_GPS_DEST_LONGITUDE = "GPSDestLongitude";
    public static final String TAG_GPS_DEST_LONGITUDE_REF = "GPSDestLongitudeRef";
    public static final String TAG_GPS_DIFFERENTIAL = "GPSDifferential";
    public static final String TAG_GPS_DOP = "GPSDOP";
    public static final String TAG_GPS_H_POSITIONING_ERROR = "GPSHPositioningError";
    public static final String TAG_GPS_IMG_DIRECTION = "GPSImgDirection";
    public static final String TAG_GPS_IMG_DIRECTION_REF = "GPSImgDirectionRef";
    private static final String TAG_GPS_INFO_IFD_POINTER = "GPSInfoIFDPointer";
    public static final String TAG_GPS_LATITUDE = "GPSLatitude";
    public static final String TAG_GPS_LATITUDE_REF = "GPSLatitudeRef";
    public static final String TAG_GPS_LONGITUDE = "GPSLongitude";
    public static final String TAG_GPS_LONGITUDE_REF = "GPSLongitudeRef";
    public static final String TAG_GPS_MAP_DATUM = "GPSMapDatum";
    public static final String TAG_GPS_MEASURE_MODE = "GPSMeasureMode";
    public static final String TAG_GPS_PROCESSING_METHOD = "GPSProcessingMethod";
    public static final String TAG_GPS_SATELLITES = "GPSSatellites";
    public static final String TAG_GPS_SPEED = "GPSSpeed";
    public static final String TAG_GPS_SPEED_REF = "GPSSpeedRef";
    public static final String TAG_GPS_STATUS = "GPSStatus";
    public static final String TAG_GPS_TIMESTAMP = "GPSTimeStamp";
    public static final String TAG_GPS_TRACK = "GPSTrack";
    public static final String TAG_GPS_TRACK_REF = "GPSTrackRef";
    public static final String TAG_GPS_VERSION_ID = "GPSVersionID";
    private static final String TAG_HAS_THUMBNAIL = "HasThumbnail";
    public static final String TAG_IMAGE_DESCRIPTION = "ImageDescription";
    public static final String TAG_IMAGE_LENGTH = "ImageLength";
    public static final String TAG_IMAGE_UNIQUE_ID = "ImageUniqueID";
    public static final String TAG_IMAGE_WIDTH = "ImageWidth";
    private static final String TAG_INTEROPERABILITY_IFD_POINTER = "InteroperabilityIFDPointer";
    public static final String TAG_INTEROPERABILITY_INDEX = "InteroperabilityIndex";
    public static final String TAG_ISO_SPEED = "ISOSpeed";
    public static final String TAG_ISO_SPEED_LATITUDE_YYY = "ISOSpeedLatitudeyyy";
    public static final String TAG_ISO_SPEED_LATITUDE_ZZZ = "ISOSpeedLatitudezzz";
    @Deprecated
    public static final String TAG_ISO_SPEED_RATINGS = "ISOSpeedRatings";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT = "JPEGInterchangeFormat";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = "JPEGInterchangeFormatLength";
    public static final String TAG_LENS_MAKE = "LensMake";
    public static final String TAG_LENS_MODEL = "LensModel";
    public static final String TAG_LENS_SERIAL_NUMBER = "LensSerialNumber";
    public static final String TAG_LENS_SPECIFICATION = "LensSpecification";
    public static final String TAG_LIGHT_SOURCE = "LightSource";
    public static final String TAG_MAKE = "Make";
    public static final String TAG_MAKER_NOTE = "MakerNote";
    public static final String TAG_MAX_APERTURE_VALUE = "MaxApertureValue";
    public static final String TAG_METERING_MODE = "MeteringMode";
    public static final String TAG_MODEL = "Model";
    public static final String TAG_NEW_SUBFILE_TYPE = "NewSubfileType";
    public static final String TAG_OECF = "OECF";
    public static final String TAG_ORF_ASPECT_FRAME = "AspectFrame";
    private static final String TAG_ORF_CAMERA_SETTINGS_IFD_POINTER = "CameraSettingsIFDPointer";
    private static final String TAG_ORF_IMAGE_PROCESSING_IFD_POINTER = "ImageProcessingIFDPointer";
    public static final String TAG_ORF_PREVIEW_IMAGE_LENGTH = "PreviewImageLength";
    public static final String TAG_ORF_PREVIEW_IMAGE_START = "PreviewImageStart";
    public static final String TAG_ORF_THUMBNAIL_IMAGE = "ThumbnailImage";
    public static final String TAG_ORIENTATION = "Orientation";
    public static final String TAG_PHOTOGRAPHIC_SENSITIVITY = "PhotographicSensitivity";
    public static final String TAG_PHOTOMETRIC_INTERPRETATION = "PhotometricInterpretation";
    public static final String TAG_PIXEL_X_DIMENSION = "PixelXDimension";
    public static final String TAG_PIXEL_Y_DIMENSION = "PixelYDimension";
    public static final String TAG_PLANAR_CONFIGURATION = "PlanarConfiguration";
    public static final String TAG_PRIMARY_CHROMATICITIES = "PrimaryChromaticities";
    private static final ExifTag TAG_RAF_IMAGE_SIZE = new ExifTag(TAG_STRIP_OFFSETS, 273, 3);
    public static final String TAG_RECOMMENDED_EXPOSURE_INDEX = "RecommendedExposureIndex";
    public static final String TAG_REFERENCE_BLACK_WHITE = "ReferenceBlackWhite";
    public static final String TAG_RELATED_SOUND_FILE = "RelatedSoundFile";
    public static final String TAG_RESOLUTION_UNIT = "ResolutionUnit";
    public static final String TAG_ROWS_PER_STRIP = "RowsPerStrip";
    public static final String TAG_RW2_ISO = "ISO";
    public static final String TAG_RW2_JPG_FROM_RAW = "JpgFromRaw";
    public static final String TAG_RW2_SENSOR_BOTTOM_BORDER = "SensorBottomBorder";
    public static final String TAG_RW2_SENSOR_LEFT_BORDER = "SensorLeftBorder";
    public static final String TAG_RW2_SENSOR_RIGHT_BORDER = "SensorRightBorder";
    public static final String TAG_RW2_SENSOR_TOP_BORDER = "SensorTopBorder";
    public static final String TAG_SAMPLES_PER_PIXEL = "SamplesPerPixel";
    public static final String TAG_SATURATION = "Saturation";
    public static final String TAG_SCENE_CAPTURE_TYPE = "SceneCaptureType";
    public static final String TAG_SCENE_TYPE = "SceneType";
    public static final String TAG_SENSING_METHOD = "SensingMethod";
    public static final String TAG_SENSITIVITY_TYPE = "SensitivityType";
    public static final String TAG_SHARPNESS = "Sharpness";
    public static final String TAG_SHUTTER_SPEED_VALUE = "ShutterSpeedValue";
    public static final String TAG_SOFTWARE = "Software";
    public static final String TAG_SPATIAL_FREQUENCY_RESPONSE = "SpatialFrequencyResponse";
    public static final String TAG_SPECTRAL_SENSITIVITY = "SpectralSensitivity";
    public static final String TAG_STANDARD_OUTPUT_SENSITIVITY = "StandardOutputSensitivity";
    public static final String TAG_STRIP_BYTE_COUNTS = "StripByteCounts";
    public static final String TAG_STRIP_OFFSETS = "StripOffsets";
    public static final String TAG_SUBFILE_TYPE = "SubfileType";
    public static final String TAG_SUBJECT_AREA = "SubjectArea";
    public static final String TAG_SUBJECT_DISTANCE = "SubjectDistance";
    public static final String TAG_SUBJECT_DISTANCE_RANGE = "SubjectDistanceRange";
    public static final String TAG_SUBJECT_LOCATION = "SubjectLocation";
    public static final String TAG_SUBSEC_TIME = "SubSecTime";
    public static final String TAG_SUBSEC_TIME_DIGITIZED = "SubSecTimeDigitized";
    public static final String TAG_SUBSEC_TIME_ORIGINAL = "SubSecTimeOriginal";
    private static final String TAG_SUB_IFD_POINTER = "SubIFDPointer";
    private static final String TAG_THUMBNAIL_DATA = "ThumbnailData";
    public static final String TAG_THUMBNAIL_IMAGE_LENGTH = "ThumbnailImageLength";
    public static final String TAG_THUMBNAIL_IMAGE_WIDTH = "ThumbnailImageWidth";
    private static final String TAG_THUMBNAIL_LENGTH = "ThumbnailLength";
    private static final String TAG_THUMBNAIL_OFFSET = "ThumbnailOffset";
    public static final String TAG_TRANSFER_FUNCTION = "TransferFunction";
    public static final String TAG_USER_COMMENT = "UserComment";
    public static final String TAG_WHITE_BALANCE = "WhiteBalance";
    public static final String TAG_WHITE_POINT = "WhitePoint";
    public static final String TAG_X_RESOLUTION = "XResolution";
    public static final String TAG_Y_CB_CR_COEFFICIENTS = "YCbCrCoefficients";
    public static final String TAG_Y_CB_CR_POSITIONING = "YCbCrPositioning";
    public static final String TAG_Y_CB_CR_SUB_SAMPLING = "YCbCrSubSampling";
    public static final String TAG_Y_RESOLUTION = "YResolution";
    @Deprecated
    public static final int WHITEBALANCE_AUTO = 0;
    @Deprecated
    public static final int WHITEBALANCE_MANUAL = 1;
    public static final short WHITE_BALANCE_AUTO = (short) 0;
    public static final short WHITE_BALANCE_MANUAL = (short) 1;
    public static final short Y_CB_CR_POSITIONING_CENTERED = (short) 1;
    public static final short Y_CB_CR_POSITIONING_CO_SITED = (short) 2;
    private static final HashMap<Integer, Integer> sExifPointerTagMap = new HashMap();
    private static final HashMap<Integer, ExifTag>[] sExifTagMapsForReading = new HashMap[EXIF_TAGS.length];
    private static final HashMap<String, ExifTag>[] sExifTagMapsForWriting = new HashMap[EXIF_TAGS.length];
    private static SimpleDateFormat sFormatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    private static final Pattern sGpsTimestampPattern = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
    private static final Pattern sNonZeroTimePattern = Pattern.compile(".*[1-9].*");
    private static final HashSet<String> sTagSetForCompatibility = new HashSet(Arrays.asList(new String[]{TAG_F_NUMBER, TAG_DIGITAL_ZOOM_RATIO, TAG_EXPOSURE_TIME, TAG_SUBJECT_DISTANCE, TAG_GPS_TIMESTAMP}));
    private final AssetInputStream mAssetInputStream;
    private final HashMap<String, ExifAttribute>[] mAttributes;
    private ByteOrder mExifByteOrder;
    private int mExifOffset;
    private final String mFilename;
    private boolean mHasThumbnail;
    private boolean mIsSupportedFile;
    private int mMimeType;
    private int mOrfMakerNoteOffset;
    private int mOrfThumbnailLength;
    private int mOrfThumbnailOffset;
    private int mRw2JpgFromRawOffset;
    private byte[] mThumbnailBytes;
    private int mThumbnailCompression;
    private int mThumbnailLength;
    private int mThumbnailOffset;

    private static class ByteOrderedDataInputStream extends InputStream implements DataInput {
        private static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
        private static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        private ByteOrder mByteOrder;
        private DataInputStream mDataInputStream;
        private final int mLength;
        private int mPosition;

        public ByteOrderedDataInputStream(InputStream inputStream) throws IOException {
            this.mByteOrder = ByteOrder.BIG_ENDIAN;
            this.mDataInputStream = new DataInputStream(inputStream);
            this.mLength = this.mDataInputStream.available();
            this.mPosition = 0;
            this.mDataInputStream.mark(this.mLength);
        }

        public ByteOrderedDataInputStream(byte[] bArr) throws IOException {
            this(new ByteArrayInputStream(bArr));
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public void seek(long j) throws IOException {
            if (((long) this.mPosition) > j) {
                this.mPosition = 0;
                this.mDataInputStream.reset();
                this.mDataInputStream.mark(this.mLength);
            } else {
                j -= (long) this.mPosition;
            }
            if (skipBytes((int) j) != ((int) j)) {
                throw new IOException("Couldn't seek up to the byteCount");
            }
        }

        public int peek() {
            return this.mPosition;
        }

        public int available() throws IOException {
            return this.mDataInputStream.available();
        }

        public int read() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.read();
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.mDataInputStream.read(bArr, i, i2);
            this.mPosition += read;
            return read;
        }

        public int readUnsignedByte() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readUnsignedByte();
        }

        public String readLine() throws IOException {
            Log.d(ExifInterface.TAG, "Currently unsupported");
            return null;
        }

        public boolean readBoolean() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readBoolean();
        }

        public char readChar() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readChar();
        }

        public String readUTF() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readUTF();
        }

        public void readFully(byte[] bArr, int i, int i2) throws IOException {
            this.mPosition += i2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            } else if (this.mDataInputStream.read(bArr, i, i2) != i2) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        public void readFully(byte[] bArr) throws IOException {
            this.mPosition += bArr.length;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            } else if (this.mDataInputStream.read(bArr, 0, bArr.length) != bArr.length) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        public byte readByte() throws IOException {
            this.mPosition++;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }

        public short readShort() throws IOException {
            this.mPosition += 2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return (short) (read + (read2 << 8));
            } else {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (short) ((read << 8) + read2);
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public int readInt() throws IOException {
            this.mPosition += 4;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            if ((((read | read2) | read3) | read4) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return read + ((read2 << 8) + ((read3 << 16) + (read4 << 24)));
            } else if (this.mByteOrder == BIG_ENDIAN) {
                return (((read << 24) + (read2 << 16)) + (read3 << 8)) + read4;
            } else {
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public int skipBytes(int i) throws IOException {
            int min = Math.min(i, this.mLength - this.mPosition);
            int i2 = 0;
            while (i2 < min) {
                i2 += this.mDataInputStream.skipBytes(min - i2);
            }
            this.mPosition += i2;
            return i2;
        }

        public int readUnsignedShort() throws IOException {
            this.mPosition += 2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return read + (read2 << 8);
            } else {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (read << 8) + read2;
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public long readUnsignedInt() throws IOException {
            return ((long) readInt()) & 4294967295L;
        }

        public long readLong() throws IOException {
            this.mPosition += 8;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            int read5 = this.mDataInputStream.read();
            int read6 = this.mDataInputStream.read();
            int read7 = this.mDataInputStream.read();
            int read8 = this.mDataInputStream.read();
            if ((((((((read | read2) | read3) | read4) | read5) | read6) | read7) | read8) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return ((long) read) + (((((long) read3) << 16) + (((((long) read5) << 32) + (((((long) read7) << 48) + (((long) read8) << 56)) + (((long) read6) << 40))) + (((long) read4) << 24))) + (((long) read2) << 8));
            } else if (this.mByteOrder == BIG_ENDIAN) {
                return (((((((((long) read2) << 48) + (((long) read) << 56)) + (((long) read3) << 40)) + (((long) read4) << 32)) + (((long) read5) << 24)) + (((long) read6) << 16)) + (((long) read7) << 8)) + ((long) read8);
            } else {
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readInt());
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readLong());
        }
    }

    private static class ByteOrderedDataOutputStream extends FilterOutputStream {
        private ByteOrder mByteOrder;
        private final OutputStream mOutputStream;

        public ByteOrderedDataOutputStream(OutputStream outputStream, ByteOrder byteOrder) {
            super(outputStream);
            this.mOutputStream = outputStream;
            this.mByteOrder = byteOrder;
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public void write(byte[] bArr) throws IOException {
            this.mOutputStream.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.mOutputStream.write(bArr, i, i2);
        }

        public void writeByte(int i) throws IOException {
            this.mOutputStream.write(i);
        }

        public void writeShort(short s) throws IOException {
            if (this.mByteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write((s >>> 0) & 255);
                this.mOutputStream.write((s >>> 8) & 255);
            } else if (this.mByteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((s >>> 8) & 255);
                this.mOutputStream.write((s >>> 0) & 255);
            }
        }

        public void writeInt(int i) throws IOException {
            if (this.mByteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write((i >>> 0) & 255);
                this.mOutputStream.write((i >>> 8) & 255);
                this.mOutputStream.write((i >>> 16) & 255);
                this.mOutputStream.write((i >>> 24) & 255);
            } else if (this.mByteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((i >>> 24) & 255);
                this.mOutputStream.write((i >>> 16) & 255);
                this.mOutputStream.write((i >>> 8) & 255);
                this.mOutputStream.write((i >>> 0) & 255);
            }
        }

        public void writeUnsignedShort(int i) throws IOException {
            writeShort((short) i);
        }

        public void writeUnsignedInt(long j) throws IOException {
            writeInt((int) j);
        }
    }

    private static class ExifAttribute {
        public final byte[] bytes;
        public final int format;
        public final int numberOfComponents;

        private ExifAttribute(int i, int i2, byte[] bArr) {
            this.format = i;
            this.numberOfComponents = i2;
            this.bytes = bArr;
        }

        public static ExifAttribute createUShort(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[3] * iArr.length)]);
            wrap.order(byteOrder);
            for (int i : iArr) {
                wrap.putShort((short) i);
            }
            return new ExifAttribute(3, iArr.length, wrap.array());
        }

        public static ExifAttribute createUShort(int i, ByteOrder byteOrder) {
            return createUShort(new int[]{i}, byteOrder);
        }

        public static ExifAttribute createULong(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[4] * jArr.length)]);
            wrap.order(byteOrder);
            for (long j : jArr) {
                wrap.putInt((int) j);
            }
            return new ExifAttribute(4, jArr.length, wrap.array());
        }

        public static ExifAttribute createULong(long j, ByteOrder byteOrder) {
            return createULong(new long[]{j}, byteOrder);
        }

        public static ExifAttribute createSLong(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[9] * iArr.length)]);
            wrap.order(byteOrder);
            for (int putInt : iArr) {
                wrap.putInt(putInt);
            }
            return new ExifAttribute(9, iArr.length, wrap.array());
        }

        public static ExifAttribute createSLong(int i, ByteOrder byteOrder) {
            return createSLong(new int[]{i}, byteOrder);
        }

        public static ExifAttribute createByte(String str) {
            if (str.length() != 1 || str.charAt(0) < '0' || str.charAt(0) > '1') {
                byte[] bytes = str.getBytes(ExifInterface.ASCII);
                return new ExifAttribute(1, bytes.length, bytes);
            }
            bytes = new byte[]{(byte) (str.charAt(0) - 48)};
            return new ExifAttribute(1, bytes.length, bytes);
        }

        public static ExifAttribute createString(String str) {
            byte[] bytes = (str + '\u0000').getBytes(ExifInterface.ASCII);
            return new ExifAttribute(2, bytes.length, bytes);
        }

        public static ExifAttribute createURational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[5] * rationalArr.length)]);
            wrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                wrap.putInt((int) rational.numerator);
                wrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(5, rationalArr.length, wrap.array());
        }

        public static ExifAttribute createURational(Rational rational, ByteOrder byteOrder) {
            return createURational(new Rational[]{rational}, byteOrder);
        }

        public static ExifAttribute createSRational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[10] * rationalArr.length)]);
            wrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                wrap.putInt((int) rational.numerator);
                wrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(10, rationalArr.length, wrap.array());
        }

        public static ExifAttribute createSRational(Rational rational, ByteOrder byteOrder) {
            return createSRational(new Rational[]{rational}, byteOrder);
        }

        public static ExifAttribute createDouble(double[] dArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[12] * dArr.length)]);
            wrap.order(byteOrder);
            for (double putDouble : dArr) {
                wrap.putDouble(putDouble);
            }
            return new ExifAttribute(12, dArr.length, wrap.array());
        }

        public static ExifAttribute createDouble(double d, ByteOrder byteOrder) {
            return createDouble(new double[]{d}, byteOrder);
        }

        public String toString() {
            return "(" + ExifInterface.IFD_FORMAT_NAMES[this.format] + ", data length:" + this.bytes.length + ")";
        }

        private Object getValue(ByteOrder byteOrder) {
            Throwable e;
            ByteOrderedDataInputStream byteOrderedDataInputStream;
            int i = 1;
            int i2 = 0;
            ByteOrderedDataInputStream byteOrderedDataInputStream2;
            try {
                byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(this.bytes);
                try {
                    byteOrderedDataInputStream2.setByteOrder(byteOrder);
                    Object str;
                    int i3;
                    switch (this.format) {
                        case 1:
                        case 6:
                            if (this.bytes.length != 1 || this.bytes[0] < (byte) 0 || this.bytes[0] > (byte) 1) {
                                str = new String(this.bytes, ExifInterface.ASCII);
                                if (byteOrderedDataInputStream2 == null) {
                                    return str;
                                }
                                try {
                                    byteOrderedDataInputStream2.close();
                                    return str;
                                } catch (Throwable e2) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e2);
                                    return str;
                                }
                            }
                            str = new String(new char[]{(char) (this.bytes[0] + 48)});
                            if (byteOrderedDataInputStream2 == null) {
                                return str;
                            }
                            try {
                                byteOrderedDataInputStream2.close();
                                return str;
                            } catch (Throwable e22) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e22);
                                return str;
                            }
                        case 2:
                        case 7:
                            StringBuilder stringBuilder;
                            byte b;
                            if (this.numberOfComponents >= ExifInterface.EXIF_ASCII_PREFIX.length) {
                                for (int i4 = 0; i4 < ExifInterface.EXIF_ASCII_PREFIX.length; i4++) {
                                    if (this.bytes[i4] != ExifInterface.EXIF_ASCII_PREFIX[i4]) {
                                        i = 0;
                                        if (i != 0) {
                                            i = ExifInterface.EXIF_ASCII_PREFIX.length;
                                            stringBuilder = new StringBuilder();
                                            for (i = 
/*
Method generation error in method: android.support.media.ExifInterface.ExifAttribute.getValue(java.nio.ByteOrder):java.lang.Object, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r0_9 'i' int) = (r0_8 'i' int), (r0_13 'i' int) binds: {(r0_13 'i' int)=B:187:0x024e, (r0_8 'i' int)=B:42:0x008c} in method: android.support.media.ExifInterface.ExifAttribute.getValue(java.nio.ByteOrder):java.lang.Object, dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:190)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:266)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:279)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:279)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:187)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:233)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:219)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 55 more

*/

                                            public double getDoubleValue(ByteOrder byteOrder) {
                                                Object value = getValue(byteOrder);
                                                if (value == null) {
                                                    throw new NumberFormatException("NULL can't be converted to a double value");
                                                } else if (value instanceof String) {
                                                    return Double.parseDouble((String) value);
                                                } else {
                                                    if (value instanceof long[]) {
                                                        long[] jArr = (long[]) value;
                                                        if (jArr.length == 1) {
                                                            return (double) jArr[0];
                                                        }
                                                        throw new NumberFormatException("There are more than one component");
                                                    } else if (value instanceof int[]) {
                                                        int[] iArr = (int[]) value;
                                                        if (iArr.length == 1) {
                                                            return (double) iArr[0];
                                                        }
                                                        throw new NumberFormatException("There are more than one component");
                                                    } else if (value instanceof double[]) {
                                                        double[] dArr = (double[]) value;
                                                        if (dArr.length == 1) {
                                                            return dArr[0];
                                                        }
                                                        throw new NumberFormatException("There are more than one component");
                                                    } else if (value instanceof Rational[]) {
                                                        Rational[] rationalArr = (Rational[]) value;
                                                        if (rationalArr.length == 1) {
                                                            return rationalArr[0].calculate();
                                                        }
                                                        throw new NumberFormatException("There are more than one component");
                                                    } else {
                                                        throw new NumberFormatException("Couldn't find a double value");
                                                    }
                                                }
                                            }

                                            public int getIntValue(ByteOrder byteOrder) {
                                                Object value = getValue(byteOrder);
                                                if (value == null) {
                                                    throw new NumberFormatException("NULL can't be converted to a integer value");
                                                } else if (value instanceof String) {
                                                    return Integer.parseInt((String) value);
                                                } else {
                                                    if (value instanceof long[]) {
                                                        long[] jArr = (long[]) value;
                                                        if (jArr.length == 1) {
                                                            return (int) jArr[0];
                                                        }
                                                        throw new NumberFormatException("There are more than one component");
                                                    } else if (value instanceof int[]) {
                                                        int[] iArr = (int[]) value;
                                                        if (iArr.length == 1) {
                                                            return iArr[0];
                                                        }
                                                        throw new NumberFormatException("There are more than one component");
                                                    } else {
                                                        throw new NumberFormatException("Couldn't find a integer value");
                                                    }
                                                }
                                            }

                                            public String getStringValue(ByteOrder byteOrder) {
                                                int i = 0;
                                                Object value = getValue(byteOrder);
                                                if (value == null) {
                                                    return null;
                                                }
                                                if (value instanceof String) {
                                                    return (String) value;
                                                }
                                                StringBuilder stringBuilder = new StringBuilder();
                                                if (value instanceof long[]) {
                                                    long[] jArr = (long[]) value;
                                                    while (i < jArr.length) {
                                                        stringBuilder.append(jArr[i]);
                                                        if (i + 1 != jArr.length) {
                                                            stringBuilder.append(",");
                                                        }
                                                        i++;
                                                    }
                                                    return stringBuilder.toString();
                                                } else if (value instanceof int[]) {
                                                    int[] iArr = (int[]) value;
                                                    while (i < iArr.length) {
                                                        stringBuilder.append(iArr[i]);
                                                        if (i + 1 != iArr.length) {
                                                            stringBuilder.append(",");
                                                        }
                                                        i++;
                                                    }
                                                    return stringBuilder.toString();
                                                } else if (value instanceof double[]) {
                                                    double[] dArr = (double[]) value;
                                                    while (i < dArr.length) {
                                                        stringBuilder.append(dArr[i]);
                                                        if (i + 1 != dArr.length) {
                                                            stringBuilder.append(",");
                                                        }
                                                        i++;
                                                    }
                                                    return stringBuilder.toString();
                                                } else if (!(value instanceof Rational[])) {
                                                    return null;
                                                } else {
                                                    Rational[] rationalArr = (Rational[]) value;
                                                    while (i < rationalArr.length) {
                                                        stringBuilder.append(rationalArr[i].numerator);
                                                        stringBuilder.append('/');
                                                        stringBuilder.append(rationalArr[i].denominator);
                                                        if (i + 1 != rationalArr.length) {
                                                            stringBuilder.append(",");
                                                        }
                                                        i++;
                                                    }
                                                    return stringBuilder.toString();
                                                }
                                            }

                                            public int size() {
                                                return ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[this.format] * this.numberOfComponents;
                                            }
                                        }

                                        static class ExifTag {
                                            public final String name;
                                            public final int number;
                                            public final int primaryFormat;
                                            public final int secondaryFormat;

                                            private ExifTag(String str, int i, int i2) {
                                                this.name = str;
                                                this.number = i;
                                                this.primaryFormat = i2;
                                                this.secondaryFormat = -1;
                                            }

                                            private ExifTag(String str, int i, int i2, int i3) {
                                                this.name = str;
                                                this.number = i;
                                                this.primaryFormat = i2;
                                                this.secondaryFormat = i3;
                                            }

                                            private boolean isFormatCompatible(int i) {
                                                if (this.primaryFormat == 7 || i == 7 || this.primaryFormat == i || this.secondaryFormat == i) {
                                                    return true;
                                                }
                                                if ((this.primaryFormat == 4 || this.secondaryFormat == 4) && i == 3) {
                                                    return true;
                                                }
                                                if ((this.primaryFormat == 9 || this.secondaryFormat == 9) && i == 8) {
                                                    return true;
                                                }
                                                if ((this.primaryFormat == 12 || this.secondaryFormat == 12) && i == 11) {
                                                    return true;
                                                }
                                                return false;
                                            }
                                        }

                                        @Retention(RetentionPolicy.SOURCE)
                                        public @interface IfdType {
                                        }

                                        private static class Rational {
                                            public final long denominator;
                                            public final long numerator;

                                            private Rational(double d) {
                                                this((long) (10000.0d * d), 10000);
                                            }

                                            private Rational(long j, long j2) {
                                                if (j2 == 0) {
                                                    this.numerator = 0;
                                                    this.denominator = 1;
                                                    return;
                                                }
                                                this.numerator = j;
                                                this.denominator = j2;
                                            }

                                            public String toString() {
                                                return this.numerator + "/" + this.denominator;
                                            }

                                            public double calculate() {
                                                return ((double) this.numerator) / ((double) this.denominator);
                                            }
                                        }

                                        static {
                                            sFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                                            for (int i = 0; i < EXIF_TAGS.length; i++) {
                                                sExifTagMapsForReading[i] = new HashMap();
                                                sExifTagMapsForWriting[i] = new HashMap();
                                                for (ExifTag exifTag : EXIF_TAGS[i]) {
                                                    sExifTagMapsForReading[i].put(Integer.valueOf(exifTag.number), exifTag);
                                                    sExifTagMapsForWriting[i].put(exifTag.name, exifTag);
                                                }
                                            }
                                            sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[0].number), Integer.valueOf(5));
                                            sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[1].number), Integer.valueOf(1));
                                            sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[2].number), Integer.valueOf(2));
                                            sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[3].number), Integer.valueOf(3));
                                            sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[4].number), Integer.valueOf(7));
                                            sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[5].number), Integer.valueOf(8));
                                        }

                                        public ExifInterface(@NonNull String str) throws IOException {
                                            Closeable fileInputStream;
                                            Throwable th;
                                            this.mAttributes = new HashMap[EXIF_TAGS.length];
                                            this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
                                            if (str == null) {
                                                throw new IllegalArgumentException("filename cannot be null");
                                            }
                                            this.mAssetInputStream = null;
                                            this.mFilename = str;
                                            try {
                                                fileInputStream = new FileInputStream(str);
                                                try {
                                                    loadAttributes(fileInputStream);
                                                    closeQuietly(fileInputStream);
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    closeQuietly(fileInputStream);
                                                    throw th;
                                                }
                                            } catch (Throwable th3) {
                                                th = th3;
                                                fileInputStream = null;
                                                closeQuietly(fileInputStream);
                                                throw th;
                                            }
                                        }

                                        public ExifInterface(@NonNull InputStream inputStream) throws IOException {
                                            this.mAttributes = new HashMap[EXIF_TAGS.length];
                                            this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
                                            if (inputStream == null) {
                                                throw new IllegalArgumentException("inputStream cannot be null");
                                            }
                                            this.mFilename = null;
                                            if (inputStream instanceof AssetInputStream) {
                                                this.mAssetInputStream = (AssetInputStream) inputStream;
                                            } else {
                                                this.mAssetInputStream = null;
                                            }
                                            loadAttributes(inputStream);
                                        }

                                        @Nullable
                                        private ExifAttribute getExifAttribute(@NonNull String str) {
                                            if (TAG_ISO_SPEED_RATINGS.equals(str)) {
                                                str = TAG_PHOTOGRAPHIC_SENSITIVITY;
                                            }
                                            for (int i = 0; i < EXIF_TAGS.length; i++) {
                                                ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get(str);
                                                if (exifAttribute != null) {
                                                    return exifAttribute;
                                                }
                                            }
                                            return null;
                                        }

                                        @Nullable
                                        public String getAttribute(@NonNull String str) {
                                            ExifAttribute exifAttribute = getExifAttribute(str);
                                            if (exifAttribute == null) {
                                                return null;
                                            }
                                            if (!sTagSetForCompatibility.contains(str)) {
                                                return exifAttribute.getStringValue(this.mExifByteOrder);
                                            }
                                            if (!str.equals(TAG_GPS_TIMESTAMP)) {
                                                try {
                                                    return Double.toString(exifAttribute.getDoubleValue(this.mExifByteOrder));
                                                } catch (NumberFormatException e) {
                                                    return null;
                                                }
                                            } else if (exifAttribute.format == 5 || exifAttribute.format == 10) {
                                                Rational[] rationalArr = (Rational[]) exifAttribute.getValue(this.mExifByteOrder);
                                                if (rationalArr == null || rationalArr.length != 3) {
                                                    Log.w(TAG, "Invalid GPS Timestamp array. array=" + Arrays.toString(rationalArr));
                                                    return null;
                                                }
                                                return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf((int) (((float) rationalArr[0].numerator) / ((float) rationalArr[0].denominator))), Integer.valueOf((int) (((float) rationalArr[1].numerator) / ((float) rationalArr[1].denominator))), Integer.valueOf((int) (((float) rationalArr[2].numerator) / ((float) rationalArr[2].denominator)))});
                                            } else {
                                                Log.w(TAG, "GPS Timestamp format is not rational. format=" + exifAttribute.format);
                                                return null;
                                            }
                                        }

                                        public int getAttributeInt(@NonNull String str, int i) {
                                            ExifAttribute exifAttribute = getExifAttribute(str);
                                            if (exifAttribute != null) {
                                                try {
                                                    i = exifAttribute.getIntValue(this.mExifByteOrder);
                                                } catch (NumberFormatException e) {
                                                }
                                            }
                                            return i;
                                        }

                                        public double getAttributeDouble(@NonNull String str, double d) {
                                            ExifAttribute exifAttribute = getExifAttribute(str);
                                            if (exifAttribute != null) {
                                                try {
                                                    d = exifAttribute.getDoubleValue(this.mExifByteOrder);
                                                } catch (NumberFormatException e) {
                                                }
                                            }
                                            return d;
                                        }

                                        public void setAttribute(@NonNull String str, @Nullable String str2) {
                                            if (TAG_ISO_SPEED_RATINGS.equals(str)) {
                                                str = TAG_PHOTOGRAPHIC_SENSITIVITY;
                                            }
                                            if (str2 != null && sTagSetForCompatibility.contains(str)) {
                                                if (str.equals(TAG_GPS_TIMESTAMP)) {
                                                    Matcher matcher = sGpsTimestampPattern.matcher(str2);
                                                    if (matcher.find()) {
                                                        str2 = Integer.parseInt(matcher.group(1)) + "/1," + Integer.parseInt(matcher.group(2)) + "/1," + Integer.parseInt(matcher.group(3)) + "/1";
                                                    } else {
                                                        Log.w(TAG, "Invalid value for " + str + " : " + str2);
                                                        return;
                                                    }
                                                }
                                                try {
                                                    str2 = new Rational(Double.parseDouble(str2)).toString();
                                                } catch (NumberFormatException e) {
                                                    Log.w(TAG, "Invalid value for " + str + " : " + str2);
                                                    return;
                                                }
                                            }
                                            for (int i = 0; i < EXIF_TAGS.length; i++) {
                                                if (i != 4 || this.mHasThumbnail) {
                                                    ExifTag exifTag = (ExifTag) sExifTagMapsForWriting[i].get(str);
                                                    if (exifTag != null) {
                                                        if (str2 != null) {
                                                            int i2;
                                                            Pair guessDataFormat = guessDataFormat(str2);
                                                            if (exifTag.primaryFormat == ((Integer) guessDataFormat.first).intValue() || exifTag.primaryFormat == ((Integer) guessDataFormat.second).intValue()) {
                                                                i2 = exifTag.primaryFormat;
                                                            } else if (exifTag.secondaryFormat != -1 && (exifTag.secondaryFormat == ((Integer) guessDataFormat.first).intValue() || exifTag.secondaryFormat == ((Integer) guessDataFormat.second).intValue())) {
                                                                i2 = exifTag.secondaryFormat;
                                                            } else if (exifTag.primaryFormat == 1 || exifTag.primaryFormat == 7 || exifTag.primaryFormat == 2) {
                                                                i2 = exifTag.primaryFormat;
                                                            } else {
                                                                String str3;
                                                                String str4 = TAG;
                                                                StringBuilder append = new StringBuilder().append("Given tag (").append(str).append(") value didn't match with one of expected ").append("formats: ").append(IFD_FORMAT_NAMES[exifTag.primaryFormat]).append(exifTag.secondaryFormat == -1 ? "" : ", " + IFD_FORMAT_NAMES[exifTag.secondaryFormat]).append(" (guess: ").append(IFD_FORMAT_NAMES[((Integer) guessDataFormat.first).intValue()]);
                                                                if (((Integer) guessDataFormat.second).intValue() == -1) {
                                                                    str3 = "";
                                                                } else {
                                                                    str3 = ", " + IFD_FORMAT_NAMES[((Integer) guessDataFormat.second).intValue()];
                                                                }
                                                                Log.w(str4, append.append(str3).append(")").toString());
                                                            }
                                                            String[] split;
                                                            int[] iArr;
                                                            String[] split2;
                                                            Rational[] rationalArr;
                                                            String[] split3;
                                                            switch (i2) {
                                                                case 1:
                                                                    this.mAttributes[i].put(str, ExifAttribute.createByte(str2));
                                                                    break;
                                                                case 2:
                                                                case 7:
                                                                    this.mAttributes[i].put(str, ExifAttribute.createString(str2));
                                                                    break;
                                                                case 3:
                                                                    split = str2.split(",");
                                                                    iArr = new int[split.length];
                                                                    for (i2 = 0; i2 < split.length; i2++) {
                                                                        iArr[i2] = Integer.parseInt(split[i2]);
                                                                    }
                                                                    this.mAttributes[i].put(str, ExifAttribute.createUShort(iArr, this.mExifByteOrder));
                                                                    break;
                                                                case 4:
                                                                    split = str2.split(",");
                                                                    long[] jArr = new long[split.length];
                                                                    for (i2 = 0; i2 < split.length; i2++) {
                                                                        jArr[i2] = Long.parseLong(split[i2]);
                                                                    }
                                                                    this.mAttributes[i].put(str, ExifAttribute.createULong(jArr, this.mExifByteOrder));
                                                                    break;
                                                                case 5:
                                                                    split2 = str2.split(",");
                                                                    rationalArr = new Rational[split2.length];
                                                                    for (i2 = 0; i2 < split2.length; i2++) {
                                                                        split3 = split2[i2].split("/");
                                                                        rationalArr[i2] = new Rational((long) Double.parseDouble(split3[0]), (long) Double.parseDouble(split3[1]));
                                                                    }
                                                                    this.mAttributes[i].put(str, ExifAttribute.createURational(rationalArr, this.mExifByteOrder));
                                                                    break;
                                                                case 9:
                                                                    split = str2.split(",");
                                                                    iArr = new int[split.length];
                                                                    for (i2 = 0; i2 < split.length; i2++) {
                                                                        iArr[i2] = Integer.parseInt(split[i2]);
                                                                    }
                                                                    this.mAttributes[i].put(str, ExifAttribute.createSLong(iArr, this.mExifByteOrder));
                                                                    break;
                                                                case 10:
                                                                    split2 = str2.split(",");
                                                                    rationalArr = new Rational[split2.length];
                                                                    for (i2 = 0; i2 < split2.length; i2++) {
                                                                        split3 = split2[i2].split("/");
                                                                        rationalArr[i2] = new Rational((long) Double.parseDouble(split3[0]), (long) Double.parseDouble(split3[1]));
                                                                    }
                                                                    this.mAttributes[i].put(str, ExifAttribute.createSRational(rationalArr, this.mExifByteOrder));
                                                                    break;
                                                                case 12:
                                                                    split = str2.split(",");
                                                                    double[] dArr = new double[split.length];
                                                                    for (i2 = 0; i2 < split.length; i2++) {
                                                                        dArr[i2] = Double.parseDouble(split[i2]);
                                                                    }
                                                                    this.mAttributes[i].put(str, ExifAttribute.createDouble(dArr, this.mExifByteOrder));
                                                                    break;
                                                                default:
                                                                    Log.w(TAG, "Data format isn't one of expected formats: " + i2);
                                                                    break;
                                                            }
                                                        }
                                                        this.mAttributes[i].remove(str);
                                                    }
                                                }
                                            }
                                        }

                                        public void resetOrientation() {
                                            setAttribute(TAG_ORIENTATION, Integer.toString(1));
                                        }

                                        public void rotate(int i) {
                                            int i2 = 4;
                                            int i3 = 0;
                                            if (i % 90 != 0) {
                                                throw new IllegalArgumentException("degree should be a multiple of 90");
                                            }
                                            int attributeInt = getAttributeInt(TAG_ORIENTATION, 1);
                                            if (ROTATION_ORDER.contains(Integer.valueOf(attributeInt))) {
                                                attributeInt = (ROTATION_ORDER.indexOf(Integer.valueOf(attributeInt)) + (i / 90)) % 4;
                                                if (attributeInt >= 0) {
                                                    i2 = 0;
                                                }
                                                i3 = ((Integer) ROTATION_ORDER.get(i2 + attributeInt)).intValue();
                                            } else if (FLIPPED_ROTATION_ORDER.contains(Integer.valueOf(attributeInt))) {
                                                attributeInt = (FLIPPED_ROTATION_ORDER.indexOf(Integer.valueOf(attributeInt)) + (i / 90)) % 4;
                                                if (attributeInt >= 0) {
                                                    i2 = 0;
                                                }
                                                i3 = ((Integer) FLIPPED_ROTATION_ORDER.get(i2 + attributeInt)).intValue();
                                            }
                                            setAttribute(TAG_ORIENTATION, Integer.toString(i3));
                                        }

                                        public void flipVertically() {
                                            int i = 1;
                                            switch (getAttributeInt(TAG_ORIENTATION, 1)) {
                                                case 1:
                                                    i = 4;
                                                    break;
                                                case 2:
                                                    i = 3;
                                                    break;
                                                case 3:
                                                    i = 2;
                                                    break;
                                                case 4:
                                                    break;
                                                case 5:
                                                    i = 8;
                                                    break;
                                                case 6:
                                                    i = 7;
                                                    break;
                                                case 7:
                                                    i = 6;
                                                    break;
                                                case 8:
                                                    i = 5;
                                                    break;
                                                default:
                                                    i = 0;
                                                    break;
                                            }
                                            setAttribute(TAG_ORIENTATION, Integer.toString(i));
                                        }

                                        public void flipHorizontally() {
                                            int i = 1;
                                            switch (getAttributeInt(TAG_ORIENTATION, 1)) {
                                                case 1:
                                                    i = 2;
                                                    break;
                                                case 2:
                                                    break;
                                                case 3:
                                                    i = 4;
                                                    break;
                                                case 4:
                                                    i = 3;
                                                    break;
                                                case 5:
                                                    i = 6;
                                                    break;
                                                case 6:
                                                    i = 5;
                                                    break;
                                                case 7:
                                                    i = 8;
                                                    break;
                                                case 8:
                                                    i = 7;
                                                    break;
                                                default:
                                                    i = 0;
                                                    break;
                                            }
                                            setAttribute(TAG_ORIENTATION, Integer.toString(i));
                                        }

                                        public boolean isFlipped() {
                                            switch (getAttributeInt(TAG_ORIENTATION, 1)) {
                                                case 2:
                                                case 4:
                                                case 5:
                                                case 7:
                                                    return true;
                                                default:
                                                    return false;
                                            }
                                        }

                                        public int getRotationDegrees() {
                                            switch (getAttributeInt(TAG_ORIENTATION, 1)) {
                                                case 3:
                                                case 4:
                                                    return 180;
                                                case 5:
                                                case 8:
                                                    return 270;
                                                case 6:
                                                case 7:
                                                    return 90;
                                                default:
                                                    return 0;
                                            }
                                        }

                                        private boolean updateAttribute(String str, ExifAttribute exifAttribute) {
                                            int i = 0;
                                            boolean z = false;
                                            while (i < EXIF_TAGS.length) {
                                                if (this.mAttributes[i].containsKey(str)) {
                                                    this.mAttributes[i].put(str, exifAttribute);
                                                    z = true;
                                                }
                                                i++;
                                            }
                                            return z;
                                        }

                                        private void removeAttribute(String str) {
                                            for (int i = 0; i < EXIF_TAGS.length; i++) {
                                                this.mAttributes[i].remove(str);
                                            }
                                        }

                                        private void loadAttributes(@NonNull InputStream inputStream) throws IOException {
                                            int i = 0;
                                            while (i < EXIF_TAGS.length) {
                                                try {
                                                    this.mAttributes[i] = new HashMap();
                                                    i++;
                                                } catch (IOException e) {
                                                    this.mIsSupportedFile = false;
                                                } finally {
                                                    addDefaultValuesForCompatibility();
                                                }
                                            }
                                            InputStream bufferedInputStream = new BufferedInputStream(inputStream, SIGNATURE_CHECK_SIZE);
                                            this.mMimeType = getMimeType((BufferedInputStream) bufferedInputStream);
                                            ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bufferedInputStream);
                                            switch (this.mMimeType) {
                                                case 0:
                                                case 1:
                                                case 2:
                                                case 3:
                                                case 5:
                                                case 6:
                                                case 8:
                                                case 11:
                                                    getRawAttributes(byteOrderedDataInputStream);
                                                    break;
                                                case 4:
                                                    getJpegAttributes(byteOrderedDataInputStream, 0, 0);
                                                    break;
                                                case 7:
                                                    getOrfAttributes(byteOrderedDataInputStream);
                                                    break;
                                                case 9:
                                                    getRafAttributes(byteOrderedDataInputStream);
                                                    break;
                                                case 10:
                                                    getRw2Attributes(byteOrderedDataInputStream);
                                                    break;
                                            }
                                            setThumbnailData(byteOrderedDataInputStream);
                                            this.mIsSupportedFile = true;
                                        }

                                        private void printAttributes() {
                                            for (int i = 0; i < this.mAttributes.length; i++) {
                                                Log.d(TAG, "The size of tag group[" + i + "]: " + this.mAttributes[i].size());
                                                for (Entry entry : this.mAttributes[i].entrySet()) {
                                                    ExifAttribute exifAttribute = (ExifAttribute) entry.getValue();
                                                    Log.d(TAG, "tagName: " + ((String) entry.getKey()) + ", tagType: " + exifAttribute.toString() + ", tagValue: '" + exifAttribute.getStringValue(this.mExifByteOrder) + "'");
                                                }
                                            }
                                        }

                                        public void saveAttributes() throws IOException {
                                            Closeable fileOutputStream;
                                            Throwable th;
                                            Closeable closeable = null;
                                            if (!this.mIsSupportedFile || this.mMimeType != 4) {
                                                throw new IOException("ExifInterface only supports saving attributes on JPEG formats.");
                                            } else if (this.mFilename == null) {
                                                throw new IOException("ExifInterface does not support saving attributes for the current input.");
                                            } else {
                                                this.mThumbnailBytes = getThumbnail();
                                                File file = new File(this.mFilename + ".tmp");
                                                if (new File(this.mFilename).renameTo(file)) {
                                                    try {
                                                        Closeable fileInputStream = new FileInputStream(file);
                                                        try {
                                                            fileOutputStream = new FileOutputStream(this.mFilename);
                                                        } catch (Throwable th2) {
                                                            th = th2;
                                                            fileOutputStream = null;
                                                            closeable = fileInputStream;
                                                            closeQuietly(closeable);
                                                            closeQuietly(fileOutputStream);
                                                            file.delete();
                                                            throw th;
                                                        }
                                                        try {
                                                            saveJpegAttributes(fileInputStream, fileOutputStream);
                                                            closeQuietly(fileInputStream);
                                                            closeQuietly(fileOutputStream);
                                                            file.delete();
                                                            this.mThumbnailBytes = null;
                                                            return;
                                                        } catch (Throwable th3) {
                                                            th = th3;
                                                            closeable = fileInputStream;
                                                            closeQuietly(closeable);
                                                            closeQuietly(fileOutputStream);
                                                            file.delete();
                                                            throw th;
                                                        }
                                                    } catch (Throwable th4) {
                                                        th = th4;
                                                        fileOutputStream = null;
                                                        closeQuietly(closeable);
                                                        closeQuietly(fileOutputStream);
                                                        file.delete();
                                                        throw th;
                                                    }
                                                }
                                                throw new IOException("Could not rename to " + file.getAbsolutePath());
                                            }
                                        }

                                        public boolean hasThumbnail() {
                                            return this.mHasThumbnail;
                                        }

                                        @Nullable
                                        public byte[] getThumbnail() {
                                            if (this.mThumbnailCompression == 6 || this.mThumbnailCompression == 7) {
                                                return getThumbnailBytes();
                                            }
                                            return null;
                                        }

                                        @Nullable
                                        public byte[] getThumbnailBytes() {
                                            Throwable e;
                                            Throwable th;
                                            if (!this.mHasThumbnail) {
                                                return null;
                                            }
                                            if (this.mThumbnailBytes != null) {
                                                return this.mThumbnailBytes;
                                            }
                                            Closeable closeable;
                                            try {
                                                if (this.mAssetInputStream != null) {
                                                    closeable = this.mAssetInputStream;
                                                    try {
                                                        if (closeable.markSupported()) {
                                                            closeable.reset();
                                                        } else {
                                                            Log.d(TAG, "Cannot read thumbnail from inputstream without mark/reset support");
                                                            closeQuietly(closeable);
                                                            return null;
                                                        }
                                                    } catch (IOException e2) {
                                                        e = e2;
                                                        try {
                                                            Log.d(TAG, "Encountered exception while getting thumbnail", e);
                                                            closeQuietly(closeable);
                                                            return null;
                                                        } catch (Throwable th2) {
                                                            th = th2;
                                                            closeQuietly(closeable);
                                                            throw th;
                                                        }
                                                    }
                                                } else if (this.mFilename != null) {
                                                    closeable = new FileInputStream(this.mFilename);
                                                } else {
                                                    closeable = null;
                                                }
                                                if (closeable == null) {
                                                    throw new FileNotFoundException();
                                                } else if (closeable.skip((long) this.mThumbnailOffset) != ((long) this.mThumbnailOffset)) {
                                                    throw new IOException("Corrupted image");
                                                } else {
                                                    byte[] bArr = new byte[this.mThumbnailLength];
                                                    if (closeable.read(bArr) != this.mThumbnailLength) {
                                                        throw new IOException("Corrupted image");
                                                    }
                                                    this.mThumbnailBytes = bArr;
                                                    closeQuietly(closeable);
                                                    return bArr;
                                                }
                                            } catch (IOException e3) {
                                                e = e3;
                                                closeable = null;
                                                Log.d(TAG, "Encountered exception while getting thumbnail", e);
                                                closeQuietly(closeable);
                                                return null;
                                            } catch (Throwable e4) {
                                                closeable = null;
                                                th = e4;
                                                closeQuietly(closeable);
                                                throw th;
                                            }
                                        }

                                        @Nullable
                                        public Bitmap getThumbnailBitmap() {
                                            if (!this.mHasThumbnail) {
                                                return null;
                                            }
                                            if (this.mThumbnailBytes == null) {
                                                this.mThumbnailBytes = getThumbnailBytes();
                                            }
                                            if (this.mThumbnailCompression == 6 || this.mThumbnailCompression == 7) {
                                                return BitmapFactory.decodeByteArray(this.mThumbnailBytes, 0, this.mThumbnailLength);
                                            }
                                            if (this.mThumbnailCompression == 1) {
                                                int[] iArr = new int[(this.mThumbnailBytes.length / 3)];
                                                for (int i = 0; i < iArr.length; i++) {
                                                    iArr[i] = (((this.mThumbnailBytes[i * 3] << 16) + 0) + (this.mThumbnailBytes[(i * 3) + 1] << 8)) + this.mThumbnailBytes[(i * 3) + 2];
                                                }
                                                ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[4].get(TAG_IMAGE_LENGTH);
                                                ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[4].get(TAG_IMAGE_WIDTH);
                                                if (!(exifAttribute == null || exifAttribute2 == null)) {
                                                    return Bitmap.createBitmap(iArr, exifAttribute2.getIntValue(this.mExifByteOrder), exifAttribute.getIntValue(this.mExifByteOrder), Config.ARGB_8888);
                                                }
                                            }
                                            return null;
                                        }

                                        public boolean isThumbnailCompressed() {
                                            return this.mThumbnailCompression == 6 || this.mThumbnailCompression == 7;
                                        }

                                        @Nullable
                                        public long[] getThumbnailRange() {
                                            if (!this.mHasThumbnail) {
                                                return null;
                                            }
                                            return new long[]{(long) this.mThumbnailOffset, (long) this.mThumbnailLength};
                                        }

                                        @Deprecated
                                        public boolean getLatLong(float[] fArr) {
                                            double[] latLong = getLatLong();
                                            if (latLong == null) {
                                                return false;
                                            }
                                            fArr[0] = (float) latLong[0];
                                            fArr[1] = (float) latLong[1];
                                            return true;
                                        }

                                        @Nullable
                                        public double[] getLatLong() {
                                            String attribute = getAttribute(TAG_GPS_LATITUDE);
                                            String attribute2 = getAttribute(TAG_GPS_LATITUDE_REF);
                                            String attribute3 = getAttribute(TAG_GPS_LONGITUDE);
                                            String attribute4 = getAttribute(TAG_GPS_LONGITUDE_REF);
                                            if (!(attribute == null || attribute2 == null || attribute3 == null || attribute4 == null)) {
                                                try {
                                                    double convertRationalLatLonToDouble = convertRationalLatLonToDouble(attribute, attribute2);
                                                    double convertRationalLatLonToDouble2 = convertRationalLatLonToDouble(attribute3, attribute4);
                                                    return new double[]{convertRationalLatLonToDouble, convertRationalLatLonToDouble2};
                                                } catch (IllegalArgumentException e) {
                                                    Log.w(TAG, "Latitude/longitude values are not parseable. " + String.format("latValue=%s, latRef=%s, lngValue=%s, lngRef=%s", new Object[]{attribute, attribute2, attribute3, attribute4}));
                                                }
                                            }
                                            return null;
                                        }

                                        public void setGpsInfo(Location location) {
                                            if (location != null) {
                                                setAttribute(TAG_GPS_PROCESSING_METHOD, location.getProvider());
                                                setLatLong(location.getLatitude(), location.getLongitude());
                                                setAltitude(location.getAltitude());
                                                setAttribute(TAG_GPS_SPEED_REF, "K");
                                                setAttribute(TAG_GPS_SPEED, new Rational((double) ((location.getSpeed() * ((float) TimeUnit.HOURS.toSeconds(1))) / 1000.0f)).toString());
                                                String[] split = sFormatter.format(new Date(location.getTime())).split("\\s+");
                                                setAttribute(TAG_GPS_DATESTAMP, split[0]);
                                                setAttribute(TAG_GPS_TIMESTAMP, split[1]);
                                            }
                                        }

                                        public void setLatLong(double d, double d2) {
                                            if (d < -90.0d || d > 90.0d || Double.isNaN(d)) {
                                                throw new IllegalArgumentException("Latitude value " + d + " is not valid.");
                                            } else if (d2 < -180.0d || d2 > 180.0d || Double.isNaN(d2)) {
                                                throw new IllegalArgumentException("Longitude value " + d2 + " is not valid.");
                                            } else {
                                                setAttribute(TAG_GPS_LATITUDE_REF, d >= 0.0d ? "N" : LATITUDE_SOUTH);
                                                setAttribute(TAG_GPS_LATITUDE, convertDecimalDegree(Math.abs(d)));
                                                setAttribute(TAG_GPS_LONGITUDE_REF, d2 >= 0.0d ? LONGITUDE_EAST : LONGITUDE_WEST);
                                                setAttribute(TAG_GPS_LONGITUDE, convertDecimalDegree(Math.abs(d2)));
                                            }
                                        }

                                        public double getAltitude(double d) {
                                            int i = -1;
                                            double attributeDouble = getAttributeDouble(TAG_GPS_ALTITUDE, -1.0d);
                                            int attributeInt = getAttributeInt(TAG_GPS_ALTITUDE_REF, -1);
                                            if (attributeDouble < 0.0d || attributeInt < 0) {
                                                return d;
                                            }
                                            if (attributeInt != 1) {
                                                i = 1;
                                            }
                                            return attributeDouble * ((double) i);
                                        }

                                        public void setAltitude(double d) {
                                            String str = d >= 0.0d ? "0" : "1";
                                            setAttribute(TAG_GPS_ALTITUDE, new Rational(Math.abs(d)).toString());
                                            setAttribute(TAG_GPS_ALTITUDE_REF, str);
                                        }

                                        public void setDateTime(long j) {
                                            long j2 = j % 1000;
                                            setAttribute(TAG_DATETIME, sFormatter.format(new Date(j)));
                                            setAttribute(TAG_SUBSEC_TIME, Long.toString(j2));
                                        }

                                        public long getDateTime() {
                                            Object attribute = getAttribute(TAG_DATETIME);
                                            if (attribute == null || !sNonZeroTimePattern.matcher(attribute).matches()) {
                                                return -1;
                                            }
                                            try {
                                                Date parse = sFormatter.parse(attribute, new ParsePosition(0));
                                                if (parse == null) {
                                                    return -1;
                                                }
                                                long time = parse.getTime();
                                                String attribute2 = getAttribute(TAG_SUBSEC_TIME);
                                                if (attribute2 == null) {
                                                    return time;
                                                }
                                                try {
                                                    long parseLong = Long.parseLong(attribute2);
                                                    while (parseLong > 1000) {
                                                        parseLong /= 10;
                                                    }
                                                    return time + parseLong;
                                                } catch (NumberFormatException e) {
                                                    return time;
                                                }
                                            } catch (IllegalArgumentException e2) {
                                                return -1;
                                            }
                                        }

                                        public long getGpsDateTime() {
                                            long j = -1;
                                            Object attribute = getAttribute(TAG_GPS_DATESTAMP);
                                            Object attribute2 = getAttribute(TAG_GPS_TIMESTAMP);
                                            if (!(attribute == null || attribute2 == null || (!sNonZeroTimePattern.matcher(attribute).matches() && !sNonZeroTimePattern.matcher(attribute2).matches()))) {
                                                try {
                                                    Date parse = sFormatter.parse(attribute + ' ' + attribute2, new ParsePosition(0));
                                                    if (parse != null) {
                                                        j = parse.getTime();
                                                    }
                                                } catch (IllegalArgumentException e) {
                                                }
                                            }
                                            return j;
                                        }

                                        private static double convertRationalLatLonToDouble(String str, String str2) {
                                            try {
                                                String[] split = str.split(",");
                                                String[] split2 = split[0].split("/");
                                                double parseDouble = Double.parseDouble(split2[0].trim()) / Double.parseDouble(split2[1].trim());
                                                split2 = split[1].split("/");
                                                double parseDouble2 = Double.parseDouble(split2[0].trim()) / Double.parseDouble(split2[1].trim());
                                                split = split[2].split("/");
                                                double parseDouble3 = ((Double.parseDouble(split[0].trim()) / Double.parseDouble(split[1].trim())) / 3600.0d) + (parseDouble + (parseDouble2 / 60.0d));
                                                if (str2.equals(LATITUDE_SOUTH) || str2.equals(LONGITUDE_WEST)) {
                                                    return -parseDouble3;
                                                }
                                                if (str2.equals("N") || str2.equals(LONGITUDE_EAST)) {
                                                    return parseDouble3;
                                                }
                                                throw new IllegalArgumentException();
                                            } catch (NumberFormatException e) {
                                                throw new IllegalArgumentException();
                                            } catch (ArrayIndexOutOfBoundsException e2) {
                                                throw new IllegalArgumentException();
                                            }
                                        }

                                        private String convertDecimalDegree(double d) {
                                            long j = (long) d;
                                            long j2 = (long) ((d - ((double) j)) * 60.0d);
                                            return j + "/1," + j2 + "/1," + Math.round((((d - ((double) j)) - (((double) j2) / 60.0d)) * 3600.0d) * 1.0E7d) + "/10000000";
                                        }

                                        private int getMimeType(BufferedInputStream bufferedInputStream) throws IOException {
                                            bufferedInputStream.mark(SIGNATURE_CHECK_SIZE);
                                            byte[] bArr = new byte[SIGNATURE_CHECK_SIZE];
                                            if (bufferedInputStream.read(bArr) != SIGNATURE_CHECK_SIZE) {
                                                throw new EOFException();
                                            }
                                            bufferedInputStream.reset();
                                            if (isJpegFormat(bArr)) {
                                                return 4;
                                            }
                                            if (isRafFormat(bArr)) {
                                                return 9;
                                            }
                                            if (isOrfFormat(bArr)) {
                                                return 7;
                                            }
                                            if (isRw2Format(bArr)) {
                                                return 10;
                                            }
                                            return 0;
                                        }

                                        private static boolean isJpegFormat(byte[] bArr) throws IOException {
                                            for (int i = 0; i < JPEG_SIGNATURE.length; i++) {
                                                if (bArr[i] != JPEG_SIGNATURE[i]) {
                                                    return false;
                                                }
                                            }
                                            return true;
                                        }

                                        private boolean isRafFormat(byte[] bArr) throws IOException {
                                            byte[] bytes = RAF_SIGNATURE.getBytes(Charset.defaultCharset());
                                            for (int i = 0; i < bytes.length; i++) {
                                                if (bArr[i] != bytes[i]) {
                                                    return false;
                                                }
                                            }
                                            return true;
                                        }

                                        private boolean isOrfFormat(byte[] bArr) throws IOException {
                                            ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
                                            this.mExifByteOrder = readByteOrder(byteOrderedDataInputStream);
                                            byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
                                            short readShort = byteOrderedDataInputStream.readShort();
                                            byteOrderedDataInputStream.close();
                                            return readShort == ORF_SIGNATURE_1 || readShort == ORF_SIGNATURE_2;
                                        }

                                        private boolean isRw2Format(byte[] bArr) throws IOException {
                                            ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
                                            this.mExifByteOrder = readByteOrder(byteOrderedDataInputStream);
                                            byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
                                            short readShort = byteOrderedDataInputStream.readShort();
                                            byteOrderedDataInputStream.close();
                                            return readShort == RW2_SIGNATURE;
                                        }

                                        private void getJpegAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream, int i, int i2) throws IOException {
                                            byteOrderedDataInputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
                                            byteOrderedDataInputStream.seek((long) i);
                                            byte readByte = byteOrderedDataInputStream.readByte();
                                            if (readByte != MARKER) {
                                                throw new IOException("Invalid marker: " + Integer.toHexString(readByte & 255));
                                            }
                                            int i3 = i + 1;
                                            if (byteOrderedDataInputStream.readByte() != MARKER_SOI) {
                                                throw new IOException("Invalid marker: " + Integer.toHexString(readByte & 255));
                                            }
                                            int i4 = i3 + 1;
                                            while (true) {
                                                byte readByte2 = byteOrderedDataInputStream.readByte();
                                                if (readByte2 != MARKER) {
                                                    throw new IOException("Invalid marker:" + Integer.toHexString(readByte2 & 255));
                                                }
                                                i4++;
                                                byte readByte3 = byteOrderedDataInputStream.readByte();
                                                i3 = i4 + 1;
                                                if (readByte3 == MARKER_EOI || readByte3 == MARKER_SOS) {
                                                    byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
                                                    return;
                                                }
                                                i4 = byteOrderedDataInputStream.readUnsignedShort() - 2;
                                                i3 += 2;
                                                if (i4 < 0) {
                                                    throw new IOException("Invalid length");
                                                }
                                                byte[] bArr;
                                                switch (readByte3) {
                                                    case (byte) -64:
                                                    case (byte) -63:
                                                    case (byte) -62:
                                                    case (byte) -61:
                                                    case (byte) -59:
                                                    case (byte) -58:
                                                    case (byte) -57:
                                                    case (byte) -55:
                                                    case (byte) -54:
                                                    case (byte) -53:
                                                    case (byte) -51:
                                                    case (byte) -50:
                                                    case (byte) -49:
                                                        if (byteOrderedDataInputStream.skipBytes(1) == 1) {
                                                            this.mAttributes[i2].put(TAG_IMAGE_LENGTH, ExifAttribute.createULong((long) byteOrderedDataInputStream.readUnsignedShort(), this.mExifByteOrder));
                                                            this.mAttributes[i2].put(TAG_IMAGE_WIDTH, ExifAttribute.createULong((long) byteOrderedDataInputStream.readUnsignedShort(), this.mExifByteOrder));
                                                            i4 -= 5;
                                                            break;
                                                        }
                                                        throw new IOException("Invalid SOFx");
                                                    case (byte) -31:
                                                        if (i4 >= 6) {
                                                            bArr = new byte[6];
                                                            if (byteOrderedDataInputStream.read(bArr) == 6) {
                                                                i3 += 6;
                                                                i4 -= 6;
                                                                if (Arrays.equals(bArr, IDENTIFIER_EXIF_APP1)) {
                                                                    if (i4 > 0) {
                                                                        this.mExifOffset = i3;
                                                                        bArr = new byte[i4];
                                                                        if (byteOrderedDataInputStream.read(bArr) == i4) {
                                                                            i4 += i3;
                                                                            readExifSegment(bArr, i2);
                                                                            i3 = i4;
                                                                            i4 = 0;
                                                                            break;
                                                                        }
                                                                        throw new IOException("Invalid exif");
                                                                    }
                                                                    throw new IOException("Invalid exif");
                                                                }
                                                            }
                                                            throw new IOException("Invalid exif");
                                                        }
                                                        break;
                                                    case (byte) -2:
                                                        bArr = new byte[i4];
                                                        if (byteOrderedDataInputStream.read(bArr) == i4) {
                                                            if (getAttribute(TAG_USER_COMMENT) != null) {
                                                                i4 = 0;
                                                                break;
                                                            }
                                                            this.mAttributes[1].put(TAG_USER_COMMENT, ExifAttribute.createString(new String(bArr, ASCII)));
                                                            i4 = 0;
                                                            break;
                                                        }
                                                        throw new IOException("Invalid exif");
                                                }
                                                if (i4 < 0) {
                                                    throw new IOException("Invalid length");
                                                } else if (byteOrderedDataInputStream.skipBytes(i4) != i4) {
                                                    throw new IOException("Invalid JPEG segment");
                                                } else {
                                                    i4 += i3;
                                                }
                                            }
                                        }

                                        private void getRawAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
                                            parseTiffHeaders(byteOrderedDataInputStream, byteOrderedDataInputStream.available());
                                            readImageFileDirectory(byteOrderedDataInputStream, 0);
                                            updateImageSizeValues(byteOrderedDataInputStream, 0);
                                            updateImageSizeValues(byteOrderedDataInputStream, 5);
                                            updateImageSizeValues(byteOrderedDataInputStream, 4);
                                            validateImages(byteOrderedDataInputStream);
                                            if (this.mMimeType == 8) {
                                                ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[1].get(TAG_MAKER_NOTE);
                                                if (exifAttribute != null) {
                                                    ByteOrderedDataInputStream byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(exifAttribute.bytes);
                                                    byteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
                                                    byteOrderedDataInputStream2.seek(6);
                                                    readImageFileDirectory(byteOrderedDataInputStream2, 9);
                                                    exifAttribute = (ExifAttribute) this.mAttributes[9].get(TAG_COLOR_SPACE);
                                                    if (exifAttribute != null) {
                                                        this.mAttributes[1].put(TAG_COLOR_SPACE, exifAttribute);
                                                    }
                                                }
                                            }
                                        }

                                        private void getRafAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
                                            byteOrderedDataInputStream.skipBytes(84);
                                            byte[] bArr = new byte[4];
                                            byte[] bArr2 = new byte[4];
                                            byteOrderedDataInputStream.read(bArr);
                                            byteOrderedDataInputStream.skipBytes(4);
                                            byteOrderedDataInputStream.read(bArr2);
                                            int i = ByteBuffer.wrap(bArr).getInt();
                                            int i2 = ByteBuffer.wrap(bArr2).getInt();
                                            getJpegAttributes(byteOrderedDataInputStream, i, 5);
                                            byteOrderedDataInputStream.seek((long) i2);
                                            byteOrderedDataInputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
                                            i2 = byteOrderedDataInputStream.readInt();
                                            for (i = 0; i < i2; i++) {
                                                int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
                                                int readUnsignedShort2 = byteOrderedDataInputStream.readUnsignedShort();
                                                if (readUnsignedShort == TAG_RAF_IMAGE_SIZE.number) {
                                                    i = byteOrderedDataInputStream.readShort();
                                                    i2 = byteOrderedDataInputStream.readShort();
                                                    ExifAttribute createUShort = ExifAttribute.createUShort(i, this.mExifByteOrder);
                                                    ExifAttribute createUShort2 = ExifAttribute.createUShort(i2, this.mExifByteOrder);
                                                    this.mAttributes[0].put(TAG_IMAGE_LENGTH, createUShort);
                                                    this.mAttributes[0].put(TAG_IMAGE_WIDTH, createUShort2);
                                                    return;
                                                }
                                                byteOrderedDataInputStream.skipBytes(readUnsignedShort2);
                                            }
                                        }

                                        private void getOrfAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
                                            getRawAttributes(byteOrderedDataInputStream);
                                            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[1].get(TAG_MAKER_NOTE);
                                            if (exifAttribute != null) {
                                                ByteOrderedDataInputStream byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(exifAttribute.bytes);
                                                byteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
                                                byte[] bArr = new byte[ORF_MAKER_NOTE_HEADER_1.length];
                                                byteOrderedDataInputStream2.readFully(bArr);
                                                byteOrderedDataInputStream2.seek(0);
                                                byte[] bArr2 = new byte[ORF_MAKER_NOTE_HEADER_2.length];
                                                byteOrderedDataInputStream2.readFully(bArr2);
                                                if (Arrays.equals(bArr, ORF_MAKER_NOTE_HEADER_1)) {
                                                    byteOrderedDataInputStream2.seek(8);
                                                } else if (Arrays.equals(bArr2, ORF_MAKER_NOTE_HEADER_2)) {
                                                    byteOrderedDataInputStream2.seek(12);
                                                }
                                                readImageFileDirectory(byteOrderedDataInputStream2, 6);
                                                exifAttribute = (ExifAttribute) this.mAttributes[7].get(TAG_ORF_PREVIEW_IMAGE_START);
                                                ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[7].get(TAG_ORF_PREVIEW_IMAGE_LENGTH);
                                                if (!(exifAttribute == null || exifAttribute2 == null)) {
                                                    this.mAttributes[5].put(TAG_JPEG_INTERCHANGE_FORMAT, exifAttribute);
                                                    this.mAttributes[5].put(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, exifAttribute2);
                                                }
                                                exifAttribute = (ExifAttribute) this.mAttributes[8].get(TAG_ORF_ASPECT_FRAME);
                                                if (exifAttribute != null) {
                                                    int[] iArr = (int[]) exifAttribute.getValue(this.mExifByteOrder);
                                                    if (iArr == null || iArr.length != 4) {
                                                        Log.w(TAG, "Invalid aspect frame values. frame=" + Arrays.toString(iArr));
                                                    } else if (iArr[2] > iArr[0] && iArr[3] > iArr[1]) {
                                                        int i = (iArr[2] - iArr[0]) + 1;
                                                        int i2 = (iArr[3] - iArr[1]) + 1;
                                                        if (i < i2) {
                                                            i += i2;
                                                            i2 = i - i2;
                                                            i -= i2;
                                                        }
                                                        exifAttribute2 = ExifAttribute.createUShort(i, this.mExifByteOrder);
                                                        exifAttribute = ExifAttribute.createUShort(i2, this.mExifByteOrder);
                                                        this.mAttributes[0].put(TAG_IMAGE_WIDTH, exifAttribute2);
                                                        this.mAttributes[0].put(TAG_IMAGE_LENGTH, exifAttribute);
                                                    }
                                                }
                                            }
                                        }

                                        private void getRw2Attributes(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
                                            getRawAttributes(byteOrderedDataInputStream);
                                            if (((ExifAttribute) this.mAttributes[0].get(TAG_RW2_JPG_FROM_RAW)) != null) {
                                                getJpegAttributes(byteOrderedDataInputStream, this.mRw2JpgFromRawOffset, 5);
                                            }
                                            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[0].get(TAG_RW2_ISO);
                                            ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[1].get(TAG_PHOTOGRAPHIC_SENSITIVITY);
                                            if (exifAttribute != null && exifAttribute2 == null) {
                                                this.mAttributes[1].put(TAG_PHOTOGRAPHIC_SENSITIVITY, exifAttribute);
                                            }
                                        }

                                        private void saveJpegAttributes(InputStream inputStream, OutputStream outputStream) throws IOException {
                                            InputStream dataInputStream = new DataInputStream(inputStream);
                                            OutputStream byteOrderedDataOutputStream = new ByteOrderedDataOutputStream(outputStream, ByteOrder.BIG_ENDIAN);
                                            if (dataInputStream.readByte() != MARKER) {
                                                throw new IOException("Invalid marker");
                                            }
                                            byteOrderedDataOutputStream.writeByte(-1);
                                            if (dataInputStream.readByte() != MARKER_SOI) {
                                                throw new IOException("Invalid marker");
                                            }
                                            byteOrderedDataOutputStream.writeByte(-40);
                                            byteOrderedDataOutputStream.writeByte(-1);
                                            byteOrderedDataOutputStream.writeByte(-31);
                                            writeExifSegment(byteOrderedDataOutputStream, 6);
                                            byte[] bArr = new byte[4096];
                                            while (dataInputStream.readByte() == MARKER) {
                                                byte readByte = dataInputStream.readByte();
                                                int readUnsignedShort;
                                                int read;
                                                switch (readByte) {
                                                    case (byte) -39:
                                                    case (byte) -38:
                                                        byteOrderedDataOutputStream.writeByte(-1);
                                                        byteOrderedDataOutputStream.writeByte(readByte);
                                                        copy(dataInputStream, byteOrderedDataOutputStream);
                                                        return;
                                                    case (byte) -31:
                                                        readUnsignedShort = dataInputStream.readUnsignedShort() - 2;
                                                        if (readUnsignedShort >= 0) {
                                                            byte[] bArr2 = new byte[6];
                                                            if (readUnsignedShort >= 6) {
                                                                if (dataInputStream.read(bArr2) == 6) {
                                                                    if (Arrays.equals(bArr2, IDENTIFIER_EXIF_APP1)) {
                                                                        if (dataInputStream.skipBytes(readUnsignedShort - 6) == readUnsignedShort - 6) {
                                                                            break;
                                                                        }
                                                                        throw new IOException("Invalid length");
                                                                    }
                                                                }
                                                                throw new IOException("Invalid exif");
                                                            }
                                                            byteOrderedDataOutputStream.writeByte(-1);
                                                            byteOrderedDataOutputStream.writeByte(readByte);
                                                            byteOrderedDataOutputStream.writeUnsignedShort(readUnsignedShort + 2);
                                                            if (readUnsignedShort >= 6) {
                                                                readUnsignedShort -= 6;
                                                                byteOrderedDataOutputStream.write(bArr2);
                                                            }
                                                            while (readUnsignedShort > 0) {
                                                                read = dataInputStream.read(bArr, 0, Math.min(readUnsignedShort, bArr.length));
                                                                if (read < 0) {
                                                                    break;
                                                                }
                                                                byteOrderedDataOutputStream.write(bArr, 0, read);
                                                                readUnsignedShort -= read;
                                                            }
                                                            break;
                                                        }
                                                        throw new IOException("Invalid length");
                                                    default:
                                                        byteOrderedDataOutputStream.writeByte(-1);
                                                        byteOrderedDataOutputStream.writeByte(readByte);
                                                        readUnsignedShort = dataInputStream.readUnsignedShort();
                                                        byteOrderedDataOutputStream.writeUnsignedShort(readUnsignedShort);
                                                        readUnsignedShort -= 2;
                                                        if (readUnsignedShort >= 0) {
                                                            while (readUnsignedShort > 0) {
                                                                read = dataInputStream.read(bArr, 0, Math.min(readUnsignedShort, bArr.length));
                                                                if (read < 0) {
                                                                    break;
                                                                }
                                                                byteOrderedDataOutputStream.write(bArr, 0, read);
                                                                readUnsignedShort -= read;
                                                            }
                                                            break;
                                                        }
                                                        throw new IOException("Invalid length");
                                                }
                                            }
                                            throw new IOException("Invalid marker");
                                        }

                                        private void readExifSegment(byte[] bArr, int i) throws IOException {
                                            ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
                                            parseTiffHeaders(byteOrderedDataInputStream, bArr.length);
                                            readImageFileDirectory(byteOrderedDataInputStream, i);
                                        }

                                        private void addDefaultValuesForCompatibility() {
                                            String attribute = getAttribute(TAG_DATETIME_ORIGINAL);
                                            if (attribute != null && getAttribute(TAG_DATETIME) == null) {
                                                this.mAttributes[0].put(TAG_DATETIME, ExifAttribute.createString(attribute));
                                            }
                                            if (getAttribute(TAG_IMAGE_WIDTH) == null) {
                                                this.mAttributes[0].put(TAG_IMAGE_WIDTH, ExifAttribute.createULong(0, this.mExifByteOrder));
                                            }
                                            if (getAttribute(TAG_IMAGE_LENGTH) == null) {
                                                this.mAttributes[0].put(TAG_IMAGE_LENGTH, ExifAttribute.createULong(0, this.mExifByteOrder));
                                            }
                                            if (getAttribute(TAG_ORIENTATION) == null) {
                                                this.mAttributes[0].put(TAG_ORIENTATION, ExifAttribute.createULong(0, this.mExifByteOrder));
                                            }
                                            if (getAttribute(TAG_LIGHT_SOURCE) == null) {
                                                this.mAttributes[1].put(TAG_LIGHT_SOURCE, ExifAttribute.createULong(0, this.mExifByteOrder));
                                            }
                                        }

                                        private ByteOrder readByteOrder(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
                                            short readShort = byteOrderedDataInputStream.readShort();
                                            switch (readShort) {
                                                case (short) 18761:
                                                    return ByteOrder.LITTLE_ENDIAN;
                                                case (short) 19789:
                                                    return ByteOrder.BIG_ENDIAN;
                                                default:
                                                    throw new IOException("Invalid byte order: " + Integer.toHexString(readShort));
                                            }
                                        }

                                        private void parseTiffHeaders(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
                                            this.mExifByteOrder = readByteOrder(byteOrderedDataInputStream);
                                            byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
                                            int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
                                            if (this.mMimeType == 7 || this.mMimeType == 10 || readUnsignedShort == 42) {
                                                readUnsignedShort = byteOrderedDataInputStream.readInt();
                                                if (readUnsignedShort < 8 || readUnsignedShort >= i) {
                                                    throw new IOException("Invalid first Ifd offset: " + readUnsignedShort);
                                                }
                                                readUnsignedShort -= 8;
                                                if (readUnsignedShort > 0 && byteOrderedDataInputStream.skipBytes(readUnsignedShort) != readUnsignedShort) {
                                                    throw new IOException("Couldn't jump to first Ifd: " + readUnsignedShort);
                                                }
                                                return;
                                            }
                                            throw new IOException("Invalid start code: " + Integer.toHexString(readUnsignedShort));
                                        }

                                        private void readImageFileDirectory(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
                                            if (byteOrderedDataInputStream.mPosition + 2 <= byteOrderedDataInputStream.mLength) {
                                                short readShort = byteOrderedDataInputStream.readShort();
                                                if (byteOrderedDataInputStream.mPosition + (readShort * 12) <= byteOrderedDataInputStream.mLength) {
                                                    for (short s = (short) 0; s < readShort; s = (short) (s + 1)) {
                                                        long j;
                                                        int i2;
                                                        Object obj;
                                                        int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
                                                        int readUnsignedShort2 = byteOrderedDataInputStream.readUnsignedShort();
                                                        int readInt = byteOrderedDataInputStream.readInt();
                                                        long peek = (long) (byteOrderedDataInputStream.peek() + 4);
                                                        ExifTag exifTag = (ExifTag) sExifTagMapsForReading[i].get(Integer.valueOf(readUnsignedShort));
                                                        if (exifTag == null) {
                                                            Log.w(TAG, "Skip the tag entry since tag number is not defined: " + readUnsignedShort);
                                                            j = 0;
                                                            i2 = readUnsignedShort2;
                                                            obj = null;
                                                        } else if (readUnsignedShort2 <= 0 || readUnsignedShort2 >= IFD_FORMAT_BYTES_PER_FORMAT.length) {
                                                            Log.w(TAG, "Skip the tag entry since data format is invalid: " + readUnsignedShort2);
                                                            j = 0;
                                                            i2 = readUnsignedShort2;
                                                            obj = null;
                                                        } else if (exifTag.isFormatCompatible(readUnsignedShort2)) {
                                                            if (readUnsignedShort2 == 7) {
                                                                readUnsignedShort2 = exifTag.primaryFormat;
                                                            }
                                                            long j2 = ((long) readInt) * ((long) IFD_FORMAT_BYTES_PER_FORMAT[readUnsignedShort2]);
                                                            if (j2 < 0 || j2 > 2147483647L) {
                                                                Log.w(TAG, "Skip the tag entry since the number of components is invalid: " + readInt);
                                                                j = j2;
                                                                i2 = readUnsignedShort2;
                                                                obj = null;
                                                            } else {
                                                                j = j2;
                                                                i2 = readUnsignedShort2;
                                                                readUnsignedShort2 = 1;
                                                            }
                                                        } else {
                                                            Log.w(TAG, "Skip the tag entry since data format (" + IFD_FORMAT_NAMES[readUnsignedShort2] + ") is unexpected for tag: " + exifTag.name);
                                                            j = 0;
                                                            i2 = readUnsignedShort2;
                                                            obj = null;
                                                        }
                                                        if (obj == null) {
                                                            byteOrderedDataInputStream.seek(peek);
                                                        } else {
                                                            if (j > 4) {
                                                                readUnsignedShort2 = byteOrderedDataInputStream.readInt();
                                                                if (this.mMimeType == 7) {
                                                                    if (TAG_MAKER_NOTE.equals(exifTag.name)) {
                                                                        this.mOrfMakerNoteOffset = readUnsignedShort2;
                                                                    } else if (i == 6 && TAG_ORF_THUMBNAIL_IMAGE.equals(exifTag.name)) {
                                                                        this.mOrfThumbnailOffset = readUnsignedShort2;
                                                                        this.mOrfThumbnailLength = readInt;
                                                                        ExifAttribute createUShort = ExifAttribute.createUShort(6, this.mExifByteOrder);
                                                                        ExifAttribute createULong = ExifAttribute.createULong((long) this.mOrfThumbnailOffset, this.mExifByteOrder);
                                                                        ExifAttribute createULong2 = ExifAttribute.createULong((long) this.mOrfThumbnailLength, this.mExifByteOrder);
                                                                        this.mAttributes[4].put(TAG_COMPRESSION, createUShort);
                                                                        this.mAttributes[4].put(TAG_JPEG_INTERCHANGE_FORMAT, createULong);
                                                                        this.mAttributes[4].put(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, createULong2);
                                                                    }
                                                                } else if (this.mMimeType == 10 && TAG_RW2_JPG_FROM_RAW.equals(exifTag.name)) {
                                                                    this.mRw2JpgFromRawOffset = readUnsignedShort2;
                                                                }
                                                                if (((long) readUnsignedShort2) + j <= ((long) byteOrderedDataInputStream.mLength)) {
                                                                    byteOrderedDataInputStream.seek((long) readUnsignedShort2);
                                                                } else {
                                                                    Log.w(TAG, "Skip the tag entry since data offset is invalid: " + readUnsignedShort2);
                                                                    byteOrderedDataInputStream.seek(peek);
                                                                }
                                                            }
                                                            Integer num = (Integer) sExifPointerTagMap.get(Integer.valueOf(readUnsignedShort));
                                                            if (num != null) {
                                                                j = -1;
                                                                switch (i2) {
                                                                    case 3:
                                                                        j = (long) byteOrderedDataInputStream.readUnsignedShort();
                                                                        break;
                                                                    case 4:
                                                                        j = byteOrderedDataInputStream.readUnsignedInt();
                                                                        break;
                                                                    case 8:
                                                                        j = (long) byteOrderedDataInputStream.readShort();
                                                                        break;
                                                                    case 9:
                                                                    case 13:
                                                                        j = (long) byteOrderedDataInputStream.readInt();
                                                                        break;
                                                                }
                                                                if (j <= 0 || j >= ((long) byteOrderedDataInputStream.mLength)) {
                                                                    Log.w(TAG, "Skip jump into the IFD since its offset is invalid: " + j);
                                                                } else {
                                                                    byteOrderedDataInputStream.seek(j);
                                                                    readImageFileDirectory(byteOrderedDataInputStream, num.intValue());
                                                                }
                                                                byteOrderedDataInputStream.seek(peek);
                                                            } else {
                                                                byte[] bArr = new byte[((int) j)];
                                                                byteOrderedDataInputStream.readFully(bArr);
                                                                ExifAttribute exifAttribute = new ExifAttribute(i2, readInt, bArr);
                                                                this.mAttributes[i].put(exifTag.name, exifAttribute);
                                                                if (TAG_DNG_VERSION.equals(exifTag.name)) {
                                                                    this.mMimeType = 3;
                                                                }
                                                                if (((TAG_MAKE.equals(exifTag.name) || TAG_MODEL.equals(exifTag.name)) && exifAttribute.getStringValue(this.mExifByteOrder).contains(PEF_SIGNATURE)) || (TAG_COMPRESSION.equals(exifTag.name) && exifAttribute.getIntValue(this.mExifByteOrder) == 65535)) {
                                                                    this.mMimeType = 8;
                                                                }
                                                                if (((long) byteOrderedDataInputStream.peek()) != peek) {
                                                                    byteOrderedDataInputStream.seek(peek);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (byteOrderedDataInputStream.peek() + 4 <= byteOrderedDataInputStream.mLength) {
                                                        int readInt2 = byteOrderedDataInputStream.readInt();
                                                        if (readInt2 > 8 && readInt2 < byteOrderedDataInputStream.mLength) {
                                                            byteOrderedDataInputStream.seek((long) readInt2);
                                                            if (this.mAttributes[4].isEmpty()) {
                                                                readImageFileDirectory(byteOrderedDataInputStream, 4);
                                                            } else if (this.mAttributes[5].isEmpty()) {
                                                                readImageFileDirectory(byteOrderedDataInputStream, 5);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        private void retrieveJpegImageSize(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
                                            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get(TAG_IMAGE_WIDTH);
                                            if (((ExifAttribute) this.mAttributes[i].get(TAG_IMAGE_LENGTH)) == null || exifAttribute == null) {
                                                ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[i].get(TAG_JPEG_INTERCHANGE_FORMAT);
                                                if (exifAttribute2 != null) {
                                                    getJpegAttributes(byteOrderedDataInputStream, exifAttribute2.getIntValue(this.mExifByteOrder), i);
                                                }
                                            }
                                        }

                                        private void setThumbnailData(ByteOrderedDataInputStream byteOrderedDataInputStream) throws IOException {
                                            HashMap hashMap = this.mAttributes[4];
                                            ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_COMPRESSION);
                                            if (exifAttribute != null) {
                                                this.mThumbnailCompression = exifAttribute.getIntValue(this.mExifByteOrder);
                                                switch (this.mThumbnailCompression) {
                                                    case 1:
                                                    case 7:
                                                        if (isSupportedDataType(hashMap)) {
                                                            handleThumbnailFromStrips(byteOrderedDataInputStream, hashMap);
                                                            return;
                                                        }
                                                        return;
                                                    case 6:
                                                        handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
                                                        return;
                                                    default:
                                                        return;
                                                }
                                            }
                                            this.mThumbnailCompression = 6;
                                            handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
                                        }

                                        private void handleThumbnailFromJfif(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap hashMap) throws IOException {
                                            ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_JPEG_INTERCHANGE_FORMAT);
                                            ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                            if (exifAttribute != null && exifAttribute2 != null) {
                                                int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
                                                int min = Math.min(exifAttribute2.getIntValue(this.mExifByteOrder), byteOrderedDataInputStream.available() - intValue);
                                                if (this.mMimeType == 4 || this.mMimeType == 9 || this.mMimeType == 10) {
                                                    intValue += this.mExifOffset;
                                                } else if (this.mMimeType == 7) {
                                                    intValue += this.mOrfMakerNoteOffset;
                                                }
                                                if (intValue > 0 && min > 0) {
                                                    this.mHasThumbnail = true;
                                                    this.mThumbnailOffset = intValue;
                                                    this.mThumbnailLength = min;
                                                    if (this.mFilename == null && this.mAssetInputStream == null) {
                                                        byte[] bArr = new byte[min];
                                                        byteOrderedDataInputStream.seek((long) intValue);
                                                        byteOrderedDataInputStream.readFully(bArr);
                                                        this.mThumbnailBytes = bArr;
                                                    }
                                                }
                                            }
                                        }

                                        private void handleThumbnailFromStrips(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap hashMap) throws IOException {
                                            ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_STRIP_OFFSETS);
                                            ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get(TAG_STRIP_BYTE_COUNTS);
                                            if (exifAttribute != null && exifAttribute2 != null) {
                                                long[] convertToLongArray = convertToLongArray(exifAttribute.getValue(this.mExifByteOrder));
                                                long[] convertToLongArray2 = convertToLongArray(exifAttribute2.getValue(this.mExifByteOrder));
                                                if (convertToLongArray == null) {
                                                    Log.w(TAG, "stripOffsets should not be null.");
                                                } else if (convertToLongArray2 == null) {
                                                    Log.w(TAG, "stripByteCounts should not be null.");
                                                } else {
                                                    int i;
                                                    long j = 0;
                                                    for (long j2 : convertToLongArray2) {
                                                        j += j2;
                                                    }
                                                    Object obj = new byte[((int) j)];
                                                    int i2 = 0;
                                                    int i3 = 0;
                                                    for (i = 0; i < convertToLongArray.length; i++) {
                                                        int i4 = (int) convertToLongArray2[i];
                                                        int i5 = ((int) convertToLongArray[i]) - i3;
                                                        if (i5 < 0) {
                                                            Log.d(TAG, "Invalid strip offset value");
                                                        }
                                                        byteOrderedDataInputStream.seek((long) i5);
                                                        i3 += i5;
                                                        Object obj2 = new byte[i4];
                                                        byteOrderedDataInputStream.read(obj2);
                                                        i3 += i4;
                                                        System.arraycopy(obj2, 0, obj, i2, obj2.length);
                                                        i2 += obj2.length;
                                                    }
                                                    this.mHasThumbnail = true;
                                                    this.mThumbnailBytes = obj;
                                                    this.mThumbnailLength = obj.length;
                                                }
                                            }
                                        }

                                        private boolean isSupportedDataType(HashMap hashMap) throws IOException {
                                            ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_BITS_PER_SAMPLE);
                                            if (exifAttribute != null) {
                                                int[] iArr = (int[]) exifAttribute.getValue(this.mExifByteOrder);
                                                if (Arrays.equals(BITS_PER_SAMPLE_RGB, iArr)) {
                                                    return true;
                                                }
                                                if (this.mMimeType == 3) {
                                                    ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get(TAG_PHOTOMETRIC_INTERPRETATION);
                                                    if (exifAttribute2 != null) {
                                                        int intValue = exifAttribute2.getIntValue(this.mExifByteOrder);
                                                        if ((intValue == 1 && Arrays.equals(iArr, BITS_PER_SAMPLE_GREYSCALE_2)) || (intValue == 6 && Arrays.equals(iArr, BITS_PER_SAMPLE_RGB))) {
                                                            return true;
                                                        }
                                                    }
                                                }
                                            }
                                            return false;
                                        }

                                        private boolean isThumbnail(HashMap hashMap) throws IOException {
                                            ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_IMAGE_LENGTH);
                                            ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get(TAG_IMAGE_WIDTH);
                                            if (!(exifAttribute == null || exifAttribute2 == null)) {
                                                int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
                                                int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
                                                if (intValue <= 512 && intValue2 <= 512) {
                                                    return true;
                                                }
                                            }
                                            return false;
                                        }

                                        private void validateImages(InputStream inputStream) throws IOException {
                                            swapBasedOnImageSize(0, 5);
                                            swapBasedOnImageSize(0, 4);
                                            swapBasedOnImageSize(5, 4);
                                            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[1].get(TAG_PIXEL_X_DIMENSION);
                                            ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[1].get(TAG_PIXEL_Y_DIMENSION);
                                            if (!(exifAttribute == null || exifAttribute2 == null)) {
                                                this.mAttributes[0].put(TAG_IMAGE_WIDTH, exifAttribute);
                                                this.mAttributes[0].put(TAG_IMAGE_LENGTH, exifAttribute2);
                                            }
                                            if (this.mAttributes[4].isEmpty() && isThumbnail(this.mAttributes[5])) {
                                                this.mAttributes[4] = this.mAttributes[5];
                                                this.mAttributes[5] = new HashMap();
                                            }
                                            if (!isThumbnail(this.mAttributes[4])) {
                                                Log.d(TAG, "No image meets the size requirements of a thumbnail image.");
                                            }
                                        }

                                        private void updateImageSizeValues(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) throws IOException {
                                            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get(TAG_DEFAULT_CROP_SIZE);
                                            ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[i].get(TAG_RW2_SENSOR_TOP_BORDER);
                                            ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[i].get(TAG_RW2_SENSOR_LEFT_BORDER);
                                            ExifAttribute exifAttribute4 = (ExifAttribute) this.mAttributes[i].get(TAG_RW2_SENSOR_BOTTOM_BORDER);
                                            ExifAttribute exifAttribute5 = (ExifAttribute) this.mAttributes[i].get(TAG_RW2_SENSOR_RIGHT_BORDER);
                                            if (exifAttribute != null) {
                                                Object createURational;
                                                Object createURational2;
                                                if (exifAttribute.format == 5) {
                                                    Rational[] rationalArr = (Rational[]) exifAttribute.getValue(this.mExifByteOrder);
                                                    if (rationalArr == null || rationalArr.length != 2) {
                                                        Log.w(TAG, "Invalid crop size values. cropSize=" + Arrays.toString(rationalArr));
                                                        return;
                                                    } else {
                                                        createURational = ExifAttribute.createURational(rationalArr[0], this.mExifByteOrder);
                                                        createURational2 = ExifAttribute.createURational(rationalArr[1], this.mExifByteOrder);
                                                    }
                                                } else {
                                                    int[] iArr = (int[]) exifAttribute.getValue(this.mExifByteOrder);
                                                    if (iArr == null || iArr.length != 2) {
                                                        Log.w(TAG, "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                                                        return;
                                                    } else {
                                                        createURational = ExifAttribute.createUShort(iArr[0], this.mExifByteOrder);
                                                        createURational2 = ExifAttribute.createUShort(iArr[1], this.mExifByteOrder);
                                                    }
                                                }
                                                this.mAttributes[i].put(TAG_IMAGE_WIDTH, createURational);
                                                this.mAttributes[i].put(TAG_IMAGE_LENGTH, createURational2);
                                            } else if (exifAttribute2 == null || exifAttribute3 == null || exifAttribute4 == null || exifAttribute5 == null) {
                                                retrieveJpegImageSize(byteOrderedDataInputStream, i);
                                            } else {
                                                int intValue = exifAttribute2.getIntValue(this.mExifByteOrder);
                                                int intValue2 = exifAttribute4.getIntValue(this.mExifByteOrder);
                                                int intValue3 = exifAttribute5.getIntValue(this.mExifByteOrder);
                                                int intValue4 = exifAttribute3.getIntValue(this.mExifByteOrder);
                                                if (intValue2 > intValue && intValue3 > intValue4) {
                                                    intValue = intValue2 - intValue;
                                                    intValue2 = intValue3 - intValue4;
                                                    exifAttribute = ExifAttribute.createUShort(intValue, this.mExifByteOrder);
                                                    exifAttribute2 = ExifAttribute.createUShort(intValue2, this.mExifByteOrder);
                                                    this.mAttributes[i].put(TAG_IMAGE_LENGTH, exifAttribute);
                                                    this.mAttributes[i].put(TAG_IMAGE_WIDTH, exifAttribute2);
                                                }
                                            }
                                        }

                                        private int writeExifSegment(ByteOrderedDataOutputStream byteOrderedDataOutputStream, int i) throws IOException {
                                            int size;
                                            int i2;
                                            int i3;
                                            int[] iArr = new int[EXIF_TAGS.length];
                                            int[] iArr2 = new int[EXIF_TAGS.length];
                                            for (ExifTag exifTag : EXIF_POINTER_TAGS) {
                                                removeAttribute(exifTag.name);
                                            }
                                            removeAttribute(JPEG_INTERCHANGE_FORMAT_TAG.name);
                                            removeAttribute(JPEG_INTERCHANGE_FORMAT_LENGTH_TAG.name);
                                            for (i3 = 0; i3 < EXIF_TAGS.length; i3++) {
                                                for (Object obj : this.mAttributes[i3].entrySet().toArray()) {
                                                    Entry entry = (Entry) obj;
                                                    if (entry.getValue() == null) {
                                                        this.mAttributes[i3].remove(entry.getKey());
                                                    }
                                                }
                                            }
                                            if (!this.mAttributes[1].isEmpty()) {
                                                this.mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(0, this.mExifByteOrder));
                                            }
                                            if (!this.mAttributes[2].isEmpty()) {
                                                this.mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(0, this.mExifByteOrder));
                                            }
                                            if (!this.mAttributes[3].isEmpty()) {
                                                this.mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(0, this.mExifByteOrder));
                                            }
                                            if (this.mHasThumbnail) {
                                                this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_TAG.name, ExifAttribute.createULong(0, this.mExifByteOrder));
                                                this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_LENGTH_TAG.name, ExifAttribute.createULong((long) this.mThumbnailLength, this.mExifByteOrder));
                                            }
                                            for (i2 = 0; i2 < EXIF_TAGS.length; i2++) {
                                                i3 = 0;
                                                for (Entry entry2 : this.mAttributes[i2].entrySet()) {
                                                    size = ((ExifAttribute) entry2.getValue()).size();
                                                    if (size > 4) {
                                                        size += i3;
                                                    } else {
                                                        size = i3;
                                                    }
                                                    i3 = size;
                                                }
                                                iArr2[i2] = iArr2[i2] + i3;
                                            }
                                            i3 = 8;
                                            for (size = 0; size < EXIF_TAGS.length; size++) {
                                                if (!this.mAttributes[size].isEmpty()) {
                                                    iArr[size] = i3;
                                                    i3 += (((this.mAttributes[size].size() * 12) + 2) + 4) + iArr2[size];
                                                }
                                            }
                                            if (this.mHasThumbnail) {
                                                this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_TAG.name, ExifAttribute.createULong((long) i3, this.mExifByteOrder));
                                                this.mThumbnailOffset = i + i3;
                                                i3 += this.mThumbnailLength;
                                            }
                                            int i4 = i3 + 8;
                                            if (!this.mAttributes[1].isEmpty()) {
                                                this.mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong((long) iArr[1], this.mExifByteOrder));
                                            }
                                            if (!this.mAttributes[2].isEmpty()) {
                                                this.mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong((long) iArr[2], this.mExifByteOrder));
                                            }
                                            if (!this.mAttributes[3].isEmpty()) {
                                                this.mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong((long) iArr[3], this.mExifByteOrder));
                                            }
                                            byteOrderedDataOutputStream.writeUnsignedShort(i4);
                                            byteOrderedDataOutputStream.write(IDENTIFIER_EXIF_APP1);
                                            byteOrderedDataOutputStream.writeShort(this.mExifByteOrder == ByteOrder.BIG_ENDIAN ? BYTE_ALIGN_MM : BYTE_ALIGN_II);
                                            byteOrderedDataOutputStream.setByteOrder(this.mExifByteOrder);
                                            byteOrderedDataOutputStream.writeUnsignedShort(42);
                                            byteOrderedDataOutputStream.writeUnsignedInt(8);
                                            for (i2 = 0; i2 < EXIF_TAGS.length; i2++) {
                                                if (!this.mAttributes[i2].isEmpty()) {
                                                    ExifAttribute exifAttribute;
                                                    byteOrderedDataOutputStream.writeUnsignedShort(this.mAttributes[i2].size());
                                                    size = ((iArr[i2] + 2) + (this.mAttributes[i2].size() * 12)) + 4;
                                                    int i5 = size;
                                                    for (Entry entry22 : this.mAttributes[i2].entrySet()) {
                                                        int i6 = ((ExifTag) sExifTagMapsForWriting[i2].get(entry22.getKey())).number;
                                                        exifAttribute = (ExifAttribute) entry22.getValue();
                                                        i3 = exifAttribute.size();
                                                        byteOrderedDataOutputStream.writeUnsignedShort(i6);
                                                        byteOrderedDataOutputStream.writeUnsignedShort(exifAttribute.format);
                                                        byteOrderedDataOutputStream.writeInt(exifAttribute.numberOfComponents);
                                                        if (i3 > 4) {
                                                            byteOrderedDataOutputStream.writeUnsignedInt((long) i5);
                                                            size = i5 + i3;
                                                        } else {
                                                            byteOrderedDataOutputStream.write(exifAttribute.bytes);
                                                            if (i3 < 4) {
                                                                for (size = i3; size < 4; size++) {
                                                                    byteOrderedDataOutputStream.writeByte(0);
                                                                }
                                                            }
                                                            size = i5;
                                                        }
                                                        i5 = size;
                                                    }
                                                    if (i2 != 0 || this.mAttributes[4].isEmpty()) {
                                                        byteOrderedDataOutputStream.writeUnsignedInt(0);
                                                    } else {
                                                        byteOrderedDataOutputStream.writeUnsignedInt((long) iArr[4]);
                                                    }
                                                    for (Entry entry222 : this.mAttributes[i2].entrySet()) {
                                                        exifAttribute = (ExifAttribute) entry222.getValue();
                                                        if (exifAttribute.bytes.length > 4) {
                                                            byteOrderedDataOutputStream.write(exifAttribute.bytes, 0, exifAttribute.bytes.length);
                                                        }
                                                    }
                                                }
                                            }
                                            if (this.mHasThumbnail) {
                                                byteOrderedDataOutputStream.write(getThumbnailBytes());
                                            }
                                            byteOrderedDataOutputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
                                            return i4;
                                        }

                                        private static Pair<Integer, Integer> guessDataFormat(String str) {
                                            if (str.contains(",")) {
                                                String[] split = str.split(",");
                                                Pair<Integer, Integer> guessDataFormat = guessDataFormat(split[0]);
                                                if (((Integer) guessDataFormat.first).intValue() == 2) {
                                                    return guessDataFormat;
                                                }
                                                for (int i = 1; i < split.length; i++) {
                                                    int intValue;
                                                    int i2;
                                                    Pair guessDataFormat2 = guessDataFormat(split[i]);
                                                    if (((Integer) guessDataFormat2.first).equals(guessDataFormat.first) || ((Integer) guessDataFormat2.second).equals(guessDataFormat.first)) {
                                                        intValue = ((Integer) guessDataFormat.first).intValue();
                                                    } else {
                                                        intValue = -1;
                                                    }
                                                    if (((Integer) guessDataFormat.second).intValue() == -1 || !(((Integer) guessDataFormat2.first).equals(guessDataFormat.second) || ((Integer) guessDataFormat2.second).equals(guessDataFormat.second))) {
                                                        i2 = -1;
                                                    } else {
                                                        i2 = ((Integer) guessDataFormat.second).intValue();
                                                    }
                                                    if (intValue == -1 && i2 == -1) {
                                                        return new Pair(Integer.valueOf(2), Integer.valueOf(-1));
                                                    }
                                                    if (intValue == -1) {
                                                        guessDataFormat = new Pair(Integer.valueOf(i2), Integer.valueOf(-1));
                                                    } else if (i2 == -1) {
                                                        guessDataFormat = new Pair(Integer.valueOf(intValue), Integer.valueOf(-1));
                                                    }
                                                }
                                                return guessDataFormat;
                                            } else if (str.contains("/")) {
                                                String[] split2 = str.split("/");
                                                if (split2.length == 2) {
                                                    try {
                                                        long parseDouble = (long) Double.parseDouble(split2[0]);
                                                        long parseDouble2 = (long) Double.parseDouble(split2[1]);
                                                        if (parseDouble < 0 || parseDouble2 < 0) {
                                                            return new Pair(Integer.valueOf(10), Integer.valueOf(-1));
                                                        }
                                                        if (parseDouble > 2147483647L || parseDouble2 > 2147483647L) {
                                                            return new Pair(Integer.valueOf(5), Integer.valueOf(-1));
                                                        }
                                                        return new Pair(Integer.valueOf(10), Integer.valueOf(5));
                                                    } catch (NumberFormatException e) {
                                                    }
                                                }
                                                return new Pair(Integer.valueOf(2), Integer.valueOf(-1));
                                            } else {
                                                try {
                                                    Long valueOf = Long.valueOf(Long.parseLong(str));
                                                    if (valueOf.longValue() >= 0 && valueOf.longValue() <= 65535) {
                                                        return new Pair(Integer.valueOf(3), Integer.valueOf(4));
                                                    }
                                                    if (valueOf.longValue() < 0) {
                                                        return new Pair(Integer.valueOf(9), Integer.valueOf(-1));
                                                    }
                                                    return new Pair(Integer.valueOf(4), Integer.valueOf(-1));
                                                } catch (NumberFormatException e2) {
                                                    try {
                                                        Double.parseDouble(str);
                                                        return new Pair(Integer.valueOf(12), Integer.valueOf(-1));
                                                    } catch (NumberFormatException e3) {
                                                        return new Pair(Integer.valueOf(2), Integer.valueOf(-1));
                                                    }
                                                }
                                            }
                                        }

                                        private void swapBasedOnImageSize(int i, int i2) throws IOException {
                                            if (!this.mAttributes[i].isEmpty() && !this.mAttributes[i2].isEmpty()) {
                                                ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get(TAG_IMAGE_LENGTH);
                                                ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[i].get(TAG_IMAGE_WIDTH);
                                                ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[i2].get(TAG_IMAGE_LENGTH);
                                                ExifAttribute exifAttribute4 = (ExifAttribute) this.mAttributes[i2].get(TAG_IMAGE_WIDTH);
                                                if (exifAttribute != null && exifAttribute2 != null && exifAttribute3 != null && exifAttribute4 != null) {
                                                    int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
                                                    int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
                                                    int intValue3 = exifAttribute3.getIntValue(this.mExifByteOrder);
                                                    int intValue4 = exifAttribute4.getIntValue(this.mExifByteOrder);
                                                    if (intValue < intValue3 && intValue2 < intValue4) {
                                                        HashMap hashMap = this.mAttributes[i];
                                                        this.mAttributes[i] = this.mAttributes[i2];
                                                        this.mAttributes[i2] = hashMap;
                                                    }
                                                }
                                            }
                                        }

                                        private static void closeQuietly(Closeable closeable) {
                                            if (closeable != null) {
                                                try {
                                                    closeable.close();
                                                } catch (RuntimeException e) {
                                                    throw e;
                                                } catch (Exception e2) {
                                                }
                                            }
                                        }

                                        private static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
                                            byte[] bArr = new byte[8192];
                                            int i = 0;
                                            while (true) {
                                                int read = inputStream.read(bArr);
                                                if (read == -1) {
                                                    return i;
                                                }
                                                i += read;
                                                outputStream.write(bArr, 0, read);
                                            }
                                        }

                                        private static long[] convertToLongArray(Object obj) {
                                            if (obj instanceof int[]) {
                                                int[] iArr = (int[]) obj;
                                                Object obj2 = new long[iArr.length];
                                                for (int i = 0; i < iArr.length; i++) {
                                                    obj2[i] = (long) iArr[i];
                                                }
                                                return obj2;
                                            } else if (obj instanceof long[]) {
                                                return (long[]) obj;
                                            } else {
                                                return null;
                                            }
                                        }
                                    }
