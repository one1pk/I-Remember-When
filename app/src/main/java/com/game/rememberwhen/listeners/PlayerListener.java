package com.game.rememberwhen.listeners;

import com.game.rememberwhen.Player;

public interface PlayerListener {
    void initiateVideoMeeting(Player user);

    void initiateAudioMeeting(Player user);

    void onMultipleUsersAction(Boolean isMultipleUsersSelected);
}
