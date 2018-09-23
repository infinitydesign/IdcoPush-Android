package io.idco.idpush.api;

/**
 * Created by 1HE on 09/23/2018
 */

public class IdPushHolder {

    public static final int CODE_SUCCESS = 200;
    public static final int CODE_FORBIDDEN = 403;
    public static final int CODE_UNAUTHORIZED = 401;
    public static final int CODE_NOT_FOUND = 404;
    public static final int CODE_DATA_NOT_VALID = 422;

    public static final int CODE_FAIL = -1;
    public static final int CODE_IMAGE_LONG_SIZE = -2;
    public static final int CODE_CANCELED = -3;
    public static final int CODE_NO_INTERNET = -4;

    public static final String JSON_status = "status";
    public static final String JSON_message = "message";
    public static final String JSON_player_id = "player_id";

    public static final String PARAMETER_project_id = "project_id";
    public static final String PARAMETER_device_type = "device_type";
    public static final String PARAMETER_game_version = "game_version";
    public static final String PARAMETER_device_model = "device_model";
    public static final String PARAMETER_device_os = "device_os";
    public static final String PARAMETER_identifier = "identifier";
    public static final String PARAMETER_notification_types = "notification_types";
    public static final String PARAMETER_test_type = "test_type";

    private static int counter = 1;
    public static final int DEVICE_ADD = counter++;

    private static final String URL_BASE = "idpush.top";
    private static final String URL_PREFIX_HTTP = "http://";
    public static final String URL_API = URL_PREFIX_HTTP + URL_BASE + "/api/v1/";

    public static final String URL_IDPUSH_DEVICE_ADD = "device/add";
}
