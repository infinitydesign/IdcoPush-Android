package io.idco.idpush.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 1HE on 09/23/2018
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class IdPushResponse {

    @SerializedName(IdPushHolder.JSON_status)
    private int status;

    @SerializedName(IdPushHolder.JSON_message)
    private String message;

    @SerializedName(IdPushHolder.JSON_player_id)
    private String playerId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
