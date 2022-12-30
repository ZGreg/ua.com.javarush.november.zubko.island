package seting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

public class IslandSettings {
    @Getter
    private int islandLength;
    @Getter
    private int islandWidth;
    @JsonIgnore
    private int locationsAmt;
    @Getter
    private int worldDuration;
    @Getter
    private int periodOfAction;


    public int getLocationsAmt() {
        return islandLength * islandWidth;
    }
}
