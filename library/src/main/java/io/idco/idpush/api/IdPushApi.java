package io.idco.idpush.api;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 1HE on 09/23/2018
 */

public interface IdPushApi {

    @FormUrlEncoded
    @POST(IdPushHolder.URL_IDPUSH_DEVICE_ADD)
    Observable<Response<IdPushResponse>> deviceAdd(
            @Field(IdPushHolder.PARAMETER_project_id) String projectId,
            @Field(IdPushHolder.PARAMETER_device_type) int deviceType,
            @Field(IdPushHolder.PARAMETER_game_version) String gameVersion,
            @Field(IdPushHolder.PARAMETER_device_model) String deviceModel,
            @Field(IdPushHolder.PARAMETER_device_os) int deviceOS,
            @Field(IdPushHolder.PARAMETER_identifier) String identifier,
            @Field(IdPushHolder.PARAMETER_notification_types) int notificationType,
            @Field(IdPushHolder.PARAMETER_test_type) int testType
    );

}
